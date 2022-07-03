package com.qhh.edu.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: QiuHH
 * @CreateTime: 2022-07-03  15:46
 * @Description: TODO
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
