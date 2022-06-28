package com.qhh.edu.controller;

import com.qhh.edu.entity.Teacher;
import com.qhh.edu.service.TeacherService;
import com.qhh.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author QiuHH
 * @since 2022-06-28
 */
@RestController
@RequestMapping("/edu/teacher")
@Api(tags = "讲师管理")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/findAll")
    @ApiOperation(value = "获取所有讲师")
    public R findAllTeacher(){
        List<Teacher> teacherList = teacherService.list(null);
        return R.ok().data("items", teacherList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag)
            return R.ok();
        else
            return R.error();
    }



}

