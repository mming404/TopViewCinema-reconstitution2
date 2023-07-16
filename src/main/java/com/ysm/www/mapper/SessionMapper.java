package com.ysm.www.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ysm.www.entity.po.Session;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Mapper
public interface SessionMapper extends BaseMapper<Session> {

    default List<Session> listSessionByFilmId(Integer filmId) {
        LambdaQueryWrapper<Session> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Session::getId, Session::getFilmId, Session::getSessionNumber, Session::getFilmDate, Session::getCinemaId)
                .eq(Session::getFilmId, filmId);
        return selectList(wrapper);
    }
}
