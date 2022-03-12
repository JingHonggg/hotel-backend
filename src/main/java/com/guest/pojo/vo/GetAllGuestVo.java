package com.guest.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllGuestVo {

    /**
     * 客户的身份证号
     */
    private String idCard;

    /**
     * 客户的姓名
     */
    private String name;

    /**
     * 客户的联系方式
     */
    private String contact;

    private Integer id;


    /**
     * 客户的id
     */
    private String guestIdCard;

    /**
     * 房间号
     */
    private String roomId;

    /**
     * 入住日期
     */
    private Timestamp fromTime;

    /**
     * 预计退房时间
     */
    private LocalDateTime toTime;

    /**
     * 状态，0代表已退房，1代表正在入住
     */
    private Integer state;

}
