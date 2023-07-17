package com.ysm.www.auth.constant;

import com.ysm.www.common.result.CommonResult;

public class SecurityConstant {
    public static final CommonResult<String> ACCESS_DENY =
            new CommonResult<String>().setSuccess(false).setMessage("无权限访问此接口").setCode(401);

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String STATE_PREFIX = "Bearer ";

    public static final String TOKEN_PREFIX = "Token:";
    public static final String INFORMATION_PREFIX = "Information:";
    public static final String PERMISSION_PREFIX = "Permission:";

    public static final String TOKEN_REFRESH_PREFIX = "refresh:";

    public static final long TOKEN_2H_TIME = 1000 * 60 * 120;

    public static final long TOKEN_7D_TIME = 604800L;
}
