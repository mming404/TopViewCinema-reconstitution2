package com.ysm.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysm.www.entity.po.Film;
import com.ysm.www.entity.result.CommonResult;
import com.ysm.www.service.FilmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/

@RestController
@RequestMapping("/api/film")
public class FilmController {

    @Resource
    private FilmService filmService;

    @PreAuthorize("hasAnyAuthority('listFilm')")
    @GetMapping("/listFilm")
    public CommonResult<Page<Film>> listFilmByPage(@RequestParam Integer pageNum){
        return CommonResult.operateSuccess(filmService.listFilmByPage(pageNum));
    }
}
