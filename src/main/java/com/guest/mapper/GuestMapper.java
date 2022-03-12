package com.guest.mapper;

import com.guest.pojo.po.Guest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guest.pojo.vo.GetAllGuestVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chuanguo.cao
 * @since 2022-03-02
 */
@Mapper
@Repository
public interface GuestMapper extends BaseMapper<Guest> {
    List<Guest> getByIdCard(String idCard);

    List<Guest> getByContact(String contact);

    List<Guest> getByName(String name);

    int delAllSelection(String[] selection);

    List<GetAllGuestVo> getAllGuest(String idCard, String userName, String phone);

    int AddGuest(String idCard, String userName, String phone);

    int UpdateGuest(GetAllGuestVo guestVo);

}
