package com.guest.pojo.vo;

import com.guest.pojo.po.Room;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * 继承Room实体类 接收前端传来的参数
 * @author: lxy
 * @date: 2022-03-18 00:41
 **/
@Data
@ApiModel(value = "房间对象")
public class RoomVo extends Room {

    @ApiModelProperty(value = "房间图片对象")
    private MultipartFile file;
}
