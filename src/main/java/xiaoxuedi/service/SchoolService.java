package xiaoxuedi.service;

import xiaoxuedi.entity.School;
import xiaoxuedi.model.Output;
import xiaoxuedi.model.school.AddInput;
import xiaoxuedi.model.school.ListOutput;
import xiaoxuedi.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static xiaoxuedi.model.Output.output;
import static xiaoxuedi.model.Output.outputOk;

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
        List<School> schools = schoolRepository.findAll();
        List<ListOutput> outputs = new ListOutput().fromEntityList(schools);
        return output(outputs);
    }
}
