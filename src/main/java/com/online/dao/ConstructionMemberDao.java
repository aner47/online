package com.online.dao;

import com.online.entity.online.constructionsite.ConstructionMember;

/**
 * 
 * @author zuozhiping
 * 
 * dao-机械数量
 *
 */
public interface ConstructionMemberDao extends BaseDao<ConstructionMember, Integer> {

	void deleteConstructionMemberByEnterpriseId(Integer id) throws Exception;

}