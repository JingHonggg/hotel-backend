package com.guest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guest.pojo.dto.StaffParam;
import com.guest.pojo.po.Front;
import com.guest.pojo.po.Staff;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 员工信息 Mapper 接口
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
public interface StaffMapper extends BaseMapper<Staff> {

    Page<Staff> search(Page<Staff> page,@Param("param") StaffParam param);
}
