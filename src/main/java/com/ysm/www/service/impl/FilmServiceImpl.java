package com.ysm.www.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.www.controller.FilmController;
import com.ysm.www.entity.po.Film;
import com.ysm.www.mapper.FilmMapper;
import com.ysm.www.service.FilmService;
import org.springframework.stereotype.Service;


/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Service
public class FilmServiceImpl extends ServiceImpl<FilmMapper, Film> implements FilmService {
    @Override
    public Page<Film> listFilmByPage(Integer pageNum) {
        return page(new Page<>(pageNum,5));
    }
}
