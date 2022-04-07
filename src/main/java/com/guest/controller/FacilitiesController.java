package com.guest.controller;


import com.guest.core.Response;
import com.guest.pojo.po.Facilities;
import com.guest.service.IFacilitiesService;
import com.guest.service.impl.FacilitiesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
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
    public Response getFacilities(){
        Map<String, Object> facilities = facilitiesService.getFacilities();
        return Response.success(facilities);
    }
    @GetMapping("/updateFacilities")
    public Response updateFacilities(String id,String person,String time,String status){
      if (facilitiesService.updateFacilities(id, person, time, status)>0){
          return Response.success(null);
      }
        return Response.fail("更新失败");
    }

}
