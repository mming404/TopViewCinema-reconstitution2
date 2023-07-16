package com.ysm.www.controller;

import com.ysm.www.entity.po.Session;
import com.ysm.www.entity.result.CommonResult;
import com.ysm.www.service.SessionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Resource
    private SessionService sessionService;

    @PreAuthorize("hasAnyAuthority('listSessionByFilmId')")
    @GetMapping("/listSessionByFilmId")
    public CommonResult<List<Session>> listSessionByFilmId(@RequestParam Integer filmId){
        return CommonResult.operateSuccess(sessionService.listSessionByFilmId(filmId));
    }
}
