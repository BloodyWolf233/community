package com.github.bloodywolf.community.service;

import com.github.bloodywolf.community.dao.MessageDAO;
import com.github.bloodywolf.community.entity.Message;
import com.github.bloodywolf.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/22 15:11
 */
@Service
public class MessageService {
    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    public List<Message> findConversations(int userId, int offset, int limit) {
        return messageDAO.selectConversations(userId, offset, limit);
    }

    public int findConversationCount(int userId) {
        return messageDAO.selectConversationCount(userId);
    }

    public List<Message> findLetters(String conversationId, int offset, int limit) {
        return messageDAO.selectLetters(conversationId, offset, limit);
    }

    public int findLetterCount(String conversationId) {
        return messageDAO.selectLetterCount(conversationId);
    }

    public int findLetterUnreadCount(int userId, String conversationId) {
        return messageDAO.selectLetterUnreadCount(userId, conversationId);
    }

    public int addMessage(Message message) {
        message.setContent(HtmlUtils.htmlEscape(message.getContent()));
        message.setContent(sensitiveFilter.filter(message.getContent()));
        return messageDAO.insertMessage(message);
    }

    public int readMessage(List<Integer> ids){
        return messageDAO.updateStatus(ids,1);
    }

    public Message findLatestNotice(int userId, String topic) {
        return messageDAO.selectLatestNotice(userId, topic);
    }

    public int findNoticeCount(int userId, String topic) {
        return messageDAO.selectNoticeCount(userId, topic);
    }

    public int findNoticeUnreadCount(int userId, String topic) {
        return messageDAO.selectNoticeUnreadCount(userId, topic);
    }

    public List<Message> findNotices(int userId, String topic, int offset, int limit) {
        return messageDAO.selectNotices(userId, topic, offset, limit);
    }
}
