package com.guest.mapper;

import com.guest.pojo.po.Front;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chuanguo.cao
 * @since 2022-03-02
 */
@Mapper
@Repository
public interface FrontMapper extends BaseMapper<Front> {
    int DeleteFronts(@Param("frontId") String[] frontId);
}
