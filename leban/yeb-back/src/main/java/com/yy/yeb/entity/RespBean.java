package com.yy.yeb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    公共返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBean {

    private long code;
    private String mesasge;
    private Object obj;

//    成功返回
    public static RespBean success(String mesasge) {
        return new RespBean(200, mesasge, null);
    }
    //    成功返回
    public static RespBean success(String mesasge, Object object) {
        return new RespBean(200, mesasge, object);
    }
//      失败返回
    public static RespBean error(String message) {
        return new RespBean(500, message, null);
    }

    //      失败返回
    public static RespBean error(String message, Object object) {
        return new RespBean(500, message, object);
    }

}
