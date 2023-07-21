package com.ysm.www.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.www.auth.util.SecurityUtil;
import com.ysm.www.entity.bo.LoginBo;
import com.ysm.www.entity.po.Permission;
import com.ysm.www.entity.po.RolePermission;
import com.ysm.www.entity.po.User;
import com.ysm.www.entity.po.UserRole;
import com.ysm.www.entity.vo.LoginVo;
import com.ysm.www.mapper.*;
import com.ysm.www.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private SecurityUtil securityUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LoginVo userLogin(LoginBo loginBo) {
        User user = userMapper.getByName(loginBo.getUsername());
        Assert.notNull(user, "查无此人");
        Assert.isTrue(loginBo.getPassword().equals(user.getPassword()), "密码错误");

        //查询用户角色
        List<UserRole> userRoles = userRoleMapper.listByUserId(user.getId());
        Assert.notEmpty(userRoles, "用户角色为空");

        List<Integer> roleIds = userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());

        //根据角色查询权限  不能重复
        List<RolePermission> rolePermissionList = rolePermissionMapper.listPerByRoleIds(roleIds);
        Assert.notEmpty(rolePermissionList, "角色权限为空");

        Set<Integer> permissionIds = rolePermissionList.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toSet());

        //将权限名封装
        List<Permission> permissionList = permissionMapper.listByIds(permissionIds);
        Assert.notEmpty(permissionList, "查无此权限");
        List<String> permissionNames = permissionList.stream()
                .map(Permission::getPermissionName)
                .collect(Collectors.toList());


        String jwt = securityUtil.login(user.getId(), permissionNames, user);
        Assert.notNull(jwt, "生成token失败");

        LoginVo loginVo = new LoginVo();
        loginVo.setOrSuccess(true);
        loginVo.setToken(jwt);
        loginVo.setUsername(user.getUsername());

        return loginVo;


    }
}
