package com.ysm.www.entity.dto;

import lombok.Data;

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