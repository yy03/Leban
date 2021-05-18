package com.yy.yeb.exception;

/*
    全局异常处理
 */


import com.yy.yeb.entity.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public RespBean mySqlException(SQLException e) {
        return RespBean.error("不支持此操作，操作失败！");
    }

}
