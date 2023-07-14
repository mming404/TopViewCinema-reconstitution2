package com.ysm.www.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysm.www.entity.po.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
   default List<Permission> listByIds(Set<Integer> permissionIds){
       LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
       wrapper.select(Permission::getId,Permission::getPermissionName,Permission::getPermissionDetail)
               .in(Permission::getId,permissionIds);
       return selectList(wrapper);
   }
}
