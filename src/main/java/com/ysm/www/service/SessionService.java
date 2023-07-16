package com.ysm.www.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ysm.www.entity.po.Session;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
public interface SessionService extends IService<Session> {
    List<Session> listSessionByFilmId(Integer filmId);
}
