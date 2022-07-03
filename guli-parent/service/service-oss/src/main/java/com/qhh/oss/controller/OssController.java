package com.qhh.oss.controller;

import com.qhh.oss.service.OssService;
import com.qhh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: QiuHH
 * @CreateTime: 2022-07-03  10:55
 * @Description: TODO
 */
@RestController
@RequestMapping("/edu/file")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/")
    public R uploadOssFile(MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }
}
