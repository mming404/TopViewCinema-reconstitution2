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
@ToString
@TableName("seat")
public class Seat {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("seat_number")
    private Integer seatNumber;
    @TableField("session_id")
    private Integer sessionId;
    @TableField("state")
    private Integer state;
    @TableField("cinema_id")
    private Integer cinemaId;
}
