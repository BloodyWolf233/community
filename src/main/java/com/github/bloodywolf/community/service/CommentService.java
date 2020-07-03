package com.github.bloodywolf.community.service;

import com.github.bloodywolf.community.dao.CommentDAO;
import com.github.bloodywolf.community.entity.Comment;
import com.github.bloodywolf.community.util.CommunityConstant;
import com.github.bloodywolf.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/21 15:17
 */
@Service
public class CommentService implements CommunityConstant {

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private DiscussPostService discussPostService;

    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return commentDAO.selectCommentsByEntity(entityType, entityId, offset, limit);
    }

    public int findCommentCount(int entityTye, int entityId) {
        return commentDAO.selectCountByEntity(entityTye, entityId);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }

        // 添加评论
        comment.setContent(HtmlUtils.htmlEscape(comment.getContent()));
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        int rows = commentDAO.insertComment(comment);

        // 更新帖子评论数量
        if (comment.getEntityType()==ENTITY_TYPE_POST){
            int count = commentDAO.selectCountByEntity(comment.getEntityType(), comment.getEntityId());
            discussPostService.updateCommentCount(comment.getEntityId(),count);
        }
        return rows;
    }

    public Comment findCommentById(int id){
        return commentDAO.selectCommentById(id);
    }
}
