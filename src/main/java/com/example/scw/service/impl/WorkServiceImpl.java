package com.example.scw.service.impl;

import com.example.scw.mapper.TeamMapper;
import com.example.scw.mapper.WorkMapper;
import com.example.scw.pojo.dto.StudyWorkDto;
import com.example.scw.pojo.entity.StudyWork;
import com.example.scw.pojo.entity.TeamWork;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.ErrorException;
import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.pojo.exception.ServerException;
import com.example.scw.service.WorkService;
import com.example.scw.utils.NotificationCreateUtils;
import com.example.scw.utils.SnowFlakeUtils;
import com.example.scw.utils.factory.StudyWorkFactory;
import com.example.scw.utils.factory.TeamWorkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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


    @Override
    @Transactional
    public String createStudyWork(StudyWork studyWork, User user) throws ErrorException {
        if (!studyWork.checkValue()) {
            throw new ParameterException("非完整学习任务属性上传");
        }
        studyWork.setWorkId((int) snowFlakeUtils.nextId());
        Integer integer = workMapper.newStudyWork(studyWork);
        if (integer!=1)
            throw new ServerException("学习任务创建异常，数据库语句返回值"+integer);
        if (studyWork.getReleaseTime().before(new Date())) {
            releaseStudyWork(studyWork,user);
            return "创建并发布成功";
        }
        return "创建成功,等待发布";
    }

    @Override
    public String modifyStudyWork(StudyWork studyWork, User user) throws ErrorException {
        if (!studyWork.checkValue()) {
            throw new ParameterException("非完整学习任务属性上传");
        }
        StudyWork old = workMapper.getStudyWork(studyWork.getWorkId());
        if (old.getStatus()==1&&studyWork.getStatus()==0)
            throw new ParameterException("不能把已发布的学习任务设置成未发布");
        Integer integer = workMapper.modifyStudyWork(studyWork);
        if (integer!=1)
            throw new ServerException("学习任务修改异常，数据库语句返回值"+integer);
        if (old.getStatus()==1&&studyWork.getStatus()==0) {
            releaseStudyWork(studyWork,user);
            return "修改并发布成功";
        }
        return "修改成功等待发布";
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

    private void releaseStudyWork(StudyWork studyWork, User user) throws ErrorException {
        if (!notificationCreateUtils.createNotification(studyWork,user.getUserId()))
            throw new ServerException("学习任务通知创建失败");
        List<Integer> teamIdAll = teamMapper.getTeamIdAll();
        for (Integer teamId:
                teamIdAll) {
            TeamWork teamWork = new TeamWork();
            teamWork.setTeamWorkId((int) snowFlakeUtils.nextId());
            teamWork.setBelongTeam(teamId);
            teamWork.setStatus(0);
            if (1!=workMapper.newTeamWork(teamWork))
                throw new ServerException("队伍学习任务创建失败");
        }
    }
}