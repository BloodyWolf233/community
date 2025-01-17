package com.github.bloodywolf.community.event;

import com.alibaba.fastjson.JSONObject;
import com.github.bloodywolf.community.entity.DiscussPost;
import com.github.bloodywolf.community.entity.Event;
import com.github.bloodywolf.community.entity.Message;
import com.github.bloodywolf.community.service.DiscussPostService;
import com.github.bloodywolf.community.service.ElasticsearchService;
import com.github.bloodywolf.community.service.MessageService;
import com.github.bloodywolf.community.util.CommunityConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/24 16:48
 */
@Component
public class EventConsumer implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);

    @Autowired
    private MessageService messageService;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private ElasticsearchService elasticsearchService;


    @KafkaListener(topics = {TOPIC_COMMENT, TOPIC_LIKE, TOPIC_FOLLOW})
    public void handleCommentMessage(ConsumerRecord record){
        if (record==null||record.value()==null){
            logger.error("消息的内容为空!");
            return;
        }
        Event event = JSONObject.parseObject(record.value().toString(),Event.class);
        if (event==null){
            logger.error("消息格式错误!");
            return;
        }

        // 发送站内通知
        Message message = new Message();
        message.setFromId(SYSTEM_USER_ID);
        message.setToId(event.getEntityUserId());
        message.setConversationId(event.getTopic());
        message.setCreateTime(new Date());

        Map<String,Object> content = new HashMap<>();
        content.put("userId",event.getUserId());
        content.put("entityType",event.getEntityType());
        content.put("entityId",event.getEntityId());

        if (!event.getData().isEmpty()){
            for (Map.Entry<String, Object> entry : event.getData().entrySet()) {
                content.put(entry.getKey(),entry.getValue());
            }
        }
        message.setContent(JSONObject.toJSONString(content));
        messageService.addMessage(message);
    }

    /**
     * 消费发帖事件
     *
     * @param record
     */
    @KafkaListener(topics = {TOPIC_PUBLISH})
    public void handlePublishMessage(ConsumerRecord record){
        if (record==null||record.value()==null){
            logger.error("消息的内容为空!");
            return;
        }
        Event event = JSONObject.parseObject(record.value().toString(),Event.class);
        if (event==null){
            logger.error("消息格式错误!");
            return;
        }

        DiscussPost post = discussPostService.findDiscussPostById(event.getEntityId());
        elasticsearchService.saveDiscussPost(post);
    }

    /**
     * 消费删帖事件
     *
     * @param record
     */
    @KafkaListener(topics = {TOPIC_DELETE})
    public void handleDeleteMessage(ConsumerRecord record){
        if (record==null||record.value()==null){
            logger.error("消息的内容为空!");
            return;
        }
        Event event = JSONObject.parseObject(record.value().toString(),Event.class);
        if (event==null){
            logger.error("消息格式错误!");
            return;
        }

        elasticsearchService.deleteDiscussPost(event.getEntityId());
    }
}
