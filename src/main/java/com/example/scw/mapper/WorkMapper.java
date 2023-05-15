package com.example.scw.mapper;

import com.example.scw.pojo.entity.*;
import com.sun.source.doctree.CommentTree;
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


    @Select("select * from scw.comment where BelongTeamWork = #{TeamWorkId} and status =1")
    Comment getCommentWork(@Param("TeamWorkId")Integer TeamWorkId);


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

    @Select("select * from team_work where BelongTeam = #{BelongTeam}")
    List<TeamWork> getTeamWorkByTeam(@Param("BelongTeam") Integer belongTeam);

    @Select("select * from team_work where BelongWork = #{StudyWorkId}")
    List<TeamWork> getTeamWorkByStudy(@Param("StudyWorkId")Integer studyWorkId);

    @Select("SELECT * from single_work where BelongWork = #{TeamWork}")
    List<SingleWork> getSingleWorkByTeamWork(@Param("TeamWork")Integer teamWork);

    @Select("SELECT * from single_work where BelongStudent = #{BelongStudent}")
    List<SingleWork> getSingleWorkByStudent(@Param("BelongStudent")Integer userId);

    @Insert("insert into single_work (SingleWorkId, WorkDescription, BelongStudent, BelongWork, ProductionRoute, Status) " +
            "VALUES (#{singleWorkId}, #{workDescription}, #{belongStudent}, #{belongWork}, #{productionRoute}, #{status})")
    Integer newSingleWork(SingleWork singleWork);

    @Select("select * from single_work where SingleWorkId = #{SingleWorkId}")
    SingleWork getSingleWork(@Param("SingleWorkId")Integer id);

    @Update("update single_work set WorkDescription = #{WorkDescription} where SingleWorkId = #{SingleWorkId}")
    Integer updateSingleWorkDescription(@Param("SingleWorkId")Integer id,@Param("WorkDescription")String description);

    @Update("update single_work set ProductionRoute = #{ProductionRoute}, Status = 0 where SingleWorkId = #{SingleWorkId}")
    Integer updateSingleWorkProSave(@Param("SingleWorkId")Integer id,@Param("ProductionRoute")String production);

    @Update("update single_work set ProductionRoute = #{ProductionRoute},Status = 1 where SingleWorkId = #{SingleWorkId}")
    Integer updateSingleWorkProCommit(@Param("SingleWorkId")Integer id,@Param("ProductionRoute")String production);

    @Update("update team_work set WorkDescription = #{workDescription},ProductionRoute = #{productionRoute},Status = #{status} where TeamWorkId = #{teamWorkId}")
    Integer updateTeamWork(TeamWork teamWork);

    @Insert("insert into comment (BelongTeamWork,status) values (#{TeamWorkId},0)")
    Integer newComment(@Param("TeamWorkId")Integer id);

    @Select("select * from comment where BelongTeamWork = #{TeamWorkId}")
    Comment getComment(@Param("TeamWorkId")Integer id);

    @Update("update comment set Description = #{description}, score = #{score}, status = #{status} where BelongTeamWork = #{belongTeamWork}")
    Integer updateComment(Comment comment);


}
