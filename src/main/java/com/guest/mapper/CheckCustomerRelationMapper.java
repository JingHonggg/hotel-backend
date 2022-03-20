package com.guest.mapper;

import com.guest.pojo.po.CheckCustomerRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guest.pojo.po.Customer;

import java.util.List;

/**
 * <p>
 * 入住客人与入住信息对应表 Mapper 接口
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
public interface CheckCustomerRelationMapper extends BaseMapper<CheckCustomerRelation> {

    List<Customer> getRelationCustomer(Long CheckInId);
}
