package com.guest.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guest.mapper.StaffMapper;
import com.guest.pojo.dto.StaffParam;
import com.guest.pojo.po.Staff;
import com.guest.service.IStaffService;
import org.springframework.stereotype.Service;

/**
 * @author: Chuanguo.Cao
 * @date: 2022-04-05 19:51
 **/
@Service
public class IStaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Override
    public Page<Staff> search(Page<Staff> page, StaffParam param) {
        param.setCurrent( (param.getCurrent()-1) * param.getSize());
        return baseMapper.search(page,param);
    }
}