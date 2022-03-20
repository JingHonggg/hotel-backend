package com.guest.pojo.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 超级管理员表
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Background对象", description="超级管理员表")
public class Background implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    @TableId("back_id")
    private String backId;

    @ApiModelProperty(value = "密码")
    private String password;


}
