package com.github.bloodywolf.community.dao;

import com.github.bloodywolf.community.entity.User;
import org.apache.ibatis.annotations.*;

/**
 * @author BloodyWolf233
 * @version 0.1
 * @date 2020/6/14 17:21
 */
@Mapper
public interface UserDAO {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = " username, password, salt, email, type, status, activation_code, header_url, create_time ";
    String SELECT_FIELDS = " id," + INSERT_FIELDS;

    @Select({" select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id = #{id} "})
    User selectById(int id);

    @Select({" select ", SELECT_FIELDS, " from ", TABLE_NAME, " where username = #{username} "})
    User selectByName(String username);

    @Select({" select ", SELECT_FIELDS, " from ", TABLE_NAME, " where email = #{email} "})
    User selectByEmail(String email);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert({" insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values ( " ,
            " #{username}, #{password}, #{salt}, #{email}, #{type}, #{status}, ",
            " #{activationCode}, #{headerUrl}, #{createTime}) "})
    int insertUser(User user);

    @Update({" update ", TABLE_NAME, " set status = #{status} where id = #{id} "})
    int updateStatus(int id, int status);

    @Update({" update ", TABLE_NAME, " set header_url = #{headerUrl} where id = #{id} "})
    int updateHeader(int id, String headerUrl);

    @Update({" update ", TABLE_NAME, " set salt = {salt}, password = #{password} where id = #{id} "})
    int updatePassword(int id,String salt, String password);


}
