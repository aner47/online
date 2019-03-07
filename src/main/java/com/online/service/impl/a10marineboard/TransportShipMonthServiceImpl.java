package com.online.service.impl.a10marineboard;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.entity.online.Enterprise;
import com.online.entity.online.a10marineboard.TransportShipMonth;
import com.online.service.EnterpriseService;
import com.online.service.a10marineboard.TransportShipMonthService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 客货运输船舶每月活动水平信息服务实现
 *
 */
@Service("transportShipMonthServiceImpl")
public class TransportShipMonthServiceImpl extends BaseServiceImpl<TransportShipMonth, Integer> implements TransportShipMonthService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Override
	public TransportShipMonth saveTransportShipMonth(TransportShipMonth transportShipMonth,Integer enterpriseId) throws Exception {
		//郑有权
		/*String enterpriseName = transportShipMonth.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<TransportShipMonth> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("港区名称已存在");
		}*/
		Enterprise enterprise = enterpriseService.find(enterpriseId);
		List<TransportShipMonth> lists = findList(null, Arrays.asList(Filter.eq("enterprise.id", enterprise.getId()),Filter.eq("shipType", transportShipMonth.getShipType())), null);
		if(lists !=null && lists.size()>0){
			throw new Exception("！港口【"+enterprise.getName()+"】中【"+transportShipMonth.getShipType()+"】已存在");
		}
		transportShipMonth.setEnterprise(enterprise);
		transportShipMonth.setProject(SpringUtils.getCurrentProject());
		return save(transportShipMonth);
	}


	@Override
	public TransportShipMonth updateTransportShipMonth(TransportShipMonth transportShipMonth,Integer enterpriseId) throws Exception {
		//郑有权
		/*String enterpriseName = transportShipMonth.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		TransportShipMonth transportShipMonthBack = find(transportShipMonth.getId());
		List<TransportShipMonth> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != transportShipMonthBack.getEnterprise().getId()){
				throw new Exception("港区名称已存在");
			}
		}*/
		Enterprise enterprise = enterpriseService.find(enterpriseId);
		TransportShipMonth transportShipMonthBack = find(transportShipMonth.getId());
		List<TransportShipMonth> lists = findList(null, Arrays.asList(Filter.eq("enterprise.id", enterprise.getId()),Filter.eq("shipType", transportShipMonth.getShipType())), null);
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getId() != transportShipMonthBack.getId()){
				throw new Exception("！港口【"+enterprise.getName()+"】中【"+transportShipMonth.getShipType()+"】已存在");
			}
		}
		transportShipMonth.setEnterprise(enterprise);
		return update(transportShipMonth,"project");
	}
	
}
