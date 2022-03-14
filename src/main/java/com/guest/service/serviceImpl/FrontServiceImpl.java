package com.guest.service.serviceImpl;

import com.guest.pojo.po.Front;
import com.guest.mapper.FrontMapper;
import com.guest.service.FrontService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chuanguo.cao
 * @since 2022-03-02
 */
@Service
public class FrontServiceImpl extends ServiceImpl<FrontMapper, Front> implements FrontService {
    @Autowired
    private FrontMapper frontMapper;

    @Override
    public int DeleteFronts(String[] frontId) {
        return frontMapper.DeleteFronts(frontId);
    }
}
