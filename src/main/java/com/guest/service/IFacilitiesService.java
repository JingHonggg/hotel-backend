package com.guest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guest.pojo.po.Facilities;

import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author
 * @since 2022-04-05
 */
public interface IFacilitiesService extends IService<Facilities> {
    Map<String, Object> getFacilities();

    int updateFacilities(String id,String person,String Time,String status);
}
