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
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/

@Data
@AllArgsConstructor
@TableName("film")
@ToString
public class Film {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("film_name")
    private String filmName;

    @TableField("class_type")
    private String classType;

    @TableField("director")
    private String director;

    @TableField("role_name")
    private String roleName;

    @TableField("timelong")
    private Integer timeLong;

    @TableField("start_date")
    private LocalDate startDate;

    @TableField("end_date")
    private LocalDate endDate;

    @TableField("money")
    private Integer money;

    @TableField("picture")
    private String picture;
}
