package com.online.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.online.entity.SystemMenu;
import com.online.entity.SystemUser;
import com.online.service.SystemMenuService;
import com.online.service.SystemUserService;

/**
 * @author xuchen
 * @time 2017年1月22日 上午9:21:33
 */

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private SystemMenuService systemMenuService;

	// 授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("开始授权");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		SystemUser user = (SystemUser) principals.getPrimaryPrincipal();
		Integer userId = user.getId();
		List<String> permsList = null;
		// 系统管理员，拥有最高权限
		if (userId>0) {
			List<SystemMenu> menuList = systemMenuService.queryList(new HashMap<String, Object>());
			permsList = new ArrayList<>(menuList.size());
			for (SystemMenu role : menuList) {
				try {
					permsList.add(role.getPerms());
				} catch (org.apache.shiro.authz.UnauthorizedException e) {

				}
			}
		} else {
			try {
				permsList = systemUserService.queryAllPerms(userId);
			} catch (org.apache.shiro.authz.UnauthorizedException e) {
				throw new UnauthorizedException("对不起你没有相关资源权限");
			}
		}
		// 用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for (String perms : permsList) {
			if (StringUtils.isBlank(perms)) {
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		info.setStringPermissions(permsSet);
		return info;
	}

	// 认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token中包含用户输入的用户名和密码
		// 在 shiro 中，用户需要提供 principals （身份）和 credentials（证明）给 shiro，从而应用能
		// 验证用户身份,principals：身份，即主体的标识属性，可以是任何东西，如用户名、邮箱等，唯一即可。
		// credentials：证明/凭证，即只有主体知道的安全值，如密码/数字证书等。
		// 最常见的 principals 和 credentials 组合就是用户名/密码了
		String username = (String) token.getPrincipal(); // 得到用户名
		String password = new String((char[]) token.getCredentials()); // 得到用户密码
		// 查询用户信息
		SystemUser user = systemUserService.findByUsername(username);
		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}
		return new SimpleAuthenticationInfo(user, password, getName());
	}

	// 清除缓存
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);

	}

}
