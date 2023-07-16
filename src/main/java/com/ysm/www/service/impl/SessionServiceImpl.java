package com.ysm.www.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.www.entity.po.Session;
import com.ysm.www.mapper.SessionMapper;
import com.ysm.www.service.SessionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper, Session> implements SessionService {

    @Resource
    private SessionMapper sessionMapper;
    @Override
    public List<Session> listSessionByFilmId(Integer filmId) {
        return sessionMapper.listSessionByFilmId(filmId);
    }
}
