package com.example.scw.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TeamMapper {

    @Select("select TeamId from team")
    List<Integer> getTeamIdAll();

    @Select("select ${Publisher} from team where TeamId = #{TeamId}")
    Integer getPublisherId(@Param("Publisher")String publisher,@Param("TeamId")Integer teamId);
}
