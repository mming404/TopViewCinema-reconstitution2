package com.ysm.www.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysm.www.entity.po.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {
    default List<RolePermission> listPerByRoleIds(List<Integer> roleIds){
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(RolePermission::getId,RolePermission::getRoleId,RolePermission::getPermissionId)
                .in(RolePermission::getRoleId,roleIds);
        return selectList(wrapper);
    }
}
