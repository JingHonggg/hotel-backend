package com.guest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guest.pojo.po.Facilities;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-05
 */
@Repository
public interface FacilitiesMapper extends BaseMapper<Facilities> {

    int updateFacilities(@Param("id") int id,@Param("person") String person,@Param("time") String time,@Param("status") int status);
}
