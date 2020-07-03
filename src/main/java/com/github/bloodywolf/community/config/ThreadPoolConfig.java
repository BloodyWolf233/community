package com.github.bloodywolf.community.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/29 16:49
 */
@Configuration
@EnableScheduling
@EnableAsync
public class ThreadPoolConfig {
}
