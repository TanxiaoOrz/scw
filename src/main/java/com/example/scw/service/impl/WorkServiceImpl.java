package com.example.scw.service.impl;

import com.example.scw.mapper.TeamMapper;
import com.example.scw.mapper.UserMapper;
import com.example.scw.mapper.WorkMapper;
import com.example.scw.pojo.dto.SingleWorkDto;
import com.example.scw.pojo.dto.StudyWorkDto;
import com.example.scw.pojo.dto.TeamWorkDto;
import com.example.scw.pojo.entity.*;
import com.example.scw.pojo.exception.*;
import com.example.scw.service.WorkService;
import com.example.scw.utils.NotificationCreateUtils;
import com.example.scw.utils.SnowFlakeUtils;
import com.example.scw.utils.TeamConfigUtils;
import com.example.scw.utils.factory.SingleWorkFactory;
import com.example.scw.utils.factory.StudyWorkFactory;
import com.example.scw.utils.factory.TeamWorkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    SnowFlakeUtils snowFlakeUtils;
    @Autowired
    WorkMapper workMapper;

    @Autowired
    TeamMapper teamMapper;
    @Autowired
    NotificationCreateUtils notificationCreateUtils;
    @Autowired
    TeamWorkFactory teamWorkFactory;
    @Autowired
    StudyWorkFactory studyWorkFactory;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TeamConfigUtils teamConfigUtils;
    @Autowired
    SingleWorkFactory singleWorkFactory;

    @Override
    public List<SingleWorkDto> getSingleResponse(User user) {
        List<SingleWork> singleWorks = workMapper.getSingleWorkByStudent(user.getUserId());
        return singleWorkFactory.getList(singleWorks);
    }


    @Override
    @Transactional
    public String createStudyWork(StudyWork studyWork, User user) throws ErrorException {
        if (!studyWork.checkValue()) {
            throw new ParameterException("非完整学习任务属性上传");
        }
        studyWork.setWorkId((int) snowFlakeUtils.nextId());
        Integer integer = workMapper.newStudyWork(studyWork);
        if (integer != 1)
            throw new ServerException("学习任务创建异常，数据库语句返回值" + integer);
        if (studyWork.getReleaseTime().before(new Date())) {
            releaseStudyWork(studyWork, user);
            return "创建并发布成功";
        }
        return "创建成功,等待发布";
    }

    @Override
    @Transactional
    public String modifyStudyWork(StudyWork studyWork, User user) throws ErrorException {
        if (!studyWork.checkValue()) {
            throw new ParameterException("非完整学习任务属性上传");
        }
        StudyWork old = workMapper.getStudyWork(studyWork.getWorkId());
        if (old.getStatus() == 1 && studyWork.getStatus() == 0)
            throw new ParameterException("不能把已发布的学习任务设置成未发布");
        Integer integer = workMapper.modifyStudyWork(studyWork);
        if (integer != 1)
            throw new ServerException("学习任务修改异常，数据库语句返回值" + integer);
        if (old.getStatus() == 0 && studyWork.getStatus() == 1) {
            releaseStudyWork(studyWork, user);
            return "修改并发布成功";
        }
        return "修改成功";
    }

    @Override
    public StudyWorkDto getStudyWorkConcrete(Integer id) {
        StudyWork studyWork = workMapper.getStudyWork(id);
        return studyWorkFactory.getOne(studyWork);
    }

    @Override
    public List<StudyWork> getStudyWorkAll() {
        return workMapper.getStudyWorkAll();
    }

    @Override
    @Scheduled(cron = "* * 0/2 * * ?")
    public void scanWorkRelease() {
        List<StudyWork> studyWorKsToRelease = workMapper.getStudyWorKsToRelease();
        for (StudyWork studyWork : studyWorKsToRelease) {
            studyWork.setStatus(1);
            try {
                releaseStudyWork(studyWork);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Scheduled(cron = "* * 1/2 * * ?")
    public void scanWorkEnd() {
        List<StudyWork> worksToEnd = workMapper.getStudyWorKsToEnd();
        for (StudyWork studyWork : worksToEnd) {
            studyWork.setStatus(2);
            try {
                endStudyWork(studyWork);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public List<TeamWorkDto> getTeamResponse(User user) {
        Team teamFormUser = teamMapper.getTeamOne(user.getUserTeam());
        List<TeamWork> teamWorkByTeam = workMapper.getTeamWorkByTeam(user.getUserTeam());
        List<TeamWorkDto> teamWorkDtoList = teamWorkFactory.getList(teamWorkByTeam);
        return teamWorkDtoList
                .stream().filter(e -> user.getUserId().equals(teamConfigUtils.getPublisherId(teamFormUser, e.getStudyWork())))
                .collect(Collectors.toList());
    }

    private void releaseStudyWork(StudyWork studyWork, User user) throws ErrorException {
        if (!notificationCreateUtils.createNotification(studyWork, user.getUserId()))
            throw new ServerException("学习任务通知创建失败");
        createTeamWork(studyWork);
    }

    private void releaseStudyWork(StudyWork studyWork) throws ErrorException {
        if (!notificationCreateUtils.createNotification(studyWork, userMapper.getTeacher()))
            throw new ServerException("学习任务通知创建失败");
        createTeamWork(studyWork);
    }

    private void endStudyWork(StudyWork studyWork) throws DataException, ServerException {
        if (!notificationCreateUtils.createNotification(studyWork, userMapper.getTeacher()))
            throw new ServerException("学习任务通知创建失败");
    }

    private void createTeamWork(StudyWork studyWork) throws ServerException {
        List<Integer> teamIdAll = teamMapper.getTeamIdAll();
        for (Integer teamId : teamIdAll) {
            TeamWork teamWork = new TeamWork();
            teamWork.setTeamWorkId((int) snowFlakeUtils.nextId());
            teamWork.setBelongTeam(teamId);
            teamWork.setBelongWork(studyWork.getWorkId());
            teamWork.setStatus(0);
            if (1 != workMapper.newTeamWork(teamWork))
                throw new ServerException("队伍学习任务创建失败");
        }
    }

    @Override
    public String createSingleWork(SingleWork singleWork, User user) throws AuthorityException, ServerException, ParameterException {
        if (!singleWork.checkValue()) {
            throw new ParameterException("不完整的个人任务创建");
        }
        Integer studyWork = workMapper.getTeamWork(singleWork.getBelongWork()).getBelongWork();
        if (user.getUserId().equals(teamConfigUtils.getPublisherId(user.getUserTeam(),workMapper.getStudyWork(studyWork)))) {
            singleWork.setSingleWorkId((int) snowFlakeUtils.nextId());
            if (workMapper.newSingleWork(singleWork)==1)
                return "创建成功";
            throw new ServerException("个人任务创建异常");
        }else
            throw new AuthorityException("你不是该团队任务福怎忍");
    }

    @Override
    public String modifySingleWork(SingleWork singleWork, Integer type,User user) throws ErrorException{
        if (!singleWork.checkValue()) {
            throw new ParameterException("不完整的个人任务修改");
        }
        SingleWork old = workMapper.getSingleWork(singleWork.getSingleWorkId());
        if (old == null) {
            throw new ParameterException("错误的个人任务id");
        }
        Integer i;
        switch (type) {
            case 1:
                if (!Objects.equals(teamConfigUtils.getPublisherId(old), user.getUserId()))
                    throw new AuthorityException("无权修改此个人任务");
                i = workMapper.updateSingleWorkDescription(singleWork.getSingleWorkId(),singleWork.getWorkDescription());
                if (i==1)
                    return "修改成功";
                break;
            case 2:
                if (!Objects.equals(old.getBelongStudent(), user.getUserId()))
                    throw new AuthorityException("无权修改此个人任务");
                i = workMapper.updateSingleWorkProSave(singleWork.getSingleWorkId(),singleWork.getProductionRoute());
                if (i==1)
                    return "修改并暂存成功";
                break;
            case 3:
                if (!Objects.equals(old.getBelongStudent(), user.getUserId()))
                    throw new AuthorityException("无权修改此个人任务");
                i = workMapper.updateSingleWorkProCommit(singleWork.getSingleWorkId(),singleWork.getProductionRoute());
                if (i==1) {
                    if (notificationCreateUtils.createNotification(singleWork, teamConfigUtils.getPublisherId(user.getUserTeam(),old)))
                        return "修改并提交成功";
                    else
                        return "通知创建失败";
                }

                break;
            default:
                throw new ParameterException("错误的功能选择");
        }
        throw new ServerException("服务器运行错误");
    }

    @Override
    public String modifyTeamWork(TeamWork teamWork, Integer type, User user) throws ErrorException {
        if (!teamWork.checkValue())
            throw new ParameterException("不完整的团队任务修改");
        if (!user.getUserId().equals(teamConfigUtils.getPublisherId(teamWork)))
            throw new AuthorityException("无权修改此团队任务");
        TeamWork old = workMapper.getTeamWork(teamWork.getTeamWorkId());
        if (old == null) {
            throw new ParameterException("错误的团队任务id");
        }
        Integer integer;
        switch (type) {
            case 1:
                teamWork.setStatus(0);
                integer = workMapper.updateTeamWork(teamWork);
                if (integer==1)
                    return "修改暂存成功";
                break;
            case 2:
                teamWork.setStatus(1);
                integer = workMapper.updateTeamWork(teamWork);
                if (integer==1) {
                    notificationCreateUtils.createNotification(teamWork, userMapper.getTeacher());
                    workMapper.newComment(teamWork.getTeamWorkId());
                    return "修改发布成功";
                }
                break;
            default:
                throw new ParameterException("错误的功能选择");
        }
        throw new ServerException("服务器运行错误");
    }

    @Override
    public String modifyComment(Comment comment, Integer type) throws ErrorException {
        if (!comment.checkValue())
            throw new ParameterException("不完整的任务评分修改");
        if (workMapper.getComment(comment.getBelongTeamWork())==null)
            throw new ParameterException("错误的任务评分id");
        Integer integer;
        switch (type) {
            case 1:
                comment.setStatus(0);
                integer = workMapper.updateComment(comment);
                if (integer==1)
                    return "修改暂存成功";
                break;
            case 2:
                comment.setStatus(1);
                integer = workMapper.updateComment(comment);
                if (integer==1)
                    return "修改发布成功";
                break;
            default:
                throw new ParameterException("错误的功能选择");
        }
        throw new ServerException("服务器运行错误");
    }

    @Override
    public Comment getCommentedTeamwork(Integer TeamworkId) throws ParameterException {
        Comment comment=workMapper.getComment(TeamworkId);
        if(comment == null)
            throw new ParameterException("错误的团队任务ID");
        else return comment;
    }




}
