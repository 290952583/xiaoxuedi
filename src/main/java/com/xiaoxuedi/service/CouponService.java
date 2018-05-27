package com.xiaoxuedi.service;

import static com.xiaoxuedi.model.Output.output;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.xiaoxuedi.entity.CouponEntity;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.model.Output;
import com.xiaoxuedi.model.coupon.ListOutput;
import com.xiaoxuedi.repository.CouponRepository;

@Service
@Transactional
public class CouponService {
	
	
	@Resource
	private CouponRepository couponRepository;
	
	
	/**
	 * 查询红包列表
	 * @return
	 */
	 public Output<List<ListOutput>> list()
	    {
	        List<CouponEntity> coupon = couponRepository.findAllByUser(UsersEntity.getUser());
	        List<ListOutput> outputs = new ListOutput().fromEntityList(coupon);
	        return output(outputs);
	    }

}
