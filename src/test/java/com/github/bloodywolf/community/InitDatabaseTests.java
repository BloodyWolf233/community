package com.github.bloodywolf.community;

import com.github.bloodywolf.community.dao.UserDAO;
import com.github.bloodywolf.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/14 16:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
//@Sql("/init-schema.sql")
public class InitDatabaseTests {
    @Autowired
    UserDAO userDAO;

    @Test
    public void testSelectUser() {
        User user = userDAO.selectById(101);
        System.out.println(user);

        user = userDAO.selectByName("liubei");
        System.out.println(user);

        user = userDAO.selectByEmail("liubei@gmail.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userDAO.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    public void updateUser() {
        int rows = userDAO.updateStatus(150, 1);
        System.out.println(rows);

        rows = userDAO.updateHeader(150, "http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows = userDAO.updatePassword(150, "hello","hello");
        System.out.println(rows);
    }
}
