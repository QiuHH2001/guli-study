package com.qhh.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: QiuHH
 * @CreateTime: 2022-07-14  20:02
 * @Description: TODO
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
