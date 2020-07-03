package com.github.bloodywolf.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/14 16:57
 */
@SpringBootApplication
public class CommunityApplication {

    @PostConstruct
    public void init(){
        // 解决netty启动冲突问题(Elasticsearch)
        // see Netty4Utils.setAvailableProcessors()
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }
}
