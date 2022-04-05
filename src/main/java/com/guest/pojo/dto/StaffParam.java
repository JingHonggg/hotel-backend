package com.guest.pojo.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author: lxy
 * @date: 2022-04-05 19:56
 **/
@Data
public class StaffParam extends Page {

    private String name;

    private String workNumber;

    private Integer status;
}
