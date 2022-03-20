package com.guest.service;

import com.guest.pojo.po.Front;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 前台管理员表 服务类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
public interface IFrontService extends IService<Front> {

    int DeleteFronts(String[] frontId);
}
