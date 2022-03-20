package com.guest.pojo.po;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 房间表
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Room对象", description="房间表")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "房间编号")
    private String roomNumber;

    @ApiModelProperty(value = "房间类型")
    private String roomType;

    @ApiModelProperty(value = "房间状态 1-空闲中 2-打扫中 3-入住中")
    private Integer roomStatus;

    @ApiModelProperty(value = "房间位置")
    private String location;

    @ApiModelProperty(value = "房间容纳人数")
    private Integer size;

    @ApiModelProperty(value = "当日房价")
    private BigDecimal price;

    @ApiModelProperty(value = "房间设施")
    private String facility;

    @ApiModelProperty(value = "附件图片地址")
    private String imageUrl;

    @ApiModelProperty(value = "创建录入人")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
