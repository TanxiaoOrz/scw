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

    @Value("${server.port}")
    String port;

    String absolutePath = System.getProperty("user.dir") + "/static/";

    @Value("${file.type-path}")
    String[] typePath;

    @Override
    public String workResourceUpload(MultipartFile multipartFile, Integer object) throws ParameterException, IOException {
        if (object>4||object<0)
            throw new ParameterException("错误的上传类型选择");
        File dir = new File(absolutePath+typePath[object]);
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dir,multipartFile.getOriginalFilename());
        int i = 0;
        while(file.exists()){
            i++;
            String[] split = multipartFile.getOriginalFilename().split("\\.");
            StringBuilder name=new StringBuilder();
            String style=split[split.length-1];
            for (int j=0;j<split.length-1;j++)
                name.append(split[j]);
            file = new File(dir,name+"(" +i+")"+style);
        }
        file.createNewFile();
        multipartFile.transferTo(file);
        return requestIP+":"+port+"/static/"+typePath[object]+"/"+file.getName();
    }

    @Override
    public String storeResourceUpload(MultipartFile multipartFile) {
        return null;
    }
}
