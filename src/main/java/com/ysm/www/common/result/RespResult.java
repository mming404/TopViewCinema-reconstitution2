package com.ysm.www.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;






@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RespResult<T> {
    private Integer code;

    private boolean isSuccess;
    private String message;
    private T data;


    public static <T> RespResult<T> success(String message, T data) {
        return new RespResult<>(ResultConstant.SUCCESS_CODE,true, message, data);
    }

    public static <T> RespResult<T> failed(String message, T data) {
        return new RespResult<>(ResultConstant.FAIL_CODE,false, message, data);
    }

    public static <T> RespResult<T> success(String message) {
        return new RespResult<>(ResultConstant.SUCCESS_CODE,true, message, null);
    }

    public static <T> RespResult<T> failed(String message) {
        return new RespResult<>(ResultConstant.FAIL_CODE,false, message, null);
    }



}
