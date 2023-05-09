package com.example.scw.utils.factory;


import com.example.scw.mapper.WorkMapper;
import com.example.scw.pojo.dto.SingleWorkDto;
import com.example.scw.pojo.entity.SingleWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SingleWorkFactory implements Factory<SingleWorkDto, SingleWork> {

    @Autowired
    WorkMapper workMapper;

    @Override
    public SingleWorkDto getOne(SingleWork singleWork) {
        SingleWorkDto singleWorkDto = new SingleWorkDto(singleWork);
        singleWorkDto.setTeamWork(workMapper.getTeamWork(singleWork.getBelongWork()));
        singleWorkDto.setStudyWork(workMapper.getStudyWork(singleWorkDto.getTeamWork().getBelongWork()));
        return singleWorkDto;
    }

    @Override
    public List<SingleWorkDto> getList(List<SingleWork> singleWorks) {
        ArrayList<SingleWorkDto> singleWorkDtos = new ArrayList<>(singleWorks.size());
        singleWorks.forEach(singleWork -> singleWorkDtos.add(getOne(singleWork)));
        return singleWorkDtos;
    }
}
