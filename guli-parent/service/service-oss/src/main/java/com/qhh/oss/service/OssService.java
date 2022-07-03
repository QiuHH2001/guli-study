package com.qhh.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: QiuHH
 * @CreateTime: 2022-07-03  10:54
 * @Description: TODO
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
