package com.guest.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guest.core.Response;
import com.guest.pojo.po.Facilities;
import com.guest.service.IFacilitiesService;
import com.guest.service.impl.FacilitiesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2022-04-05
 */
@RestController
@RequestMapping("/facilities")
public class FacilitiesController {

    @Autowired
    private IFacilitiesService facilitiesService;

    @RequestMapping("/getFacilities")
    public Response getFacilities() {
        Map<String, Object> facilities = facilitiesService.getFacilities();
        return Response.success(facilities);
    }

    @GetMapping("/updateFacilities")
    public Response updateFacilities(String id, String person, String time, String status) {
        if (facilitiesService.updateFacilities(id, person, time, status) > 0) {
            return Response.success(null);
        }
        return Response.fail("更新失败");
    }

    @PostMapping("/getData")
    public Response getData(@RequestBody Facilities facilities){
        QueryWrapper<Facilities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",facilities.getName());
        Facilities one = facilitiesService.getOne(queryWrapper);
        return Response.success(one);
    }

    @PostMapping("/currentAdd")
    public Response currentAdd(@RequestBody Facilities facilities){
        QueryWrapper<Facilities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",facilities.getName());
        Facilities one = facilitiesService.getOne(queryWrapper);
        if (one!= null && one.getCurrent() < one.getTotal()){
            one.setCurrent(one.getCurrent() + 1);
            facilitiesService.updateById(one);
        }
        return Response.success();
    }

    @PostMapping("/currentSub")
    public Response currentSub(@RequestBody Facilities facilities){
        QueryWrapper<Facilities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",facilities.getName());
        Facilities one = facilitiesService.getOne(queryWrapper);
        if (one!= null && one.getCurrent() > 0){
            one.setCurrent(one.getCurrent() - 1);
            facilitiesService.updateById(one);
        }
        return Response.success();
    }

}
