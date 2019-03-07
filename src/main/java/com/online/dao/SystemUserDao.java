package com.online.dao;

import java.util.List;
import java.util.Map;

import com.online.entity.SystemMenu;
import com.online.entity.SystemUser;

/**
 * 
 * @author zuozhiping
 * 
 * dao-用户
 *
 */
public interface SystemUserDao extends BaseDao<SystemUser, Integer> {

	/**
	 * 登录操作
	 */
	SystemUser findByUsername(String username);

	/**
	 * 查询所有用户拥有的权限
	 */
	List<String> queryAllPerms(Integer id);

	/**
	 * 查询所有用户拥有的菜单项
	 */
	List<SystemMenu> queryAllPerm(Integer id);

	List<SystemUser> queryForms(String username, String phone);
	
	List<SystemUser> queryProjectInvitationCode(String projectCode);

	List<Map<Integer,String>> getUsernameById();
	
	public List<SystemUser> findByRole(String userType,String roleName);

	
	int updateProjectInvitationCode(String oldprojectCode,String projectCode);


}