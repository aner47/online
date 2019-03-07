package com.online.dao.impl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.online.dao.SystemRoleDao;
import com.online.entity.SystemRole;

/**
 * 
 * @author 左志平
 * 
 * dao-角色实现
 *
 */
@Repository("systemRoleDaoImpl")
public class SystemRoleDaoImpl extends BaseDaoImpl<SystemRole, Integer> implements SystemRoleDao {

	@Override
	@SuppressWarnings("unchecked")
	@Transactional
	public List<String> findRoles(int id) {
		try {
			String sql="SELECT ur.`roles`"
					+ "FROM sys_user_role ur "
					+ "LEFT JOIN sys_user su "
					+ "ON ur.`sys_user`=su.`id`"
					+ "LEFT JOIN sys_role sr "
					+ "ON ur.`roles`=sr.`id` "
					+ "WHERE  ur.`sys_user`= ?2 ";
			Query createNativeQuery = entityManager.createNativeQuery(sql);
			createNativeQuery.setParameter(2, id);
			createNativeQuery.unwrap(SQLQuery.class);
			List<String> resultList = createNativeQuery.getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteRoleMenu(int roleId) {
		String hql ="delete from sys_role_menu where sys_role=:roleId";
		Query query = entityManager.createNativeQuery(hql).setParameter("roleId",roleId);
		int flag = query.executeUpdate();
		return flag;
	}


	@Override
	public int deleteUserRole(int roleId) {
		String hql =" delete from sys_user_role where roles=:roleId";
		Query query = entityManager.createNativeQuery(hql).setParameter("roleId", roleId);
		int flag = query.executeUpdate();
		return flag;
	}

}