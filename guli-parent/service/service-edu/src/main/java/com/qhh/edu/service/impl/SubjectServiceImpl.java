package com.qhh.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.qhh.edu.entity.Subject;
import com.qhh.edu.entity.excel.SubjectData;
import com.qhh.edu.listener.SubjectExcelListener;
import com.qhh.edu.mapper.SubjectMapper;
import com.qhh.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author QiuHH
 * @since 2022-07-03
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
