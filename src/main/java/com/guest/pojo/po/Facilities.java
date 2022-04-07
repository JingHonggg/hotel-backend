package com.guest.pojo.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Facilities对象", description="")
public class Facilities implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "序号")
    private Integer id;

    @ApiModelProperty(value = "所属楼层")
    private String floor;

    @ApiModelProperty(value = "维护人")
    private String person;

    @ApiModelProperty(value = "总数")
    private Integer total;

    @ApiModelProperty(value = "当前人数")
    private Integer current;

    @ApiModelProperty(value = "开放状态1开放 2不开放")
    private Integer status;

    @ApiModelProperty(value = "营业时间")
    private String time;


}
