package com.example.scw.service;

import com.example.scw.pojo.dto.SingleWorkDto;
import com.example.scw.pojo.dto.StudyWorkDto;
import com.example.scw.pojo.dto.TeamWorkDto;
import com.example.scw.pojo.entity.*;
import com.example.scw.pojo.exception.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

public interface WorkService {
    String createStudyWork(StudyWork studyWork, User user) throws ErrorException;

    String modifyStudyWork(StudyWork studyWork, User user) throws ErrorException;

    StudyWorkDto getStudyWorkConcrete(Integer id);
    List<StudyWork> getStudyWorkAll();


    List<TeamWorkDto> getTeamResponse(User user);
    List<SingleWorkDto> getSingleResponse(User user);
    String createSingleWork(SingleWork singleWork,User user) throws AuthorityException, ServerException, ParameterException;

    String modifySingleWork(SingleWork singleWork, Integer type,User user) throws ErrorException;

    String modifyTeamWork(TeamWork teamWork, Integer type, User user) throws ErrorException;

    String modifyComment( Comment comment,  Integer type) throws ErrorException;

    void scanWorkRelease();
    void scanWorkEnd();

}
