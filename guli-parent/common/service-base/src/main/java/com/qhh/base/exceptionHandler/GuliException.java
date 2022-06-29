package com.qhh.base.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: QiuHH
 * @CreateTime: 2022-06-29  15:45
 * @Description: 自定义异常类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException{
    private Integer code;
    private String msg;
}
