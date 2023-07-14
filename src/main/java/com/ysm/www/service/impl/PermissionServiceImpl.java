package com.ysm.www.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.www.entity.po.Permission;
import com.ysm.www.mapper.PermissionMapper;
import com.ysm.www.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
