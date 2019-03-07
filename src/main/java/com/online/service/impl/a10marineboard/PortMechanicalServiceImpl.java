package com.online.service.impl.a10marineboard;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.entity.online.Enterprise;
import com.online.entity.online.a10marineboard.Port;
import com.online.entity.online.a10marineboard.PortMechanical;
import com.online.service.EnterpriseService;
import com.online.service.a10marineboard.PortMechanicalService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 港口机械信息服务实现
 *
 */
@Service("portMechanicalServiceImpl")
public class PortMechanicalServiceImpl extends BaseServiceImpl<PortMechanical, Integer> implements PortMechanicalService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Override
	public PortMechanical savePortMechanical(PortMechanical portMechanical,Integer enterpriseId) throws Exception {
		//郑有权
		Enterprise enterprise = enterpriseService.find(enterpriseId);
		List<PortMechanical> lists = findList(null, Arrays.asList(Filter.eq("enterprise.id", enterprise.getId()),Filter.eq("mechanicalType", portMechanical.getMechanicalType())), null);
		if(lists !=null && lists.size()>0){
			throw new Exception("！港口【"+enterprise.getName()+"】中【"+portMechanical.getMechanicalType()+"】已存在");
		}
		portMechanical.setEnterprise(enterprise);
		portMechanical.setProject(SpringUtils.getCurrentProject());
		return save(portMechanical);
	}


	@Override
	public PortMechanical updatePortMechanical(PortMechanical portMechanical,Integer enterpriseId) throws Exception {
		//郑有权
		Enterprise enterprise = enterpriseService.find(enterpriseId);
		PortMechanical portMechanicalBack = find(portMechanical.getId());
		//List<PortMechanical> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		List<PortMechanical> lists = findList(null, Arrays.asList(Filter.eq("enterprise.id", enterprise.getId()),Filter.eq("mechanicalType", portMechanical.getMechanicalType())), null);
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getId() != portMechanicalBack.getId()){
				throw new Exception("！港口【"+enterprise.getName()+"】中【"+portMechanical.getMechanicalType()+"】已存在");
			}
		}
		
		portMechanical.setEnterprise(enterprise);
		return update(portMechanical,"project");
	}
	
}
