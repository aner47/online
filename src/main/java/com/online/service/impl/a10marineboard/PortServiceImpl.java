package com.online.service.impl.a10marineboard;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.entity.DictionaryData;
import com.online.entity.online.Enterprise;
import com.online.entity.online.a10marineboard.Port;
import com.online.service.EnterpriseService;
import com.online.service.SelectService;
import com.online.service.a10marineboard.PortService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 港口信息服务实现
 *
 */
@Service("portServiceImpl")
public class PortServiceImpl extends BaseServiceImpl<Port, Integer> implements PortService,SelectService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Override
	public Port savePort(Port port) throws Exception {
		//郑有权
		String enterpriseName = port.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<Port> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("港口名称已存在");
		}
		port.setEnterprise(enterprise);
		port.setProject(SpringUtils.getCurrentProject());
		return save(port);
	}


	@Override
	public Port updatePort(Port port) throws Exception {
		//郑有权
		String enterpriseName = port.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		Port portBack = find(port.getId());
		List<Port> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != portBack.getEnterprise().getId()){
				throw new Exception("港口名称已存在");
			}
		}
		port.setEnterprise(enterprise);
		return update(port,"project");
	}


	@Override
	public List<DictionaryData> getSelectData(String param) {
		//郑有权
		List<Port> ports = findByFilter(Filter.eq("project.id", Integer.parseInt(param)));
		
		List<DictionaryData>  list = new ArrayList<>();
		for (Port port : ports) {
			DictionaryData aData = new DictionaryData();
			aData.setCode(port.getEnterprise().getId().toString());
			aData.setCodeValue(port.getEnterprise().getName());
			list.add(aData);
		}
		return list;
		
	}
	
}
