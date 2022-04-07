package com.guest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guest.mapper.FacilitiesMapper;
import com.guest.pojo.po.Facilities;
import com.guest.service.IFacilitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2022-04-05
 */
@Service
public class FacilitiesServiceImpl extends ServiceImpl<FacilitiesMapper, Facilities> implements IFacilitiesService {

    @Autowired
    private FacilitiesMapper facilitiesMapper;
    QueryWrapper<Facilities> queryWrapper = new QueryWrapper<>();

    @Override
    public Map<String, Object> getFacilities() {
        Map<String, Object> map = new HashMap<>();
        List<Facilities> facilities = facilitiesMapper.selectList(queryWrapper);
        map.put("sangna",facilities.get(0));
        map.put("jiuba",facilities.get(1));
        map.put("canting",facilities.get(2));
        return map;
    }

    @Override
    public int updateFacilities(String id,String person,String Time,String status) {
        int i = facilitiesMapper.updateFacilities(Integer.parseInt(id), person, Time,Integer.parseInt(status) );
        return i;
    }
}
