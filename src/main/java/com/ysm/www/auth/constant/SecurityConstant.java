package com.ysm.www.auth.constant;

import com.ysm.www.common.result.CommonResult;
import com.ysm.www.common.result.RespResult;

public class SecurityConstant {
    public static final RespResult<String> ACCESS_DENY = RespResult.failed("无权访问此接口");

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String STATE_PREFIX = "Bearer ";

    public static final String TOKEN_PREFIX = "Token:";
    public static final String INFORMATION_PREFIX = "Information:";
    public static final String PERMISSION_PREFIX = "Permission:";

    public static final String TOKEN_REFRESH_PREFIX = "refresh:";

    public static final long TOKEN_2H_TIME = 1000 * 60 * 120;

    public static final long TOKEN_7D_TIME = 604800L;
}
