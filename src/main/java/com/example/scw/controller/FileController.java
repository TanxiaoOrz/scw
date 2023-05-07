package com.example.scw.controller;

import com.example.scw.pojo.exception.ParameterException;
import com.example.scw.pojo.vo.Vo;
import com.example.scw.service.impl.FileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("/cse/File")
@Api(tags = "文件的上传接口")
@CrossOrigin
public class FileController {

    @Autowired
    FileServiceImpl fileService;

    @PostMapping(value = "/work/resource/{type}")
    @ApiOperation(value = "图片或图片上传", notes = "图片或图片上传,返回填充在resourceRoute的访问url")
    // 此处的@RequestParam中的file名应与前端upload组件中的name的值保持一致
    @ApiImplicitParam(value = "对应的任务选项，0是学习任务附件，" +
                    "1是团队任务附件，2是个人任务附件，3是团队任务成品，4是个人任务成品"
                    ,name = "type",required = true,
                    dataTypeClass = Integer.class,paramType = "path")
    public Vo<String> upload(@RequestPart("file") MultipartFile multipartFile, @PathVariable Integer type) throws ParameterException, IOException {
        return new Vo<>(fileService.workResourceUpload(multipartFile,type));
    }

}