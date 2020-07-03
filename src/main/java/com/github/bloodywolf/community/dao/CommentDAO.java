package com.github.bloodywolf.community.dao;

import com.github.bloodywolf.community.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/21 15:08
 */
@Mapper
public interface CommentDAO {
    String TABLE_NAME = "comment";
    String INSERT_FIELDS = " user_id, entity_type, entity_id, target_id, content, status, create_time ";
    String SELECT_FIELDS = " id," +INSERT_FIELDS;

    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    int selectCountByEntity(int entityType, int entityId);

    @Insert({" insert into ",TABLE_NAME," (",INSERT_FIELDS,") values ( ",
            " #{userId}, #{entityType}, #{entityId}, #{targetId}, #{content}, #{status}, #{createTime}) "})
    int insertComment(Comment comment);

    @Select({" select ",SELECT_FIELDS," from ",TABLE_NAME," where id = #{id}"})
    Comment selectCommentById(int id);
}
