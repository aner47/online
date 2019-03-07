package com.online.dao.impl;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.online.dao.SystemMenuDao;
import com.online.entity.SystemMenu;

/**
 * 
 * @author 左志平
 * 
 * dao-菜单实现
 *
 */
@Repository("systemMenuDaoImpl")
public class SystemMenuDaoImpl extends BaseDaoImpl<SystemMenu, Integer> implements SystemMenuDao {

	@Override
	public List<SystemMenu> querylist(HashMap<String, Object> hashMap) {
		String sql="select menu from SystemMenu menu ";
		 try {
			TypedQuery<SystemMenu> createQuery = entityManager.createQuery(sql, SystemMenu.class);
			 List<SystemMenu> resultList = createQuery.getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryMenus(int id) {
		
			String sql="select rm.`system_menus` "
					+ "from sys_role_menu rm  "
					+ "left join sys_menu sm on rm.`system_menus`=sm.`id` "
					+ "left join sys_role sr on rm.`sys_role`=sr.`id`"
					+ "where sr.`id`= ?1 ";
			Query createNativeQuery = entityManager.createNativeQuery(sql);
			createNativeQuery.setParameter(1, id);
			createNativeQuery.unwrap(SQLQuery.class);
			List<String> resultList = createNativeQuery.getResultList();
			return resultList;
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SystemMenu> queryActivemenu() {
		String sql ="SELECT sm.`id`,sm.`name` FROM sys_menu sm WHERE pid='0' ORDER BY sm.`id` DESC ";
		Query createNativeQuery = entityManager.createNativeQuery(sql);
		createNativeQuery.unwrap(SQLQuery.class);
		List<SystemMenu> resultList = createNativeQuery.getResultList();
		return resultList;
	}

	@Override
	public List<SystemMenu> querymenuByPid(Integer pid) {
		//郑有权
		String sql="select * from sys_menu WHERE pid IN (SELECT id FROM sys_menu WHERE pid = ?1) "
				+ "OR id IN(SELECT id FROM sys_menu WHERE pid = ?2) order by sort";
		 try {
			Query createQuery = entityManager.createNativeQuery(sql,SystemMenu.class);
			createQuery.setParameter(1, pid);
			createQuery.setParameter(2, pid);
			createQuery.unwrap(SQLQuery.class);
			@SuppressWarnings("unchecked")
			List<SystemMenu> resultList = createQuery.getResultList();
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

