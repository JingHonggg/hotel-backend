package com.guest.pojo.vo;

import com.guest.pojo.po.CheckIn;
import com.guest.pojo.po.Customer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: Lxy
 * @date: 2022-03-19 00:01
 **/
@Data
@ApiModel(value = "入住信息参数对象")
public class CheckInVo extends CheckIn {

    @ApiModelProperty("入住客人")
    private List<Customer> checkInUser;
}
