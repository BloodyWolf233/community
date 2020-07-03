package com.github.bloodywolf.community;

import com.github.bloodywolf.community.dao.DiscussPostDAO;
import com.github.bloodywolf.community.dao.LoginTicketDAO;
import com.github.bloodywolf.community.dao.MessageDAO;
import com.github.bloodywolf.community.entity.DiscussPost;
import com.github.bloodywolf.community.entity.LoginTicket;
import com.github.bloodywolf.community.entity.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/14 17:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    DiscussPostDAO discussPostDAO;

    @Autowired
    LoginTicketDAO loginTicketDAO;

    @Autowired
    MessageDAO messageDAO;

    @Test
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostDAO.selectDiscussPosts(149, 0, 10,0);
        for (DiscussPost post : list) {
            System.out.println(post);
        }

        int rows = discussPostDAO.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(111);
        loginTicket.setStatus(0);
        loginTicket.setTicket("avc");
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        loginTicketDAO.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketDAO.selectByTicket("avc");
        System.out.println(loginTicket);

        loginTicketDAO.updateStatus("avc", 1);

        loginTicket = loginTicketDAO.selectByTicket("avc");
        System.out.println(loginTicket);

    }

    @Test
    public void testSelectLetters() {
        List<Message> list = messageDAO.selectConversations(111, 0, 20);
        for (Message message : list) {
            System.out.println(message);
        }
        int count = messageDAO.selectConversationCount(111);
        System.out.println(count);
        System.out.println("########################");

        list = messageDAO.selectLetters("111_112", 0, 10);
        for (Message message : list) {
            System.out.println(message);
        }
        count = messageDAO.selectLetterCount("111_112");
        System.out.println(count);

        System.out.println("########################");
        count = messageDAO.selectLetterUnreadCount(131,"111_131");
        System.out.println(count);
    }

    @Test
    public void testAddMessage(){
        Message message = new Message();
        message.setToId(111);
        message.setFromId(131);
        message.setConversationId("111_131");
        message.setContent("test1111");
        message.setStatus(0);
        message.setCreateTime(new Date());

        messageDAO.insertMessage(message);
    }
}
