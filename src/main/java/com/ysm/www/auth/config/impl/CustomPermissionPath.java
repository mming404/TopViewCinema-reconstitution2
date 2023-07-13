package com.ysm.www.auth.config.impl;


import com.ysm.www.auth.config.IPermissionPath;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Albumen
 * @date 2020/3/2
 */
@Component
public class CustomPermissionPath implements IPermissionPath {
	@Override
	public List<String> permitPath() {
		List<String> permitPath = new LinkedList<>();
		permitPath.add("/api/user/login**");
		return permitPath;
	}

	@Override
	public List<String> ignorePath() {
		List<String> ignorePath = new LinkedList<>();
		ignorePath.add("/druid/**");
		return ignorePath;
	}

	@Override
	public List<String> authenticatedPath() {
		List<String> authenticatedPath = new LinkedList<>();
		authenticatedPath.add("/api/**");
		return authenticatedPath;
	}
}
