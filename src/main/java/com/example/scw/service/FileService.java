package com.example.scw.service;

import com.example.scw.pojo.exception.ParameterException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    int STUDY_WORK_RESOURCE = 0;
    int TEAM_WORK_RESOURCE = 1;
    int SINGLE_WORK_RESOURCE = 2;
    int TEAM_WORK_PRODUCTION = 3;
    int SINGLE_WORK_PRODUCTION = 4;

    String workResourceUpload(MultipartFile multipartFile, Integer object) throws ParameterException, IOException;

    String storeResourceUpload(MultipartFile multipartFile);

}
