package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.SchoolRegionEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.school.AddRegionInput;
import com.xiaoxuedi.model.school.ListRegionOutput;
import com.xiaoxuedi.repository.SchoolRegionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.xiaoxuedi.model.Output.*;

@Service
@Transactional
public class SchoolRegionService
{

    @Resource
    private SchoolRegionRepository schoolRegionRepository;

    public Output add(AddRegionInput input)
    {
        schoolRegionRepository.save(input.toEntity());
        return outputOk();
    }

    public Output update(AddRegionInput input)
    {
        SchoolRegionEntity schoolRegion = schoolRegionRepository.findOne(input.getId());
        if (schoolRegion == null)
        {
            return outputParameterError();
        }

        input.update(schoolRegion);
        schoolRegionRepository.save(schoolRegion);
        return outputOk();
    }

    public Output<List<ListRegionOutput>> list(String schoolId)
    {
        List<SchoolRegionEntity> schoolRegions = schoolRegionRepository.findBySchoolId(schoolId);
        List<ListRegionOutput> outputs = new ListRegionOutput().fromEntityList(schoolRegions);
        return output(outputs);
    }

}
