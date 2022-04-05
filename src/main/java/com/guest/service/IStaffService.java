package com.guest.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guest.pojo.dto.StaffParam;
import com.guest.pojo.po.Room;
import com.guest.pojo.po.Staff;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
public interface IStaffService extends IService<Staff> {


    Page<Staff> search(Page<Staff> page, StaffParam param);
}
