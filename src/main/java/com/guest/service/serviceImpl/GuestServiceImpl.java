package com.guest.service.serviceImpl;

import com.guest.pojo.po.Guest;
import com.guest.mapper.GuestMapper;
import com.guest.pojo.vo.GetAllGuestVo;
import com.guest.service.CheckInService;
import com.guest.service.GuestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chuanguo.cao
 * @since 2022-03-02
 */
@Service
public class GuestServiceImpl extends ServiceImpl<GuestMapper, Guest> implements GuestService {

    @Autowired
    GuestMapper guestMapper;

    @Autowired
    private CheckInService checkInService;

    @Override
    public List<Guest> getByIdCard(String idCard) {
        return guestMapper.getByIdCard(idCard);
    }

    @Override
    public List<Guest> getByContact(String contact) {
        return guestMapper.getByContact(contact);
    }

    @Override
    public List<Guest> getByName(String name) {
        return guestMapper.getByName(name);
    }

    @Override
    public int delAllSelection(String[] selection) {
        return guestMapper.delAllSelection(selection);
    }

    @Override
    public List<GetAllGuestVo> getAllGuest(String idCard, String userName, String phone) {
        return guestMapper.getAllGuest(idCard, userName, phone);
    }

    @Override
    public int UpdateGuest(GetAllGuestVo getAllGuestVo) {
        LocalDateTime dt = LocalDateTime.now();
        LocalDateTime time = dt.plusDays(1);
        getAllGuestVo.setToTime(time);
        return guestMapper.UpdateGuest(getAllGuestVo);
    }

    @Override
    public int AddGuest(GetAllGuestVo getAllGuestVo) {
        int count = guestMapper.AddGuest(getAllGuestVo.getIdCard(), getAllGuestVo.getName(), getAllGuestVo.getContact());
        int count2 = checkInService.AddCheckIn(getAllGuestVo.getIdCard(), getAllGuestVo.getRoomId(), getAllGuestVo.getState());
        return count + count2;

    }
}
