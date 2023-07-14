package com.ysm.www.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/**
 * @Description: TODO 电影场次
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
@ToString
@TableName("session")
public class Session {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("film_id")
    private Integer filmId;

    @TableField("session_number")
    private Integer sessionNumber;

    @TableField("film_date")
    private LocalDate filmDate;

    @TableField("cinema_id")
    private Integer cinemaId;
}
