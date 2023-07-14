package com.ysm.www.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ysm.www.auth.dto.SignResult;
import com.ysm.www.entity.bo.LoginBo;
import com.ysm.www.entity.po.User;
import com.ysm.www.entity.vo.LoginVo;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
public interface UserService extends IService<User> {
    SignResult userLogin(LoginBo loginBo);
}
