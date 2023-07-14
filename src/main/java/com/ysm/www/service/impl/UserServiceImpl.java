package com.ysm.www.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.www.entity.po.User;
import com.ysm.www.mapper.UserMapper;
import com.ysm.www.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
