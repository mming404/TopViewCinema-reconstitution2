package com.ysm.www.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysm.www.entity.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    default List<UserRole> listByUserId(Integer id){
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(UserRole::getId,UserRole::getUserId,UserRole::getRoleId)
                .eq(UserRole::getUserId,id);
        return selectList(wrapper);
    }
}
