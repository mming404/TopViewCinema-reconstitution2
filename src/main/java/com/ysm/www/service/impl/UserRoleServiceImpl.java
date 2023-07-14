package com.ysm.www.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.www.entity.po.UserRole;
import com.ysm.www.mapper.UserRoleMapper;
import com.ysm.www.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper,UserRole> implements UserRoleService {
}
