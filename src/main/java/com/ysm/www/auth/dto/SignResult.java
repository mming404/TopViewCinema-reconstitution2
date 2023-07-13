package com.ysm.www.auth.dto;

import lombok.Data;

/**
 * @author Albumen
 * @date on 2020/7/25
 */
@Data
public class SignResult {
    /**
     * 签发是否成功
     */
    private boolean success;

    /**
     * Token
     */
    private String token;

    /**
     * Token 过期时间（毫秒时间戳）
     */
    private Long expireAt;
}
