package com.github.bloodywolf.community.dao;

import com.github.bloodywolf.community.entity.DiscussPost;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/15 14:52
 */
@Mapper
public interface DiscussPostDAO {
    String TABLE_NAME = "discuss_post";
    String INSERT_FIELDS = " user_id, title, content, type, status, create_time, comment_count, score ";
    String SELECT_FIELDS = " id," + INSERT_FIELDS;


    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit,int orderMode);


    int selectDiscussPostRows(@Param("userId") int userId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({" insert into ", TABLE_NAME, " ( ", INSERT_FIELDS, ") values(",
            "#{userId}, #{title}, #{content}, #{type}, #{status}, #{createTime}, #{commentCount}, #{score})"})
    int insertDiscussPost(DiscussPost discussPost);

    @Select({" select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id = #{id}"})
    DiscussPost selectDiscussPostById(int id);

    @Update({" update ", TABLE_NAME, "set comment_count = #{commentCount} where id = #{id}"})
    int updateCommentCount(int id, int commentCount);

    @Update({" update ", TABLE_NAME, "set type = #{type} where id = #{id}"})
    int updateType(int id, int type);

    @Update({" update ", TABLE_NAME, "set status = #{status} where id = #{id}"})
    int updateStatus(int id, int status);

    @Update({" update ", TABLE_NAME, "set score = #{score} where id = #{id}"})
    int updateScore(int id, double score);
}
