package com.ysm.www.entity.vo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Data
public class LoginVo {

    private String username;

    private boolean orSuccess;

    private String token;


}
