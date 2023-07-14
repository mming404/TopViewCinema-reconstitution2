package com.ysm.www.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ysm.www.entity.po.Film;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Mapper
public interface FilmMapper extends BaseMapper<Film> {
    default Page<Film> listFilmsByCond(String filmName, String filmType, LocalDate startDate, Integer pageNum){
        LambdaQueryWrapper<Film> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Film::getId,Film::getFilmName,Film::getClassType,Film::getDirector,Film::getRoleName,
                Film::getTimeLong,Film::getStartDate,Film::getEndDate,Film::getMoney,Film::getPicture)
                .like(Film::getFilmName,filmName)
                .like(Film::getClassType,filmType)
                .like(startDate!= null,Film::getStartDate,startDate);

        Page<Film> filmPage = new Page<>(pageNum, 5);
        return selectPage(filmPage,queryWrapper);

    }
}
