package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ConstructionMemberDao;
import com.online.entity.online.constructionsite.ConstructionMember;

/**
 * 
 * @author 左志平
 * 
 * dao-机械数量
 *
 */
@Repository("constructionMemberDaoImpl")
public class ConstructionMemberDaoImpl extends BaseDaoImpl<ConstructionMember, Integer> implements ConstructionMemberDao {

	@Override
	public void deleteConstructionMemberByEnterpriseId(Integer id) throws Exception {
		//郑有权
		String sql = "delete t1 FROM ol_construction_member t1 WHERE enterprise =?1";
		int t1 = entityManager.createNativeQuery(sql, ConstructionMember.class).setParameter(1, id).executeUpdate();
		
	}

	

   


}