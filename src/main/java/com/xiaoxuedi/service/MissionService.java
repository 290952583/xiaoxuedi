package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.CouponEntity;
import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.PageInput;
import com.xiaoxuedi.model.mission.*;
import com.xiaoxuedi.repository.CouponRepository;
import com.xiaoxuedi.repository.MissionRepository;
import com.xiaoxuedi.repository.UserRepository;
import com.xiaoxuedi.util.OrderUtil;
import com.xiaoxuedi.util.StringUtil;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static com.xiaoxuedi.model.Output.*;


@Service
@Transactional
public class MissionService
{
    @Resource
    private MissionRepository missionRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private CouponRepository couponRepository;

    
    /**
     * 代取快递任务处理
     * @param input
     * @return
     */
    public Output add(AddInput input)
    {
        
    	MissionEntity mission =input.toEntity();
    	mission.setActualAmount(mission.getPrice());//实际金额
    	mission.setMissionNo(OrderUtil.getOrderIdByUUId());//任务编号，用于支付
    	mission.setCreateTime(new Timestamp(new Date().getTime()));//创建时间
    	//查询是否有红包可以使用
    	if(!StringUtil.isEmpty(input.getCoupon_id())) {
    		CouponEntity coupon = couponRepository.findOne(input.getCoupon_id());
    		//判断是否存在，且，不失效，不过期，满足可用金额
    		if(coupon!=null&&"valid".equals(coupon.getStatus())) {
    			long nowDate = new Date().getTime();
     			long endTime = coupon.getEndTime().getTime();
    			if(endTime>nowDate&&mission.getPrice().compareTo(coupon.getFullAmountReduction())<0) {//可用
    				mission.setCoupon_id(input.getCoupon_id());
    				mission.setCouponAmount(coupon.getAmount());
    				if(mission.getPrice().subtract(coupon.getAmount()).doubleValue()>0d) {
    					mission.setActualAmount(mission.getPrice().subtract(coupon.getAmount()));//实际金额
    				}else {
    					mission.setActualAmount(new BigDecimal(0));//实际金额为0
    				}
    				//删除红包
    				couponRepository.delete(input.getCoupon_id());
    			}
    			   			
    		}
    	}
        mission = missionRepository.save(mission);
        if (mission == null)
        {
            return outputParameterError();
        }
        return output(mission);
    }

    public Output delete(DeleteInput input)
    {
        MissionEntity mission = missionRepository.findOne(input.getId());
        if (mission == null)
        {
            return outputParameterError();
        }
        if (!mission.isBelong())
        {
            return outputNotBelong();
        }
        if (mission.getStatus() != MissionEntity.Status.WAIT)
        {
            return outputMissionStatusError();
        }

        missionRepository.delete(mission);

        return outputOk();
    }

    public Output update(UpdateInput input)
    {
        MissionEntity mission = missionRepository.findOne(input.getId());
        if (mission == null)
        {
            return outputParameterError();
        }
        if (!mission.isBelong())
        {
            return outputNotBelong();
        }
        if (mission.getStatus() != MissionEntity.Status.WAIT)
        {
            return outputMissionStatusError();
        }

        UsersEntity user = mission.getUser();

        input.update(mission);
        missionRepository.save(mission);
        userRepository.save(user);
        return outputOk();
    }

    public Output finish(FinishInput input)
    {
        MissionEntity mission = missionRepository.findOne(input.getId());
        if (mission == null)
        {
            return outputParameterError();
        }
        if (!mission.isBelong())
        {
            return outputNotBelong();
        }
        if (mission.getStatus() != MissionEntity.Status.PROCESSING)
        {
            return outputMissionStatusError();
        }
        mission.setStatus(MissionEntity.Status.FINISH);
        missionRepository.save(mission);

        return outputOk();
    }

    public Output accept(AcceptInput input)
    {
        UsersEntity user = userRepository.getCurrentUser();
        if (!user.isAuth())
        {
            return outputNotAuth();
        }
        MissionEntity mission = missionRepository.findOne(input.getId());
        if (mission == null)
        {
            return outputParameterError();
        }
        if (mission.getStatus() != MissionEntity.Status.WAIT)
        {
            return outputMissionStatusError();
        }
        mission.setStatus(MissionEntity.Status.PROCESSING);
        mission.setUser(user);
//        mission.setAcceptTime(new Timestamp(System.currentTimeMillis()));
        missionRepository.save(mission);
        return outputOk();
    }

    public Output cancel(CancelInput input)
    {
        MissionEntity mission = missionRepository.findOne(input.getId());
        if (mission == null)
        {
            return outputParameterError();
        }
        if (!mission.getUser().getId().equals(UsersEntity.getUserId()))
        {
            return outputNotBelong();
        }
        if (mission.getStatus() != MissionEntity.Status.PROCESSING)
        {
            return outputMissionStatusError();
        }
        mission.setStatus(MissionEntity.Status.CANCEL);
        missionRepository.save(mission);
        return outputOk();
    }

    public Output acceptCancel(AcceptCancelInput input)
    {
        MissionEntity mission = missionRepository.findOne(input.getId());
        if (mission == null)
        {
            return outputParameterError();
        }
        if (!mission.isBelong())
        {
            return outputNotBelong();
        }
        if (mission.getStatus() != MissionEntity.Status.CANCEL)
        {
            return outputMissionStatusError();
        }
        mission.setStatus(MissionEntity.Status.WAIT);
        mission.setUser(null);
//        mission.setAcceptTime(null);
        missionRepository.save(mission);
        return outputOk();
    }

    public Output<List<ListOutput>> myList(StatusesInput input)
    {
        List<MissionEntity> missions = missionRepository.findAllByUserAndStatusIn(UsersEntity.getUser(), input.getStatuses(), input.getPageableSortByTime());
        List<ListOutput> outputs = new ListOutput().fromEntityList(missions);
        return output(outputs);
    }

    public Output<List<ListOutput>> acceptList(StatusesInput input)
    {
        List<MissionEntity> missions = missionRepository.findAllByUserAndStatusIn(UsersEntity.getUser(), input.getStatuses(), input.getPageableSortByTime());
        List<ListOutput> outputs = new ListOutput().fromEntityList(missions);
        return output(outputs);
    }

    public Output<List<ListOutput>> nearby(NearbyInput input)
    {
        SchoolEntity school = new SchoolEntity();
        school.setId(input.getSchoolId());
        MissionEntity.Status[] statuses = {MissionEntity.Status.WAIT, MissionEntity.Status.PROCESSING};
//        List<MissionEntity> missions = missionRepository.findAllByUserSchoolAndStatusIn(school, statuses, input.getPageableSortByTime());
//        List<ListOutput> outputs = new ListOutput().fromEntityList(missions);
        return output(null);
    }
    
    
    /**
     * 查询所有订单列表
     * @param input
     * @return
     */
    public Output<List<ListOutput>> list(PageInput input)
    {
        List<MissionEntity> missions = missionRepository.findAllByUser(UsersEntity.getUser(), input.getPageableSortByTime());
        List<ListOutput> outputs = new ListOutput().fromEntityList(missions);
        return output(outputs);
    }
    
    
}
