package com.ysm.www.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/

@Data
@AllArgsConstructor
@TableName("cinema")
@ToString
public class Cinema {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("cinema_name")
    private String cinemaName;
}
