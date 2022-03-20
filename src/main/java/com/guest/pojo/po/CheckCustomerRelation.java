package com.guest.pojo.po;

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
 * 入住客人与入住信息对应表
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="CheckCustomerRelation对象", description="入住客人与入住信息对应表")
public class CheckCustomerRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "唯一标识")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "入住信息id")
    private Long checkInId;

    @ApiModelProperty(value = "入住客户id")
    private Long customerId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
