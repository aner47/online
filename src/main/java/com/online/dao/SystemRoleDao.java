package com.online.dao;

import java.util.List;

import com.online.entity.SystemRole;

/**
 * 
 * @author zuozhiping
 * 
 * dao-角色
 *
 */
public interface SystemRoleDao extends BaseDao<SystemRole, Integer> {

	List<String> findRoles(int id);

	int deleteRoleMenu(int roleId);

	public int deleteUserRole(int roleId);

}