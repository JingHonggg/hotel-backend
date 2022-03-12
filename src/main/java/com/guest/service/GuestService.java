package com.guest.service;

import com.guest.pojo.po.Guest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guest.pojo.vo.GetAllGuestVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chuanguo.cao
 * @since 2022-03-02
 */
@Service
public interface GuestService extends IService<Guest> {

    List<Guest> getByIdCard(String idCard);

    List<Guest> getByContact(String contact);

    List<Guest> getByName(String name);

    int delAllSelection(String[] selection);

    List<GetAllGuestVo> getAllGuest(String idCard, String userName, String phone);

    int AddGuest(GetAllGuestVo getAllGuestVo);

    int UpdateGuest(GetAllGuestVo getAllGuestVo);
}
