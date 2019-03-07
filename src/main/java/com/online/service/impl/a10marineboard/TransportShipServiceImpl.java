package com.online.service.impl.a10marineboard;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.entity.online.Enterprise;
import com.online.entity.online.a10marineboard.TransportShip;
import com.online.service.EnterpriseService;
import com.online.service.a10marineboard.TransportShipService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 客货运输船舶活动信息服务实现
 *
 */
@Service("transportShipServiceImpl")
public class TransportShipServiceImpl extends BaseServiceImpl<TransportShip, Integer> implements TransportShipService {
	
	@Autowired
	private EnterpriseService enterpriseService;
	
	@Override
	public TransportShip saveTransportShip(TransportShip transportShip,Integer enterpriseId) throws Exception {
		//郑有权
		/*String enterpriseName = transportShip.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		List<TransportShip> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			throw new Exception("港区名称已存在");
		}*/
		Enterprise enterprise = enterpriseService.find(enterpriseId);
		List<TransportShip> lists = findList(null, Arrays.asList(Filter.eq("enterprise.id", enterprise.getId()),Filter.eq("shipType", transportShip.getShipType())), null);
		if(lists !=null && lists.size()>0){
			throw new Exception("！港口【"+enterprise.getName()+"】中【"+transportShip.getShipType()+"】已存在");
		}
		transportShip.setEnterprise(enterprise);
		transportShip.setProject(SpringUtils.getCurrentProject());
		return save(transportShip);
	}


	@Override
	public TransportShip updateTransportShip(TransportShip transportShip,Integer enterpriseId) throws Exception {
		//郑有权
		Enterprise enterprise = enterpriseService.find(enterpriseId);
		TransportShip transportShipBack = find(transportShip.getId());
		//List<transportShip> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		List<TransportShip> lists = findList(null, Arrays.asList(Filter.eq("enterprise.id", enterprise.getId()),Filter.eq("shipType", transportShip.getShipType())), null);
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getId() != transportShipBack.getId()){
				throw new Exception("！港口【"+enterprise.getName()+"】中【"+transportShip.getShipType()+"】已存在");
			}
		}
		/*String enterpriseName = transportShip.getEnterprise().getName();
		Enterprise enterprise = enterpriseService.findSaveByEnterpriseName(enterpriseName);
		TransportShip transportShipBack = find(transportShip.getId());
		List<TransportShip> lists = findByFilter(Filter.eq("enterprise.id", enterprise.getId()));
		if(lists !=null && lists.size()>0){
			if(lists.get(0).getEnterprise().getId() != transportShipBack.getEnterprise().getId()){
				throw new Exception("港区名称已存在");
			}
		}*/
		transportShip.setEnterprise(enterprise);
		return update(transportShip,"project");
	}
	
}
