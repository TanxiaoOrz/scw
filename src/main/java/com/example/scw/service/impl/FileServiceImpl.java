package com.example.scw.service.impl;

import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.request-ip}")
    String requestIP;

    String absolutePath = System.getProperty("user.dir") + "/static/";

    @Value("${file.type-path}")
    List<String> typePath;

    @Override
    public String workResourceUpload(MultipartFile multipartFile, Integer object) throws ParameterException, IOException {
        if (object>4||object<0)
            throw new ParameterException("错误的上传类型选择");
        File dir = new File(absolutePath+typePath.get(object));
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dir,multipartFile.getOriginalFilename());
        int i = 0;
        while(file.exists()){
            file = new File(dir,multipartFile.getOriginalFilename()+ Integer.toString(i));
        }
        file.createNewFile();
        multipartFile.transferTo(file);
        return requestIP+typePath.get(object)+file.getName();
    }

    @Override
    public String storeResourceUpload(MultipartFile multipartFile) {
        return null;
    }
}
