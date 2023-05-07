package com.example.scw.mapper;

import com.example.scw.pojo.entity.StudyWork;
import com.example.scw.pojo.entity.TeamWork;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkMapper {

    @Insert("insert into study_work (WorkId, Publisher, Content, ReleaseTime, EndTime, ResourceRoute, Status) " +
            "VALUES (#{workId}, #{publisher}, #{content}, #{releaseTime}, #{endTime}, #{resourceRoute}, #{status})")
    Integer newStudyWork(StudyWork studyWork);

    @Update("update study_work set Publisher=#{publisher}, Content=#{content}, ReleaseTime=#{releaseTime}," +
            " EndTime=#{endTime}, ResourceRoute=#{resourceRoute}, Status=#{status} where WorkId = #{workId}")
    Integer modifyStudyWork(StudyWork studyWork);

    @Select("select * from study_work where WorkId = #{WorkId}")
    StudyWork getStudyWork(@Param("WorkId")Integer workId);

    @Select("select * from study_work")
    List<StudyWork> getStudyWorkAll();

    @Insert("insert into team_work (TeamWorkId, BelongTeam, BelongWork, Status) " +
            "VALUES (#{teamWorkId}, #{belongTeam}, #{belongWork}, #{status})")
    Integer newTeamWork(TeamWork teamWork);

    @Select("select * from study_work where ReleaseTime < now() and Status = 0")
    List<StudyWork> getStudyWorKsToRelease();

    @Select("select * from study_work where EndTime < now() and Status = 1")
    List<StudyWork> getStudyWorKsToEnd();

    @Select("select * from team_work where TeamWorkId = #{TeamWorkId}")
    TeamWork getTeamWork(@Param("TeamWorkId")Integer teamWorkId);

    @Select("select * from team_work where BelongWork = #{StudyWorkId}")
    List<TeamWork> getTeamWorkByStudy(@Param("StudyWorkId")Integer studyWorkId);
}
