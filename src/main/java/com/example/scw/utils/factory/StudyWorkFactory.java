package com.example.scw.utils.factory;

import com.example.scw.mapper.WorkMapper;
import com.example.scw.pojo.dto.StudyWorkDto;
import com.example.scw.pojo.entity.StudyWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudyWorkFactory implements Factory<StudyWorkDto, StudyWork>{

    @Autowired
    WorkMapper workMapper;

    @Override
    public StudyWorkDto getOne(StudyWork studyWork) {
        StudyWorkDto studyWorkDto = new StudyWorkDto(studyWork);
        studyWorkDto.setTeamWorks(workMapper.getTeamWorkByStudy(studyWork.getWorkId()));
        return studyWorkDto;
    }

    @Override
    public List<StudyWorkDto> getList(List<StudyWork> studyWorks) {
        ArrayList<StudyWorkDto> studyWorkDtos = new ArrayList<>(studyWorks.size());
        studyWorks.forEach(e->studyWorkDtos.add(getOne(e)));
        return studyWorkDtos;
    }
}
