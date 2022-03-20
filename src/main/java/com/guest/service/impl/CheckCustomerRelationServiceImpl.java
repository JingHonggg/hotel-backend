package com.guest.service.impl;

import com.guest.pojo.po.CheckCustomerRelation;
import com.guest.mapper.CheckCustomerRelationMapper;
import com.guest.pojo.po.Customer;
import com.guest.service.ICheckCustomerRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 入住客人与入住信息对应表 服务实现类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Service
public class CheckCustomerRelationServiceImpl extends ServiceImpl<CheckCustomerRelationMapper, CheckCustomerRelation> implements ICheckCustomerRelationService {

    @Override
    public List<Customer> getRelationCustomer(Long checkInId) {
        return baseMapper.getRelationCustomer(checkInId);
    }
}
