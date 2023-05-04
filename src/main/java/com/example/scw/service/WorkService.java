package com.example.scw.service;

import com.example.scw.pojo.dto.StudyWorkDto;
import com.example.scw.pojo.entity.StudyWork;
import com.example.scw.pojo.entity.User;
import com.example.scw.pojo.exception.DataException;
import com.example.scw.pojo.exception.ErrorException;
import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.pojo.exception.ServerException;

import java.util.List;

public interface WorkService {
    String createStudyWork(StudyWork studyWork, User user) throws ErrorException;

    String modifyStudyWork(StudyWork studyWork, User user) throws ErrorException;

    StudyWorkDto getStudyWorkConcrete(Integer id);
    List<StudyWork> getStudyWorkAll();
}
