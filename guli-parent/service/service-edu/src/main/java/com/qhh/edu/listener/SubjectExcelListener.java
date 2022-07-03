package com.qhh.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qhh.base.exceptionHandler.GuliException;
import com.qhh.edu.entity.Subject;
import com.qhh.edu.entity.excel.SubjectData;
import com.qhh.edu.service.SubjectService;

/**
 * @Author: QiuHH
 * @CreateTime: 2022-07-03  15:52
 * @Description: 不能交给Spring管理，手动new，不能注入其他对象，不能实现数据库操作
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

//    没有交给spring管理，无法直接注入service，需要手动传入
    public SubjectService subjectService;

    public SubjectExcelListener() {}
    public SubjectExcelListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

//    读取excel，一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new GuliException(20001, "文件数据为空");
        }
//        每次读取有两个值
//        判断一级分类是否重复
        Subject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null){ //表中没有相同的一级分类，可以添加到表中
            existOneSubject = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        String pid = existOneSubject.getId();
//        添加二级分类
        Subject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject == null){ //表中没有相同的一级分类，可以添加到表中
            existTwoSubject = new Subject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

//    判断一级分类不能重复添加
    private Subject existOneSubject(SubjectService subjectService, String name){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name)
                .eq("parent_id", "0");
        return subjectService.getOne(wrapper);
    }

//    判断一级分类不能重复添加
    private Subject existTwoSubject(SubjectService subjectService, String name, String pid){
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name)
                .eq("parent_id", pid);
        return subjectService.getOne(wrapper);
    }


    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
