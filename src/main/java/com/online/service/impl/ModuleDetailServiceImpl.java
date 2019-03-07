package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.ModuleDetailDao;
import com.online.entity.DictionaryData;
import com.online.entity.online.ModuleDetail;
import com.online.service.ModuleDetailService;
import com.online.service.SelectService;
/**
 * 
 * @author 左志平
 * 
 * 模块明细服务实现
 *
 */
@Service("moduleDetailServiceImpl")
public class ModuleDetailServiceImpl extends BaseServiceImpl<ModuleDetail, Integer> implements SelectService, ModuleDetailService {

    @Autowired
    private ModuleDetailDao moduleDetailDao;
    
    @Override
    public void deleteDetails(Integer ids) {
        moduleDetailDao.deleteDetails(ids);
        
    }

	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		if(StringUtils.isNotEmpty(param)){
			String[] split = param.split(",");
			for (String str : split) {
				String[] split2 = str.split(":");
				filters.add(new Filter(split2[0],Operator.eq,split2[1]));
			}
		}
		List<ModuleDetail> findList = findList(null, filters, null);
		List<DictionaryData>  list = new ArrayList<>();
		for (ModuleDetail moduleDetail : findList) {
			DictionaryData aData = new DictionaryData();
			aData.setCode(moduleDetail.getId()+"");
			aData.setCodeValue(moduleDetail.getHead());
			list.add(aData);
		}
		return list;
	}
	
	@Override
	public List<ModuleDetail> getListByModuleId(int moduleId){
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("moduleInfo.id", Operator.eq, moduleId));
		return findList(null, filters, null);
	}
    
	
}
