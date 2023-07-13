package com.ysm.www.auth.config;

import java.util.List;

/**
 * @author Albumen
 * @date 2020/3/2
 */
public interface IPermissionPath {
    /**
     * 返回一个允许通过的路径的列表
     *
     * @return 路径列表
     */
    List<String> permitPath();

    /**
     * 返回一个安全框架忽略的路径的列表
     *
     * @return 路径列表
     */
    List<String> ignorePath();

    /**
     * 返回一个需要进入鉴权的路径的列表
     *
     * @return 路径列表
     */
    List<String> authenticatedPath();
}
