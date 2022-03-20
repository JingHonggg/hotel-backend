package com.guest.service.impl;

import com.guest.pojo.po.Front;
import com.guest.mapper.FrontMapper;
import com.guest.service.IFrontService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 前台管理员表 服务实现类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Service
public class FrontServiceImpl extends ServiceImpl<FrontMapper, Front> implements IFrontService {

    @Override
    public int DeleteFronts(String[] frontId) {
        return baseMapper.DeleteFronts(frontId);
    }
}
