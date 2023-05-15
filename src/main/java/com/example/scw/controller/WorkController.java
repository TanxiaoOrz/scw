package com.example.scw.controller;

import com.example.scw.pojo.dto.SingleWorkDto;
import com.example.scw.pojo.dto.StudyWorkDto;
import com.example.scw.pojo.dto.TeamWorkDto;
import com.example.scw.pojo.entity.Comment;
import com.example.scw.pojo.entity.SingleWork;
import com.example.scw.pojo.entity.StudyWork;
import com.example.scw.pojo.entity.TeamWork;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.AuthorityException;
import com.example.scw.pojo.exception.ErrorException;
import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.pojo.exception.ServerException;
import com.example.scw.pojo.vo.Vo;
import com.example.scw.service.WorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/work")
@Api(tags = "学习任务的操作接口")
public class WorkController {

    @Autowired
    WorkService workService;

    @PostMapping("/study/create")
    @ApiOperation(value = "教师创建学习任务的接口，创建时id设置为0即可")
    @ApiImplicitParam(name = "studyWork", value = "学习任务结构体", required = true, dataTypeClass = StudyWork.class, paramType = "body")
    public Vo<String> createStudyWork(@RequestBody StudyWork studyWork, HttpServletRequest request)
            throws ErrorException {
        User user = (User) request.getAttribute("User");
        return new Vo<>(workService.createStudyWork(studyWork, user));
    }

    @PostMapping("/study/modify")
    @ApiOperation(value = "教师修改学习任务的接口")
    @ApiImplicitParam(name = "studyWork", value = "学习任务结构体", required = true, dataTypeClass = StudyWork.class, paramType = "body")
    public Vo<String> modifyStudyWork(@RequestBody StudyWork studyWork, HttpServletRequest request)
            throws ErrorException {
        User user = (User) request.getAttribute("User");
        return new Vo<>(workService.modifyStudyWork(studyWork, user));
    }

    @GetMapping("/study/concrete/{id}")
    @ApiOperation(value = "教师具体查看学习任务的接口")
    @ApiImplicitParam(name = "id", value = "学习任务id", required = true, dataTypeClass = Integer.class, paramType = "path")
    public Vo<StudyWorkDto> getStudyWorkConcrete(@PathVariable Integer id) {
        return new Vo<>(workService.getStudyWorkConcrete(id));
    }

    @GetMapping("/study/all")
    @ApiOperation(value = "查看所有学习任务的接口")
    public Vo<List<StudyWork>> getStudyWorkAll() {
        return new Vo<>(workService.getStudyWorkAll());
    }

    @GetMapping("/team/response")
    @ApiOperation(value = "获取学生负责的团队任务")
    public Vo<List<TeamWorkDto>> getTeamResponse(@RequestAttribute(name = "User") @ApiIgnore User user) {
        List<TeamWorkDto> teamResponse = workService.getTeamResponse(user);
        return new Vo<>(teamResponse);
    }

    @GetMapping("/single/response")
    @ApiOperation(value = "获取学生负责的个人任务")
    public Vo<List<SingleWorkDto>> getSingleResponse(@RequestAttribute(name = "User") @ApiIgnore User user) {
        return new Vo<>(workService.getSingleResponse(user));
    }

    @PostMapping("/single/create")
    @ApiOperation(value = "创建指定成员的个人任务")
    @ApiImplicitParam(name = "singleWork", value = "学习任务结构体,创建时不需要主键id，设为0即可", required = true, dataTypeClass = SingleWork.class, paramType = "body")
    public Vo<String> createSingleWork(@RequestBody SingleWork singleWork,@RequestAttribute(name = "User") @ApiIgnore User user) throws ServerException, AuthorityException, ParameterException {
        return new Vo<>(workService.createSingleWork(singleWork,user));
    }

    @PostMapping("/single/modify/{type}")
    @ApiOperation(value = "个人任务修改", notes = "团队负责人修改描述或是个人完成提交成果暂存或上交")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "singleWork", value = "学习任务结构体", required = true, dataTypeClass = SingleWork.class, paramType = "body"),
            @ApiImplicitParam(name = "type", value = "类型选择1代表负责人修改描述，2代表修改成果路径暂存，3代表修改成果路径并提交", required = true, dataTypeClass = Integer.class, paramType = "path")
    })
    public Vo<String> modifySingleWork(@RequestBody SingleWork singleWork, @PathVariable Integer type
                ,@RequestAttribute(name = "User") @ApiIgnore User user) throws ErrorException {
        return new Vo<>(workService.modifySingleWork(singleWork, type, user));
    }

    @PostMapping("/team/modify/{type}")
    @ApiOperation(value = "团队任务修改", notes = "生成描述，修改成果，上交等待审阅")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teamWork", value = "学习任务结构体", required = true, dataTypeClass = TeamWork.class, paramType = "body"),
            @ApiImplicitParam(name = "type", value = "类型选择1代表负责人修改描述和成果路径暂存，2代表负责人修改描述和成果路径提交", required = true, dataTypeClass = Integer.class, paramType = "path")
    })
    public Vo<String> modifyTeamWork(@RequestBody TeamWork teamWork, @PathVariable Integer type
            ,@RequestAttribute(name = "User") @ApiIgnore User user) throws ErrorException {
        return new Vo<>(workService.modifyTeamWork(teamWork, type, user));
    }



    @PostMapping("/comment/{type}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "comment", value = "学习任务结构体", required = true, dataTypeClass = Comment.class, paramType = "body"),
            @ApiImplicitParam(name = "type", value = "类型选择1代表负责人修改描述和成果路径暂存，2代表负责人修改描述和成果路径提交", required = true, dataTypeClass = Integer.class, paramType = "path")
    })
    @ApiOperation(value = "教师批阅提交的团队任务", notes = "生成描述，添加分数，完成批注")
    public Vo<String> modifyComment(@RequestBody Comment comment, @PathVariable Integer type) throws ErrorException {
        return new Vo<>(workService.modifyComment(comment, type));
    }


    @GetMapping("/comment/{teamworkID}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teamworkID", value = "TeamWork的ID", required = true, dataTypeClass = Integer.class, paramType = "path")
    })
    @ApiOperation(value = "查看教师已批阅的团队任务", notes = "查看已批的任务")
    public Vo<Comment> getComment(@PathVariable Integer teamworkID) throws ParameterException {
        return new Vo<>(workService.getCommentedTeamwork(teamworkID));
    }
}
