package com.example.scw.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.scw.pojo.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from scw.user where UserCode = #{UserCode} and UserPass = #{UserPass}")
    User getUserLogin(@Param("UserCode") String userCode, @Param("UserPass") String userPass);

    @Select("select UserId from scw.user where UserType = 'teacher'")
    Integer getTeacher();
}
