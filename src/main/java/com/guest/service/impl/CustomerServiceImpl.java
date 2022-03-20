package com.guest.service.impl;

import com.guest.pojo.po.Customer;
import com.guest.mapper.CustomerMapper;
import com.guest.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入住客人表 服务实现类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {

}
