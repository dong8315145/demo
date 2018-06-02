package com.example.demo.common.permissionfilter;

import com.example.demo.common.constant.CommonConstants;
import com.example.demo.common.exception.PermissionException;
import com.example.demo.common.menufilter.Menu;
import com.example.demo.dao.mapper.RoleMenuMapper;
import com.example.demo.dao.mapper.RoleMenuPermissionMapper;
import com.example.demo.dao.mapper.SysMenuMapper;
import com.example.demo.dao.mapper.SysRoleMapper;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PermissionInterceptor extends HandlerInterceptorAdapter {


	Integer k;

	@Resource
	private SysRoleMapper sysRoleMapper;

	@Resource
	private SysMenuMapper sysMenuMapper;

	@Resource
	private RoleMenuMapper roleMenuMapper;

	@Resource
	private RoleMenuPermissionMapper roleMenuPermissionMapper;

	@Resource
	protected Message message;

	private String[] menuIdMappings;

	private String[] excludeMenuIdMappings;

	private Map<String, Object> params = Maps.newHashMap();

	private PathMatcher matcher = new AntPathMatcher();

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		if (!(arg2 instanceof HandlerMethod)) {
			return super.preHandle(arg0, arg1, arg2);
		}
		params.clear();
		params.put("flag", CommonConstants.VALID);
		params.put("id", WebUtils.getSessionAttribute(arg0, CommonConstants.LOGIN_USER));
		List<Object> list = new ArrayList<Object>();
		 sysRoleMapper.selectCount(params);
		if (CollectionUtils.isEmpty(list)) {
			this.throwException(arg0);
		}
		HandlerMethod hm = (HandlerMethod) arg2;
		if (ArrayUtils.isNotEmpty(menuIdMappings)) {
			String uri = arg0.getRequestURI().substring(arg0.getContextPath().length());
			if (ArrayUtils.isNotEmpty(excludeMenuIdMappings)) {
				for (String excludeMenuIdMapping : excludeMenuIdMappings) {
					if (matcher.match(excludeMenuIdMapping, uri)) {
						return super.preHandle(arg0, arg1, arg2);
					}
				}
			}
			for (String menuIdMapping : menuIdMappings) {
				if (matcher.match(menuIdMapping, uri)) {
					String menuId = arg0.getParameter("mid");
					if (StringUtils.isBlank(menuId)) {
						this.throwException(arg0);
					}
					return super.preHandle(arg0, arg1, arg2);
				}
			}
		}
		Menu m = hm.getMethodAnnotation(Menu.class);
		if (m == null) {
			m = AnnotationUtils.getAnnotation(hm.getBeanType(), Menu.class);
		}
		if (m != null && StringUtils.isNotBlank(m.value())) {
			this.check(arg0, m.value(), hm, list.get(0));
		}
		return super.preHandle(arg0, arg1, arg2);
	}

	private void check(HttpServletRequest arg0, String menuId, HandlerMethod hm, Object roleId) throws Exception {
		params.clear();
		params.put("menuId", menuId);
		if (sysMenuMapper.selectCount(params) == 0) {
			params.clear();
			params.put("roleId", roleId);
			if (roleMenuMapper.selectCount(params) == 0) {
				// 通过检查不用拦截
			} else {
				this.throwException(arg0);
			}

		} else {
			this.throwException(arg0);
		}
		Permission p = hm.getMethodAnnotation(Permission.class);
		if (p == null) {
			p = AnnotationUtils.getAnnotation(hm.getBeanType(), Permission.class);
		}
		params.clear();
		if (p != null && StringUtils.isNotBlank(p.value()) && roleMenuPermissionMapper.selectCount(params) == 0) {
			this.throwException(arg0);
		}

	}

	private void throwException(HttpServletRequest request) throws Exception {
		new PermissionException("无操作权限");
	}

	public String[] getMenuIdMappings() {
		return menuIdMappings;
	}

	public void setMenuIdMappings(String[] menuIdMappings) {
		this.menuIdMappings = menuIdMappings;
	}

	public String[] getExcludeMenuIdMappings() {
		return excludeMenuIdMappings;
	}

	public void setExcludeMenuIdMappings(String[] excludeMenuIdMappings) {
		this.excludeMenuIdMappings = excludeMenuIdMappings;
	}
}
