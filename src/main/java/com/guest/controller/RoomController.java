package com.guest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guest.core.Response;
import com.guest.core.ResponseMsg;
import com.guest.pojo.po.CheckIn;
import com.guest.pojo.po.Room;
import com.guest.pojo.vo.RoomVo;
import com.guest.service.IBackgroundService;
import com.guest.service.ICheckInService;
import com.guest.service.IFrontService;
import com.guest.service.IRoomService;
import com.guest.utils.JwtUtill;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * <p>
 * 房间表 前端控制器
 * </p>
 *
 * @author lxy
 * @since 2022-03-17
 */
@RestController
@RequestMapping("/room")
@Slf4j
@Api("房间管理")
public class RoomController {

    @Autowired
    IRoomService roomService;

    @Autowired
    IBackgroundService backgroundService;

    @Autowired
    IFrontService frontService;

    @Autowired
    ICheckInService checkInService;

    @Autowired
    JwtUtill jwtUtill;

    @PostMapping("/saveRoom")
    @ApiOperation("新增、修改房间")
    public Response addRoom(RoomVo room, HttpServletRequest request) {
        //鉴权 查询是否有操作权限
        String num = (String) request.getAttribute("num");
        if (!check(num)) {
            return new Response(ResponseMsg.ILLEGAL_OPERATION);
        }
        //从数据库中查是否有该房间
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_number", room.getRoomNumber());
        Room roomInDb = roomService.getOne(queryWrapper);
        Room newRoom = new Room();
        //复制room内的属性到newRoom
        BeanUtils.copyProperties(room, newRoom);
        //如果数据库中没有该房间的记录
        if (roomInDb == null) {
            //如果上传了房间的图片
            if (room.getFile() != null) {
                //保存图片 返回图片对应的地址
                String imageUrl = savePicture(room.getFile(), room.getImageUrl());
                //将图片地址存入newRoom对象
                newRoom.setImageUrl(imageUrl);
            }
            //将newRoom对象存入数据库
            roomService.save(newRoom);
        } else { //如果数据库中有该房间的记录
            //获取该房间在数据库中的唯一Id
            newRoom.setId(roomInDb.getId());
            // 同上！！！
            if (room.getFile() != null) {
                String imageUrl = savePicture(room.getFile(), room.getImageUrl());
                newRoom.setImageUrl(imageUrl);
            }
            //更新数据库中该房间的信息
            roomService.updateById(newRoom);
        }
        return Response.success();
    }

    @PostMapping("/deleteRoom")
    @ApiOperation("删除房间")
    public Response deleteRoom(@RequestBody Room room, HttpServletRequest request) {
        //鉴权
        String num = (String) request.getAttribute("num");
        if (!check(num)) {
            return new Response(ResponseMsg.ILLEGAL_OPERATION);
        }
        if (room.getId() == null) {
            return Response.fail("房间Id为空");
        }
        //查询数据库中该房间的信息
        Room roomInDb = roomService.getById(room.getId());
        //若为入住状态 不可删除 直接返回
        if (roomInDb.getRoomStatus() == 3) {
            return Response.fail("房间处于入住状态,不允许删除!");
        }
        //非入住状态时 可以删除
        try {
            //删除房间对应的图片
            removePicture(roomInDb.getImageUrl());
            //删除房间
            roomService.removeById(roomInDb.getId());
            return Response.success();
        } catch (Exception e) {
            return Response.fail("房间删除失败, " + e.getClass());
        }
    }

    /**
     * 鉴权 校验是否有操作权限
     *
     * @param name
     * @return
     */
    private Boolean check(String name) {
        //判断操作的用户是否是前端管理员或者后台管理员
        return backgroundService.getById(name) != null || frontService.getById(name) != null;
    }

