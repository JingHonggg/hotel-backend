package com.guest.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guest.core.Response;
import com.guest.core.ResponseMsg;
import com.guest.pojo.po.CheckCustomerRelation;
import com.guest.pojo.po.CheckIn;
import com.guest.pojo.po.Customer;
import com.guest.pojo.po.Room;
import com.guest.pojo.vo.CheckInVo;
import com.guest.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 入住信息表 前端控制器
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@RestController
@RequestMapping("/check-in")
public class CheckInController {

    @Autowired
    ICheckInService checkInService;

    @Autowired
    ICustomerService customerService;

    @Autowired
    ICheckCustomerRelationService relationService;

    @Autowired
    IRoomService roomService;

    @Autowired
    IBackgroundService backgroundService;

    @Autowired
    IFrontService frontService;

    @PostMapping("/addCheckIn")
    @ApiOperation("新增入住信息")
    @Transactional(rollbackFor = Exception.class)  //开启事务
    public Response addCheckIn(@RequestBody CheckInVo checkInVo, HttpServletRequest request) {
        //鉴权
        String num = (String) request.getAttribute("num");
        if (!check(num)) {
            return new Response(ResponseMsg.ILLEGAL_OPERATION);
        }
        CheckIn checkIn = new CheckIn();
        //获取前端传来的客人信息列表
        List<Customer> checkInUser = checkInVo.getCheckInUser();
        //判断入住人数是否大于房间最大容量
        if (!checkCheckInUserCount(checkInVo, checkInUser)) {
            return Response.fail("入住人数大于房间最大人数");
        }
        //保存入住信息以及客人信息
        try {
            //复制checkInVo的属性到checkIn中
            BeanUtils.copyProperties(checkInVo, checkIn);
            //保存入住信息
            saveCheckIn(checkIn, checkInVo.getRoomNumber());
            //保存客人信息
            saveCustomer(checkInUser);
        } catch (Exception e) {
            return Response.fail("新增入住和客人信息失败," + e.getMessage());
        }
        try {
            //建立入住客人与入住信息的联系
            CheckInCustomerRelation(checkInUser, checkInVo);
        } catch (Exception e) {
            throw e;
        }
        //更新房间状态
        try {
            QueryWrapper<Room> queryWrapper = new QueryWrapper();
            queryWrapper.eq("room_number", checkInVo.getRoomNumber());
            Room room = roomService.getOne(queryWrapper);
            //更改房间状态为入住中
            room.setRoomStatus(3);
            roomService.updateById(room);
        } catch (Exception e) {
            throw e;
        }
        return Response.success();
    }

    @GetMapping("/getCheckIn")
    @ApiOperation("获取入住信息")
    public Response getCheckIn(@RequestParam String roomNumber) {
        //查询入住信息
        QueryWrapper<CheckIn> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ne("status", 1)  //ne->not equal 代表状态为未离店
                .eq("room_number", roomNumber);
        //根据房号查询数据库中对应的入住信息
        CheckIn checkIn = checkInService.getOne(queryWrapper);
        if (checkIn == null)
            return Response.success();
        //获取入住信息所关联的入住客人列表
        List<Customer> customerList = relationService.getRelationCustomer(checkIn.getId());
        CheckInVo result = new CheckInVo();
        //复制属性
        BeanUtils.copyProperties(checkIn, result);
        //将客人列表赋值 返回给前端
        result.setCheckInUser(customerList);
        return Response.success(result);
    }

    /**
     * 新增入住信息与客户的对应关系
     *
     * @param checkInUser
     * @param checkInVo
     * @return
     */
    public Boolean CheckInCustomerRelation(List<Customer> checkInUser, CheckInVo checkInVo) {
        try {
            //查询数据库中房间号对应的入住信息
            QueryWrapper<CheckIn> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .ne("status", 1)  //状态为未离店
                    .eq("room_number", checkInVo.getRoomNumber());
            CheckIn checkIn = checkInService.getOne(queryWrapper);
            //如果入住信息为空 直接返回
            if (checkIn == null)
                return false;
            List<CheckCustomerRelation> list = new ArrayList<>();
            relationService.remove(new QueryWrapper<CheckCustomerRelation>().eq("check_in_id",checkIn.getId()));
            //遍历入住的客人数组 与当前入住信息建立关联
            checkInUser.forEach(item -> {
            CheckCustomerRelation relation = new CheckCustomerRelation();
                //查询数据库中中是否有该客人信息
                QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
                customerQueryWrapper.eq("id_number", item.getIdNumber());
                Customer customer = customerService.getOne(customerQueryWrapper);
                //获取对应客人的Id
                relation.setCustomerId(customer.getId());
                //获取本次入住信息的Id
                relation.setCheckInId(checkIn.getId());
                list.add(relation);
            });
            relationService.saveOrUpdateBatch(list);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 保存入住信息 有则更新无则保存
     *
     * @param checkIn
     * @param roomNumber
     */
    private void saveCheckIn(CheckIn checkIn, String roomNumber) {
        QueryWrapper<CheckIn> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ne("status", 1)  //状态为未离店
                .eq("room_number", roomNumber);
        CheckIn check = checkInService.getOne(queryWrapper);
        if (check != null) {
            checkIn.setId(check.getId());
        }
        checkInService.saveOrUpdate(checkIn);
    }

    /**
     * 保存客人信息到数据库
     * @param checkInUser
     */
    private void saveCustomer(List<Customer> checkInUser) {
        //遍历客人信息数组
        checkInUser.forEach(customer -> {
            //查询数据库中是否有对应身份证号码的客人
            QueryWrapper<Customer> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id_number", customer.getIdNumber());
            Customer one = customerService.getOne(queryWrapper);
            //若有该记录 则获取该记录对应的Id
            if (one != null) {
                customer.setId(one.getId());
            }
            //更新客人信息
            customerService.saveOrUpdate(customer);
        });
    }

    /**
     * 判断入住人数是否大于房间最大容量
     *
     * @param checkInVo
     * @param checkInUser
     * @return
     */
    private Boolean checkCheckInUserCount(CheckInVo checkInVo, List<Customer> checkInUser) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_number", checkInVo.getRoomNumber());
        Room room = roomService.getOne(queryWrapper);
        //判断入住人数是否大于房间最大容量
        if (room.getSize() < checkInUser.size()) {
            //超过最大容量 返回false
            return false;
        }
        //未超过 返回true
        return true;
    }

    /**
     * 判断操作用户是否有权限
     *
     * @param name
     * @return
     */
    private Boolean check(String name) {
        return backgroundService.getById(name) != null || frontService.getById(name) != null;
    }
}
