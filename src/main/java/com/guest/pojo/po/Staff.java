package com.guest.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author: lxy
 * @date: 2022-04-03 19:44
 **/
@TableName("staff")
@Data
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("work_number")
    private String workNumber;

    @TableField("name")
    private String name;

    @TableField("post")
    private String post;

    @TableField("status")
    private Integer status;

    @TableField("phone")
    private String phone;

    @TableField("work_year")
    private Integer workYear;

    @TableField("description")
    private String description;

    @TableField("image_url")
    private String imageUrl;

    @TableField(exist = false)
    private String url;
}
