package com.ysm.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysm.www.auth.util.SecurityUtil;
import com.ysm.www.entity.bo.FilmConditionBo;
import com.ysm.www.entity.po.Film;
import com.ysm.www.common.result.CommonResult;
import com.ysm.www.service.FilmService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        System.out.println("SecurityUtil.getUserId() = " + SecurityUtil.getUserId());
        return CommonResult.operateSuccess(filmService.listFilmByPage(pageNum));
    }

    @PreAuthorize("hasAnyAuthority('listFilmByCond')")
    @PostMapping("/listFilmByCond")
    public CommonResult<Page<Film>> listFilmByPageAndCondition(@Validated @RequestBody FilmConditionBo filmConditionBo){
        return CommonResult.operateSuccess(filmService.listFilmByCond(filmConditionBo));
    }
}
