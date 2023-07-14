package com.ysm.www.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/
@Data
@AllArgsConstructor
@TableName("t_order")
@ToString
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("film_id")
    private Integer filmId;
    @TableField("session_id")
    private Integer sessionId;
    @TableField("seat_id")
    private Integer seatId;

    @TableField("money")
    private Integer money;

    @TableField("order_date")
    private LocalDateTime orderDateTime;

    @TableField("state")
    private Integer state;

    @TableField("user_id")
    private Integer userId;
    @TableField("cinema_name")
    private String cinemaName;
}
