package com.guest.pojo.vo;

import com.guest.pojo.po.Staff;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lxy
 * @date: 2022-04-05 20:20
 **/
@Data
public class StaffVo extends Staff {

    private MultipartFile file;
}
