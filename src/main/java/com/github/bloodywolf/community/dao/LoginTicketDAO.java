package com.github.bloodywolf.community.dao;

import com.github.bloodywolf.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/18 14:25
 */
@Mapper
@Deprecated
public interface LoginTicketDAO {
    String TABLE_NAME = "login_ticket";
    String INSERT_FIELDS = " user_id, ticket, status, expired";
    String SELECT_FIELDS = " id," + INSERT_FIELDS;

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({" insert into ",TABLE_NAME," ( ",INSERT_FIELDS," ) values( ",
            "#{userId}, #{ticket}, #{status}, #{expired}) "})
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({" select ",SELECT_FIELDS," from ",TABLE_NAME," where ticket = #{ticket}" })
    LoginTicket selectByTicket(String ticket);

    @Update({" update ",TABLE_NAME," set status = #{status} where ticket = #{ticket} "})
    int updateStatus(String ticket,int status);
}
