package com.guest.service.impl;

import com.guest.pojo.po.Room;
import com.guest.mapper.RoomMapper;
import com.guest.service.IRoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 房间表 服务实现类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Service
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements IRoomService {

    @Override
    public int batchDelete(String[] Ids) {
        return baseMapper.batchDelete(Ids);
    }
}
