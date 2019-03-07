package com.online.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.ConstructionMemberDao;
import com.online.entity.online.constructionsite.ConstructionMember;
import com.online.service.ConstructionMemberService;

/**
 *机械数量
 *
 */
@Service("constructionMemberServiceImpl")
public class ConstructionMemberServiceImpl extends BaseServiceImpl<ConstructionMember, Integer>
		implements  ConstructionMemberService {
	
	@Autowired
	private ConstructionMemberDao constructionMemberDao;

	
	@Override
	public void deleteConstructionMemberByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		constructionMemberDao.deleteConstructionMemberByEnterpriseId(id);
	}

	

	

}
