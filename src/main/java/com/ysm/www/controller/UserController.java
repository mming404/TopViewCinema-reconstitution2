package com.ysm.www.controller;

import com.ysm.www.entity.bo.LoginBo;
import com.ysm.www.common.result.CommonResult;
import com.ysm.www.entity.vo.LoginVo;
import com.ysm.www.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    CommonResult<LoginVo> login(@Validated @RequestBody LoginBo loginBo){
        return CommonResult.operateSuccess(userService.userLogin(loginBo));
    }
}
