package com.ysm.www.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.www.entity.po.Role;
import com.ysm.www.mapper.RoleMapper;
import com.ysm.www.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
