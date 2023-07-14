package com.ysm.www.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysm.www.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {
    default User getByName(String username){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(User::getId,User::getUsername,User::getPassword)
                .eq(User::getUsername,username);
        return selectOne(wrapper);
    }
}
