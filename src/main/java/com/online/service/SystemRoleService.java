package com.online.service;

import java.util.List;

import com.online.entity.SystemRole;
/**
 * 
 * @author zuozhiping
 * 
 * 角色服务接口
 *
 */
public interface SystemRoleService extends BaseService<SystemRole, Integer> {

	void saveEntity(SystemRole systemRole, String menuIds);

	List<String> findRoles(int id);

	int deleteRoleMenu(int roleId);
	
	public int deleteUserRole(int roleId);
	
}