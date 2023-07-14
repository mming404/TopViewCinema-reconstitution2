package com.ysm.www.mapper;

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
}
