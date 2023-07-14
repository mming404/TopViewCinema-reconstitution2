package com.ysm.www.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ysm.www.entity.bo.FilmConditionBo;
import com.ysm.www.entity.po.Film;


/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
public interface FilmService extends IService<Film> {
    Page<Film> listFilmByPage(Integer pageNum);

    Page<Film> listFilmByCond(FilmConditionBo filmConditionBo);
}
