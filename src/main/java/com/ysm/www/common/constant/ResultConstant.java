package com.ysm.www.common.constant;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/17
 * @Version V1.0
 **/
public class ResultConstant {


    public static final String SUCCEED_MESSAGE = "success";
    public static final String FAILED_MESSAGE = "failed";
    public static final String NO_AUTHORITY_MESSAGE = "no power";

    public static final int SUCCEED_CODE = 200;
    public static final int FAILED_CODE = 401;
    public static final int NO_AUTHENTICATION_CODE = 402;
    public static final int NO_AUTHORITY_CODE = 403;
    public static final int TOKEN_EXPIRE_CODE = 420;
    public static final int TOKEN_REFRESH_FAILED_CODE = 421;
}
