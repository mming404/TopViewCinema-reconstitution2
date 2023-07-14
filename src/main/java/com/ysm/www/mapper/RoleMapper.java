package com.ysm.www.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysm.www.entity.po.Role;
import com.ysm.www.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
//    default List<Role> getRoleListById(Integer id){
//        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
//        wrapper.select(Role::getId,Role::getRoleName,Role::getRoleDetail)
//    }
}
