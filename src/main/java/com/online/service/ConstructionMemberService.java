package com.online.service;

import com.online.entity.online.constructionsite.ConstructionMember;
/**
 * 
 * @author zuozhiping
 * 
 * 机械数量
 *
 */
public interface ConstructionMemberService extends BaseService<ConstructionMember, Integer> {

	
	void deleteConstructionMemberByEnterpriseId(Integer id) throws Exception;
	
}