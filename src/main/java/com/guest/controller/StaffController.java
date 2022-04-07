package com.guest.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guest.core.Response;
import com.guest.pojo.dto.StaffParam;
import com.guest.pojo.po.Staff;
import com.guest.pojo.vo.StaffVo;
import com.guest.service.IStaffService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: lxy
 * @date: 2022-04-05 19:53
 **/
@RestController
@Slf4j
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    IStaffService staffService;

    @PostMapping("/search")
    public Response search(@RequestBody StaffParam staffParam){
        Page<Staff> page = new Page<>(staffParam.getCurrent(),staffParam.getSize());
        Page<Staff> search = staffService.search(page, staffParam);
        int total = staffService.list().size();
        search.setTotal(total);
        return Response.success(search);
    }

    @PostMapping("/add")
    public Response add(StaffVo staffVo){
        QueryWrapper<Staff> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("work_number",staffVo.getWorkNumber());
        Staff one = staffService.getOne(queryWrapper);
        if (one!=null){
            return Response.fail("员工工号已存在");
        }
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffVo,staff);
        if (staffVo.getFile()!=null){
            String imageUrl = savePicture(staffVo.getFile(), null);
            staff.setImageUrl(imageUrl);
        }
        staffService.save(staff);
        return Response.success();
    }

    @PostMapping("/edit")
    public Response edit(StaffVo staffVo){
        Staff staff = new Staff();
        BeanUtils.copyProperties(staffVo,staff);
        if (staffVo.getFile()!=null){
            String imageUrl = savePicture(staffVo.getFile(), staff.getImageUrl());
            staff.setImageUrl(imageUrl);
        }
        staffService.updateById(staff);
        return Response.success();
    }

    @PostMapping("/delete")
    public Response delete(@RequestBody Staff staff){
        staffService.removeById(staff.getId());
        removePicture(staff.getImageUrl());
        return Response.success();
    }

    @PostMapping("/getImage")
    @ApiOperation("获取图片流")
    public void getImage(@RequestParam String imageUrl, HttpServletResponse response) {
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

    private void removePicture(String path) {
        if (path != null) {
            File file = new File(path);
            file.delete();
        }
    }

    private String savePicture(MultipartFile file, String originPath) {
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
}
