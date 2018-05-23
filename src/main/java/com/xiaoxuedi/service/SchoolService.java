package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.model.school.AddInput;
import com.xiaoxuedi.model.school.ListOutput;
import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.xiaoxuedi.model.Output.output;
import static com.xiaoxuedi.model.Output.outputOk;

@Service
@Transactional
public class SchoolService
{
    @Resource
    private SchoolRepository schoolRepository;

    public Output add(AddInput input)
    {
        schoolRepository.save(input.toEntity());
        return outputOk();
    }

    public Output<List<ListOutput>> list()
    {
        List<SchoolEntity> schools = schoolRepository.findAll();
        List<ListOutput> outputs = new ListOutput().fromEntityList(schools);
        return output(outputs);
    }
}
