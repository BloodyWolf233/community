package com.github.bloodywolf.community;

import com.github.bloodywolf.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/20 14:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter(){
        String text = "这里可以赌博，可以嫖娼，可以吸毒，可以开票，哈哈哈哈！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

        text = "这☆里☆可☆以☆赌☆博☆，可☆以☆嫖☆娼，可☆以☆吸☆☆☆毒☆，可☆☆以☆☆☆开☆☆☆票☆☆，哈哈哈哈！";
        text = sensitiveFilter.filter(text);
        System.out.println(text);
    }
}
