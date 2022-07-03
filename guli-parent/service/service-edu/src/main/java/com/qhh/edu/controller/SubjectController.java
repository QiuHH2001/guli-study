package com.qhh.edu.controller;


import com.qhh.edu.service.SubjectService;
import com.qhh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author QiuHH
 * @since 2022-07-03
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

//    添加课程分类，获取上传来的文件，读取文件内容
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
//        上传来的excel文件
        subjectService.saveSubject(file, subjectService);
        return R.ok();
    }

}

