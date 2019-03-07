package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.BaseInformationDao;
import com.online.entity.online.BaseInformation;
import com.online.service.BaseInformationService;
import com.online.util.SpringUtils;
/**
 * 
 * @author 左志平
 * 
 * 生产服务实现
 *
 */
@Service("baseInformationServiceImpl")
public class BaseInformationServiceImpl extends BaseServiceImpl<BaseInformation, Integer> implements BaseInformationService {

	@Autowired
	private BaseInformationDao baseInformationDao;
	
	@Override
	public BaseInformation findListByProjectAndEnterprise() {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<BaseInformation> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}
	@Override
	public BaseInformation findListByProjectAndEnterprise(Integer ProjectId,Integer EnterpriseId) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,ProjectId));
		filters.add(new Filter("enterprise.id",Operator.eq,EnterpriseId));
		List<BaseInformation> findList = findList(null, filters, null);
		if(findList != null && findList.size() > 0 ){
			return findList.get(0);
		}else{
			return null;
		}
	}
	
	@Override
	public void deleteBaseInformationByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		baseInformationDao.deleteBaseInformationByEnterpriseId(id);
	}
	
}
