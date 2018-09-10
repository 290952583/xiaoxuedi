package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.BusinessUsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.business.ListOutput;
import com.xiaoxuedi.repository.BusinessUsersRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static com.xiaoxuedi.model.Output.output;

@Service
@Transactional
public class BusinessService {

    @Resource
    private BusinessUsersRepository businessUsersRepository;

    /**
     * 查询所有商户信息列表（无分页）
     *
     * @return
     */
    public Output<List<ListOutput>> list(String schoolId) {
        List<BusinessUsersEntity> commoditys = businessUsersRepository.findAllBySchoolId(schoolId);
        List<ListOutput> outputs = new ListOutput().fromEntityList(commoditys);
        return output(outputs);
    }

    /**
     * 查询单个商家
     *
     * @return
     */
    public Output<BusinessUsersEntity> item(String businessId) {
        return output(businessUsersRepository.findAllById(businessId));
    }
}
