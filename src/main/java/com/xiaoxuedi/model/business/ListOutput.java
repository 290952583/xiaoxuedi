package com.xiaoxuedi.model.business;

import com.xiaoxuedi.entity.BusinessUsersEntity;
import com.xiaoxuedi.model.ModelFromEntityList;
import lombok.Data;

import java.math.BigDecimal;



@Data
public class ListOutput implements ModelFromEntityList<BusinessUsersEntity, ListOutput> {

	private String id;

    private String name;//店铺名称

    private String schoolId;//学校id

    private String address;//店铺地址

    private String headImg;//头像

    private String businessHours;//营业时间

    private String placard;//公告

    private String servicePhone;//客服电话

    private String businessStatus;//营业状态

    private Integer ordersSum;//总订单量

    private BigDecimal startingPrice;//起送价

    private BigDecimal distributionPrice;//配送费

    private String businessLicense;//营业执照

    private String authStatus;//认证状态

    @Override
    public ListOutput fromEntity(BusinessUsersEntity commodity){
    	
    	this.id =commodity.getId();

        this.name =commodity.getName();//店铺名称

        this.schoolId =commodity.getSchoolId();//学校id

        this. address =commodity.getAddress();//店铺地址

        this. headImg =commodity.getHeadImg();//头像

        this.businessHours =commodity.getBusinessHours();//营业时间

        this.placard =commodity.getPlacard();//公告

        this.servicePhone =commodity.getServicePhone();//客服电话

        this.businessStatus =commodity.getBusinessStatus();//营业状态

        this.ordersSum =commodity.getOrdersSum();//总订单量

        this.startingPrice =commodity.getStartingPrice();//起送价

        this.distributionPrice =commodity.getDistributionPrice();//配送费

        this.businessLicense =commodity.getBusinessLicense();//营业执照

        this.authStatus =commodity.getAuthStatus();//认证状态
        return this;

    }

}
