package com.xiaoxuedi.service;

import com.xiaoxuedi.entity.MissionEntity;
import com.xiaoxuedi.entity.OrdersEntity;
import com.xiaoxuedi.entity.SchoolEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.mission.*;
import com.xiaoxuedi.repository.MissionRepository;
import com.xiaoxuedi.repository.OrderRepository;
import com.xiaoxuedi.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

import static com.xiaoxuedi.model.Output.*;


@Service
@Transactional
public class MissionService
{
    @Resource
    private MissionRepository missionRepository;
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private UserRepository userRepository;

    public Output add(AddInput input)
    {
        UsersEntity user = userRepository.getCurrentUser();
//        if (user.getBalance() < input.getPrice())
//        {
//            return outputInsufficientBalance();
//        }
//        user.setBalance(user.getBalance() - input.getPrice());
        userRepository.save(user);
        MissionEntity mission = missionRepository.save(input.toEntity());
        if (mission == null)
        {
            return outputParameterError();
        }

        OrdersEntity order = new OrdersEntity();
        orderRepository.save(order);

        return outputOk();
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

        UsersEntity user = mission.getUser();
//        user.setBalance(user.getBalance() + mission.getPrice());
        userRepository.save(user);

        orderRepository.deleteByUserAndMission(user, mission);
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
//        user.setBalance(user.getBalance() + input.getPrice() - mission.getPrice());
//        if (user.getBalance() < 0)
//        {
//            return outputInsufficientBalance();
//        }

        input.update(mission);
        missionRepository.save(mission);
        userRepository.save(user);
        OrdersEntity order = orderRepository.findByUserAndMission(user, mission);
//        order.setAmount(-mission.getPrice());
        orderRepository.save(order);

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

        UsersEntity user = mission.getUser();
        OrdersEntity order = new OrdersEntity();
        orderRepository.save(order);
//        user.setBalance(user.getBalance() + mission.getPrice());
        userRepository.save(user);

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
        List<MissionEntity> missions = missionRepository.findAllByAcceptUserAndStatusIn(UsersEntity.getUser(), input.getStatuses(), input.getPageableSortByTime());
        List<ListOutput> outputs = new ListOutput().fromEntityList(missions);
        return output(outputs);
    }

    public Output<List<ListOutput>> nearby(NearbyInput input)
    {
        SchoolEntity school = new SchoolEntity();
        school.setId(input.getSchoolId());
        MissionEntity.Status[] statuses = {MissionEntity.Status.WAIT, MissionEntity.Status.PROCESSING};
        List<MissionEntity> missions = missionRepository.findAllByUserSchoolAndStatusIn(school, statuses, input.getPageableSortByTime());
        List<ListOutput> outputs = new ListOutput().fromEntityList(missions);
        return output(outputs);
    }
}
