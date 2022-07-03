package com.qhh.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qhh.edu.entity.Teacher;
import com.qhh.edu.entity.vo.TeacherQuery;
import com.qhh.edu.service.TeacherService;
import com.qhh.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RequestMapping("/eduservice/teacher")
@Api(tags = "讲师管理")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/findAll")
    @ApiOperation(value = "获取所有讲师")
    public R findAllTeacher(){
        List<Teacher> teacherList = teacherService.list(null);
//        try {
//            int i = 10 / 0;
//        }catch (ArithmeticException e){
//            throw new GuliException(50001, "执行了自定义异常处理");
//        }
        return R.ok().data("items", teacherList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据ID删除讲师")
    public R removeTeacher(@PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if (flag)
            return R.ok();
        return R.error();
    }

    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable Long current,
                             @PathVariable Long limit){
//        创建Page对象
        Page<Teacher> pageTeacher = new Page<>(current,limit);
//        调用方法实现分页，把分页所有数据封装到pageTeacher中
        teacherService.page(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();
//        Map<String, Object> map = new HashMap<>();
//        map.put("total" , total);
//        map.put("rows", records);
        return R.ok().data("total", total).data("rows", records);
    }

//    条件查询带分页的方法
    @PostMapping("/pageTeacherCondition/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable Long current,
                                  @PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<Teacher> page = new Page<>(current, limit);

        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified",end);
        }

//        排序
        wrapper.orderByDesc("gmt_create");

        teacherService.page(page, wrapper);
        long total = page.getTotal();
        List<Teacher> records = page.getRecords();
        return R.ok().data("total", total).data("rows", records);
    }

//    添加讲师
    @PostMapping("/addTeacher")
    public R addTeacher(@RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        if (save)
            return R.ok();
        return R.error();
    }

//    根据id查询
    @GetMapping("/getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

//    修改讲师
    @PostMapping("/updateTeacher")
    public R updateTeacher(@RequestBody Teacher teacher){
        boolean b = teacherService.updateById(teacher);
        if (b)
            return R.ok();
        return R.error();
    }

}

