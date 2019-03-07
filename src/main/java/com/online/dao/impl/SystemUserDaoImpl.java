package com.online.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.online.dao.SystemUserDao;
import com.online.entity.SystemMenu;
import com.online.entity.SystemUser;

/**
 * 
 * @author 左志平
 * 
 * dao-用户实现
 *
 */
@Repository("systemUserDaoImpl")
public class SystemUserDaoImpl extends BaseDaoImpl<SystemUser, Integer> implements SystemUserDao {


	public SystemUser findByUsername(String username){
		String jpql = "select systemuser from SystemUser systemuser where username=:username";
		try {
			return entityManager.createQuery(jpql, SystemUser.class).setParameter("username", username).getSingleResult();
		} catch (Exception e) {
			return null ; 
		}
		 
	}

	public List<String> queryAllPerms(Integer id) {
		try {
			String jpql="SELECT m.`perms` FROM sys_user_role ur  "
					+ "LEFT JOIN sys_role_menu rm ON ur.`roles` = rm.`sys_role` "
					+ "LEFT JOIN sys_menu m ON rm.`system_menus` = m.`id` "
					+ "WHERE ur.`sys_user`=?4";
			Query createNativeQuery = entityManager.createNativeQuery(jpql);
			createNativeQuery.setParameter(4, id);
			createNativeQuery.unwrap(SQLQuery.class);
			@SuppressWarnings("unchecked")
			List<String> resultList = createNativeQuery.getResultList();
			
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	/**
	 * 查询所有用户拥有的菜单项
	 */
	@Override
	public List<SystemMenu> queryAllPerm(Integer id) {
		try {
			String jpql="SELECT distinct m.* FROM sys_user_role ur  "
					+ "INNER JOIN sys_role_menu rm ON ur.`roles` = rm.`sys_role` "
					+ "INNER JOIN sys_menu m ON rm.`system_menus` = m.`id` "
					+ "WHERE ur.`sys_user`= ?1 order by m.id  ";
			Query createNativeQuery = entityManager.createNativeQuery(jpql,SystemMenu.class);
			createNativeQuery.setParameter(1, id);
			createNativeQuery.unwrap(SQLQuery.class);
			@SuppressWarnings("unchecked")
			List<SystemMenu> resultList = createNativeQuery.getResultList();
			
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SystemUser> queryForms(String username, String phone) {
		String sql="select su.* from sys_user su where username like '%?2%' and phone like '%?3%'";
		Query createNativeQuery = entityManager.createNativeQuery(sql, SystemUser.class);
		createNativeQuery.setParameter(2, "%username%");
		createNativeQuery.setParameter(3, "%phone%");
		createNativeQuery.unwrap(SQLQuery.class);
		@SuppressWarnings("unchecked")
		List<SystemUser> resultList = createNativeQuery.getResultList();
		return resultList;
	}

	@Override
	public List<SystemUser> queryProjectInvitationCode(String projectCode) {
		String sql="select su.* from sys_user su where project_invitation_code =?1";
		Query createNativeQuery = entityManager.createNativeQuery(sql, SystemUser.class);
		createNativeQuery.setParameter(1, projectCode);
		createNativeQuery.unwrap(SQLQuery.class);
		@SuppressWarnings("unchecked")
		List<SystemUser> resultList = createNativeQuery.getResultList();
		return resultList;
	}

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<Integer,String>> getUsernameById() {
        String sql="SELECT sys.id,sys.username  FROM `sys_user` sys ";
        Query createNativeQuery = entityManager.createNativeQuery(sql);
        createNativeQuery.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
       List<Map<Integer,String>> resultList = createNativeQuery.getResultList();
        return  resultList;
    }

	@Override
	public List<SystemUser> findByRole(String userType,String roleName) {
		
		StringBuffer jpql = new StringBuffer();
		
		try {
			jpql.append("SELECT sysuser.* FROM sys_user sysuser CROSS JOIN sys_user_role roles1_, sys_role systemrole2_ ");
			jpql.append(" WHERE sysuser.id = roles1_.sys_user"); 
			jpql.append(" AND roles1_.roles = systemrole2_.id ");
			if(StringUtils.isNotBlank(userType)){
				jpql.append(" AND sysuser.user_type = :userType"); 
			}
			if(StringUtils.isNotBlank(roleName)){
				jpql.append(" AND systemrole2_.`name`= :roleName");
			}
			
			
			Query createNativeQuery = entityManager.createNativeQuery(jpql.toString(),SystemUser.class);
			
			if(StringUtils.isNotBlank(userType)){
				createNativeQuery.setParameter("userType", userType);
			}
			if(StringUtils.isNotBlank(roleName)){
				createNativeQuery.setParameter("roleName", roleName);
			}
			createNativeQuery.unwrap(SQLQuery.class);
			@SuppressWarnings("unchecked")
			List<SystemUser> resultList = createNativeQuery.getResultList();
			
			return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateProjectInvitationCode(String oldprojectCode,String projectCode) {
		String sql="update sys_user su set su.project_invitation_code =?2 where su.project_invitation_code =?1";
		Query createNativeQuery = entityManager.createNativeQuery(sql, SystemUser.class);
		createNativeQuery.setParameter(1, oldprojectCode);
		createNativeQuery.setParameter(2, projectCode);
		createNativeQuery.unwrap(SQLQuery.class);
		@SuppressWarnings("unchecked")
		int resultList = createNativeQuery.executeUpdate();
		return resultList;
	}


}