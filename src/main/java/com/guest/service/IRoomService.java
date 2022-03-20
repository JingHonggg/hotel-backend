package com.guest.service;

import com.guest.pojo.po.Room;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 房间表 服务类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
public interface IRoomService extends IService<Room> {

    int batchDelete(String[] Ids);
}
