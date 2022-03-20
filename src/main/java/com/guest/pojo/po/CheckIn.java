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
 * 入住信息表
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CheckIn对象", description="入住信息表")
public class CheckIn implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "房间编号")
    private String roomNumber;

    @ApiModelProperty(value = "开房人")
    private String registrant;

    @ApiModelProperty(value = "押金")
    private BigDecimal cashPledge;

    @ApiModelProperty(value = "应付金额")
    private BigDecimal shouldPay;

    @ApiModelProperty(value = "实付金额")
    private BigDecimal realPay;

    @ApiModelProperty(value = "入住时间")
    private LocalDateTime inDate;

    @ApiModelProperty(value = "离店时间")
    private LocalDateTime outDate;

    @ApiModelProperty(value = "入住信息登记人")
    private String createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否已离店")
    private Boolean status;


}
