package com.ysm.www.entity.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Data
public class LoginBo {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
