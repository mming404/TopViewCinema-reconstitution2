package com.ysm.www.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: TODO  关联用户与角色表  一对多
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/

@Data
@AllArgsConstructor
@ToString
@TableName("user_role")
public class UserRole {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Integer userId;
    @TableField("role_id")
    private Integer roleId;

}
