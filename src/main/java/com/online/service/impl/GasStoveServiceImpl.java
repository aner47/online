package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Order;
import com.online.Filter.Operator;
import com.online.dao.ExhaustionHoleDao;
import com.online.dao.GasStoveDao;
import com.online.entity.online.BoilerInformation;
import com.online.entity.online.GasStove;
import com.online.entity.online.embeddable.EnterpriseEmissionsManagement;
import com.online.service.ExhaustionHoleService;
import com.online.service.GasStoveService;
import com.online.util.SpringUtils;
/**
 * 煤气炉
 */
@Service("gasStoveServiceImpl")
public class GasStoveServiceImpl extends BaseServiceImpl<GasStove, Integer> implements GasStoveService {

	@Autowired
	private ExhaustionHoleService exhaustionHoleService;
	
	@Autowired
	private GasStoveDao gasStoveDao;
	
	@Override
	public GasStove saveGasStove(GasStove gasStove, Integer exhaustionHoleId) {
		//郑有权
		int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("emissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<GasStove> lists = findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getNo() != null){
				no = lists.get(0).getNo()+1;
			}
			
		}
		gasStove.setNo(no);
		gasStove.setProject(SpringUtils.getCurrentProject());
		EnterpriseEmissionsManagement eem = new EnterpriseEmissionsManagement();
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(exhaustionHoleId != null ){
			eem.setExhaustionHole(exhaustionHoleService.find(exhaustionHoleId));
		}
		gasStove.setEmissionsManagement(eem);
		return save(gasStove);
	}

	@Override
	public GasStove updateGasStove(GasStove gasStove, Integer exhaustionHoleId) {
		int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("emissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<GasStove> lists = findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getNo() != null){
				no = lists.get(0).getNo()+1;
			}
			
		}
		if(gasStove.getNo() == null){
			gasStove.setNo(no);
		}
		
		
		GasStove gasStoveBack = update(gasStove, "emissionsManagement","project");
		gasStoveBack.getEmissionsManagement().setExhaustionHole(exhaustionHoleService.find(exhaustionHoleId));
		return gasStoveBack;
	}

	@Override
	public void deleteGasStoveByEnterpriseId(Integer id) throws Exception {
		// TODO Auto-generated method stub
		//郑有权
		gasStoveDao.deleteGasStoveByEnterpriseId(id);
	}
	
	
	
}
