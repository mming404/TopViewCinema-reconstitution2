package com.ysm.www.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @Description: TODO  角色关联权限表  多对多
 * @Author MiSinG
 * @Date 2023/7/14
 * @Version V1.0
 **/

@Data
@AllArgsConstructor
@ToString
@TableName("role_permission")
public class RolePermission {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @TableField("role_id")
    private Integer roleId;

    @TableField("permission_id")
    private Integer permissionId;
}
