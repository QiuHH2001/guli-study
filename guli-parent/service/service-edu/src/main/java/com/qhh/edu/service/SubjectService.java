package com.qhh.edu.service;

import com.qhh.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qhh.edu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author QiuHH
 * @since 2022-07-03
 */
public interface SubjectService extends IService<Subject> {

    void saveSubject(MultipartFile file, SubjectService subjectService);

    List<OneSubject> getOneTwoSubject();
}
