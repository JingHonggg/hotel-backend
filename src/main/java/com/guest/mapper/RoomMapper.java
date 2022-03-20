package com.guest.mapper;

import com.guest.pojo.po.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 房间表 Mapper 接口
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
public interface RoomMapper extends BaseMapper<Room> {

    int batchDelete(@Param("Ids")String[] Ids);
}
