package com.online.service.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.SystemMenuDao;
import com.online.entity.DictionaryData;
import com.online.entity.SystemMenu;
import com.online.service.SelectService;
import com.online.service.SystemMenuService;
/**
 * 
 * @author 左志平
 * 
 * 菜单服务实现
 *
 */
@Service("systemMenuServiceImpl")
public class SystemMenuServiceImpl extends BaseServiceImpl<SystemMenu, Integer> 
	implements SystemMenuService , SelectService {

	@Autowired
	private SystemMenuDao systemMenuDao;
	

	public List<SystemMenu> queryList(HashMap<String, Object> hashMap) {
		return systemMenuDao.querylist(hashMap);
	}


	
	public List<String> queryMenus(int id) {
		return systemMenuDao.queryMenus(id);
	}



	@Override
	public List<SystemMenu> queryActivemenu() {
		return systemMenuDao.queryActivemenu();
	}



	@Override
	public List<SystemMenu> querymenuByPid(Integer pid) {
		//郑有权
		return systemMenuDao.querymenuByPid(pid);
	}



	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		List<DictionaryData> datas = new ArrayList<>();
		if(StringUtils.isNotEmpty(param)){
			String[] split = param.split(",");
			for (String str : split) {
				String[] split2 = str.split(":");
				if(split2.length==2){
					filters.add(new Filter(split2[0],Operator.eq,split2[1]));
				}
			}
		}
		List<SystemMenu> findList = findList(null, filters, null);
		if (findList != null) {
			findList.forEach((f) -> {
				DictionaryData aData = new DictionaryData();
				aData.setCode(f.getId() + "");
				aData.setCodeValue(f.getName());
				datas.add(aData);
			});
		}
		return datas;
	}

}