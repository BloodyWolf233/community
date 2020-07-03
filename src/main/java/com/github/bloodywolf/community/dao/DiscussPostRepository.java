package com.github.bloodywolf.community.dao;

import com.github.bloodywolf.community.entity.DiscussPost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/27 18:41
 */
@Repository
public interface DiscussPostRepository extends ElasticsearchRepository<DiscussPost, Integer> {

}