    /**
     * 保存图片到本地 返回图片在本地的 URL
     *
     * @param file       文件对象
     * @param originPath 原图片的 URL
     * @return
     */
    public String savePicture(MultipartFile file, String originPath) {
        //原路径为空 证明之前房间没有上传过图片 则保存file到本地
        if (originPath == null) {
            //图片存储目录
            String dirPath = System.getProperty("user.dir") + "\\roomPicture";
            //图片新名称 命名方式: 当前时间戳.jpg  避免图片名称出现重复
            String fileName = System.currentTimeMillis() + ".jpg";  //以时间戳重新命名图片
            //图片保存的路径
            String savePath = dirPath + File.separator;
            log.info("图片保存地址：" + savePath);
            // 图片的访问路径 = 保存路径 + 文件名
            String visitPath = savePath + fileName;
            log.info("图片访问uri：" + visitPath);
            //创建文件对象
            File saveFile = new File(visitPath);
            //文件对象是否存在 不存在则创建
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }
            try {
                //将上传的房间图片文件移动到真实存储路径下
                file.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //返回图片存储的路径
            return visitPath;
        } else { //原路径不为空 证明之前上传国图片 则直接更新图片即可
            try {
                //创建图片所在地址的文件对象
                File originFile = new File(originPath);
                //删除原来的文件
                originFile.delete();
                //保存新的图片文件
                file.transferTo(originFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //返回图片对应的地址
            return originPath;
        }
    }

    /**
     * 删除图片
     *
     * @param path 图片的地址
     */
    private void removePicture(String path) {
        if (path != null) {
            File file = new File(path);
            file.delete();
        }
    }

    @GetMapping("/getRooms")
    @ApiOperation("获取所有房间列表")
    public Response getRooms(HttpServletRequest request) {
        //鉴权
        String num = (String) request.getAttribute("num");
        if (!check(num)) {
            return new Response(ResponseMsg.ILLEGAL_OPERATION);
        }
        List<Room> roomList;
        try {
            //查询所有房间
            roomList = roomService.list();
            //返回房间的列表
            return Response.success(roomList);
        } catch (Exception e) {
            return Response.fail("获取房间列表失败, " + e.getMessage());
        }
    }

    @PostMapping("/checkOut")
    @ApiOperation("退房操作")
    @Transactional(rollbackFor = Exception.class)
    public Response checkOut(@RequestParam String roomNumber, HttpServletRequest request) {
        //鉴权
        String num = (String) request.getAttribute("num");
        if (!check(num)) {
            return new Response(ResponseMsg.ILLEGAL_OPERATION);
        }
        //判断并更新房间状态
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_number", roomNumber);
        //查询数据库中该房间的信息
        Room room = roomService.getOne(queryWrapper);
        //判断房间状态是否为入住中
        if (room.getRoomStatus() == 3) {
            //为入住中则将房间状态设置为打扫中
            room.setRoomStatus(2);
            //更新房间信息
            roomService.updateById(room);
        } else {
            return Response.fail("房间状态非入住中,无法退房。");
        }
        //入住信息改为离店
        modifyCheckInStatus(roomNumber);
        return Response.success();
    }

    @PostMapping("/updateRoomStatus")
    @ApiOperation("更新房间状态")
    public Response updateStatus(@RequestParam String roomNumber, HttpServletRequest request) {
        //鉴权
        String num = (String) request.getAttribute("num");
        if (!check(num)) {
            return new Response(ResponseMsg.ILLEGAL_OPERATION);
        }
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_number", roomNumber);
        //查询数据库中对应的房间信息
        Room roomInDb = roomService.getOne(queryWrapper);
        //房间状态是否为打扫中
        if (roomInDb.getRoomStatus() == 2) {
            //是则将状态更改为空闲中
            roomInDb.setRoomStatus(1);
            try {
                //更新房间信息
                roomService.updateById(roomInDb);
                return Response.success();
            } catch (Exception e) {
                return Response.fail("房间状态更新失败, " + e.getMessage());
            }
        }
        return Response.fail("房间状态非打扫中,更新状态失败");
    }

    @PostMapping("/getImage")
    @ApiOperation("获取图片流")
    public void getImage(@RequestParam String imageUrl,HttpServletResponse response) {
        //创建图片url对应的文件对象
        File imageFile = new File(imageUrl);
        try {
            //获取与文件的连接对象
            FileInputStream fis = new FileInputStream(imageFile);
            //创建文件对应大小的字节数组
            byte[] bytes = new byte[fis.available()];
            //将文件读取为字节数组
            fis.read(bytes);
            //关闭文件的连接对象
            fis.close();  //关闭流 避免其余地方使用该图片地址时 造成无法操作的Bug
            //设置返回格式为 jpg格式的图片
            response.setContentType("image/JPEG");
            //以文件流形式返回图片对象
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更改入住信息的状态
     * @param roomNumber
     */
    private void modifyCheckInStatus(String roomNumber) {
        try {
            QueryWrapper<CheckIn> queryWrapper = new QueryWrapper<>();
            //查询数据库中对应房间号的入住信息
            queryWrapper
                    .ne("status", 1) //查询状态为未离店的入住信息
                    .eq("room_number", roomNumber);
            CheckIn checkIn = checkInService.getOne(queryWrapper);
            //若入住信息不为空
            if (checkIn != null) {
                //将入住信息状态更改为已离店
                checkIn.setStatus(true);
                //更新入住信息
                checkInService.updateById(checkIn);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
