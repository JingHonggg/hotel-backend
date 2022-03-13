package com.guest.service;

import com.guest.pojo.po.Front;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chuanguo.cao
 * @since 2022-03-02
 */
@Service
public interface FrontService extends IService<Front> {
    int DeleteFronts(String[] frontId);
}
