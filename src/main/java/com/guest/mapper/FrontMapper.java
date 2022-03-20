package com.guest.mapper;

import com.guest.pojo.po.Front;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 前台管理员表 Mapper 接口
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
public interface FrontMapper extends BaseMapper<Front> {

    int DeleteFronts(@Param("frontId") String[] frontId);
}
