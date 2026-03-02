package com.tools.ehcache.mapper;

import com.tools.ehcache.entity.UserDO;
import org.apache.ibatis.annotations.*;

/**
 * @author shenlx
 * @description
 * @date 2026/2/15 21:02
 */
@Mapper
public interface UserMapper {

    @Update("update user set sname=#{name},ssex=#{sex} where sno=#{sno}")
    int update(UserDO user);

    @Delete("delete from user where sno=#{sno}")
    void deleteBySno(String sno);

    @Select("select * from user where sno=#{sno}")
    @Results(id = "student", value = { @Result(property = "sno", column = "sno", javaType = String.class),
            @Result(property = "name", column = "sname", javaType = String.class),
            @Result(property = "sex", column = "ssex", javaType = String.class) })
    UserDO queryBySno(String sno);
}
