package com.guest.controller;

import com.guest.core.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileController {

    RoomController roomController = new RoomController();

    @PostMapping("/uploadImg")
    public Response uploadImg(MultipartFile file, String originPath) {
        String url = roomController.savePicture(file, originPath);
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap.put("url", url);
        return Response.success(resultMap);
    }
}
