package com.guest.service;

import com.guest.pojo.po.CheckCustomerRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guest.pojo.po.Customer;

import java.util.List;

/**
 * <p>
 * 入住客人与入住信息对应表 服务类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
public interface ICheckCustomerRelationService extends IService<CheckCustomerRelation> {

    List<Customer> getRelationCustomer(Long checkInId);
}
