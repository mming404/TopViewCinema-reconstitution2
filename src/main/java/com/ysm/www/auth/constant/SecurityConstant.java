package com.ysm.www.auth.constant;


import com.ysm.www.entity.result.CommonResult;

/**
 * @author Albumen
 * @date 2020/3/1
 */
public class SecurityConstant {
    public static final CommonResult<String> ACCESS_DENY =
            new CommonResult<String>().setSuccess(false).setMessage("无权限访问此接口").setCode(401);

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String STATE_PREFIX = "Bearer ";

    public static final String TOKEN_PREFIX = "Token-";
    public static final String INFORMATION_PREFIX = "Information-";
    public static final String PERMISSION_PREFIX = "Permission-";
}
