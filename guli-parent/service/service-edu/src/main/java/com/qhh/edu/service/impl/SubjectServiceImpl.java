package com.qhh.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qhh.edu.entity.Subject;
import com.qhh.edu.entity.excel.SubjectData;
import com.qhh.edu.entity.subject.OneSubject;
import com.qhh.edu.entity.subject.TwoSubject;
import com.qhh.edu.listener.SubjectExcelListener;
import com.qhh.edu.mapper.SubjectMapper;
import com.qhh.edu.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 课程分类列表
     * @return
     */
    @Override
    public List<OneSubject> getOneTwoSubject() {
//        查询一级分类
        QueryWrapper<Subject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<Subject> oneSubjectList = baseMapper.selectList(wrapperOne);

//        查询二级分类
        QueryWrapper<Subject> wrapperTwo = new QueryWrapper<>();
        wrapperOne.ne("parent_id", "0");
        List<Subject> twoSubjectList = baseMapper.selectList(wrapperTwo);

//        封装一级分类
        List<OneSubject> finalSubjectList = new ArrayList<>();
        for (Subject subject : oneSubjectList) {
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(subject, oneSubject);

            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            for (Subject tSubject : twoSubjectList) {
                if (tSubject.getParentId().equals(subject.getId())){
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubjectList);
            finalSubjectList.add(oneSubject);
        }

        return finalSubjectList;
    }
}
