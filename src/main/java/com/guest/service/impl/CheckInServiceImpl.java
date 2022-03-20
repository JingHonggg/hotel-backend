package com.guest.service.impl;

import com.guest.pojo.po.CheckIn;
import com.guest.mapper.CheckInMapper;
import com.guest.service.ICheckInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入住信息表 服务实现类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Service
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements ICheckInService {

}
