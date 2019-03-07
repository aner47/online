package com.online.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Order;
import com.online.Filter.Operator;
import com.online.dao.BoilerInformationDao;
import com.online.dao.DictionaryDao;
import com.online.entity.Dictionary;
import com.online.entity.DictionaryData;
import com.online.entity.online.BoilerInformation;
import com.online.entity.online.ExhaustionHole;
import com.online.entity.online.GovernanceMeasures;
import com.online.entity.online.PowerPlant;
import com.online.entity.online.Enterprise.EnterpriseType;
import com.online.entity.online.embeddable.EnterpriseEmissionsManagement;
import com.online.service.BoilerInformationService;
import com.online.service.ExhaustionHoleService;
import com.online.service.GovernanceMeasuresService;
import com.online.service.SelectService;
import com.online.util.SpringUtils;

/**
 * 
 * @author 左志平
 * 
 *         锅炉信息服务实现
 *
 */
@Service("boilerInformationServiceImpl")
public class BoilerInformationServiceImpl extends BaseServiceImpl<BoilerInformation, Integer>
		implements BoilerInformationService,SelectService {

	@Autowired
	private GovernanceMeasuresService governanceMeasuresService;
	@Autowired
	private ExhaustionHoleService exhaustionHoleService;
	@Autowired
	private BoilerInformationDao boilerInformationDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	
	
	
	@Override
	public BoilerInformation saveDetailBoilerInformation(BoilerInformation boilerInformation,
			Integer governanceMeasures1, Integer governanceMeasures2, Integer governanceMeasures3,
			Integer governanceMeasures4,
			Integer exhaustionHoleId,String allData) {
		int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("emissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<BoilerInformation> lists = findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getNo() != null){
				no = lists.get(0).getNo()+1;
			}
			
		}
		boilerInformation.setNo(no);
		
		//郑有权
		boilerInformation.setProject(SpringUtils.getCurrentProject());
		EnterpriseEmissionsManagement eem = new EnterpriseEmissionsManagement();
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(exhaustionHoleId != null ){
			eem.setExhaustionHole(exhaustionHoleService.find(exhaustionHoleId));
		}
		if(governanceMeasures1 != null ){
			eem.setGovernanceMeasures1(governanceMeasuresService.find(governanceMeasures1));
		}
		if(governanceMeasures2 != null ){
			eem.setGovernanceMeasures2(governanceMeasuresService.find(governanceMeasures2));
		}
		if(governanceMeasures3 != null ){
			eem.setGovernanceMeasures3(governanceMeasuresService.find(governanceMeasures3));
		}
		if(governanceMeasures4 != null ){
			eem.setGovernanceMeasures4(governanceMeasuresService.find(governanceMeasures4));
		}
		boilerInformation.setEmissionsManagement(eem);
		
		/*List<Fuel> fuelslist = JSON.parseArray(allData, Fuel.class);
		Set<Fuel> fuels = new HashSet<>();
		for(Fuel fuel:fuelslist){
			fuel.setBoilerInformation(boilerInformation);
			fuels.add(fuelService.save(fuel));
		}*/
		
		
		
		/*Fuel fuel = new Fuel();
		fuel.setFuelType("（1）煤炭");
		fuel.setFuelUnit("吨");
		fuel.setBoilerInformation(boilerInformation);
		Fuel fuel1 = new Fuel();
		fuel1.setFuelType("（2）煤炭");
		fuel1.setFuelUnit("吨222");
		fuel1.setBoilerInformation(boilerInformation);
		fuels.add(fuelService.save(fuel));
		fuels.add(fuelService.save(fuel1));*/
		
//		boilerInformation.setFuels(fuels);
		
		return save(boilerInformation);
	}
	
	@Override
	public BoilerInformation updateDetailBoilerInformation(BoilerInformation boilerInformation,
			Integer governanceMeasures1, Integer governanceMeasures2, Integer governanceMeasures3,
			Integer governanceMeasures4,
			Integer exhaustionHoleId) {
		EnterpriseEmissionsManagement eem = boilerInformation.getEmissionsManagement();
		if (eem == null) {
			eem = new EnterpriseEmissionsManagement();
			eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		}
		
		if (governanceMeasures1!=null) {
			eem.setGovernanceMeasures1(governanceMeasuresService.find(governanceMeasures1));
		}
		if (governanceMeasures2!=null) {
			eem.setGovernanceMeasures2(governanceMeasuresService.find(governanceMeasures2));
		}
		if (governanceMeasures3!=null) {
			eem.setGovernanceMeasures3(governanceMeasuresService.find(governanceMeasures3));
		}
		if (governanceMeasures4!=null) {
			eem.setGovernanceMeasures4(governanceMeasuresService.find(governanceMeasures4));
		}
			
			
		if (exhaustionHoleId != null) {
			eem.setExhaustionHole(exhaustionHoleService.find(exhaustionHoleId));
		}
		boilerInformation.setEmissionsManagement(eem);
		
		
		
		int no = 1;
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		filters.add(new Filter("emissionsManagement.enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		List<Order> listOrder = new ArrayList<>();
		listOrder.add(Order.desc("createDate"));
		List<BoilerInformation> lists = findList(null, filters, null);
		if(lists != null && lists.size()>0){
			if(lists.get(0).getNo() != null){
				no = lists.get(0).getNo()+1;
			}
			
		}
		if(boilerInformation.getNo() == null){
			boilerInformation.setNo(no);
		}
		
		
		return update(boilerInformation,"project");
	}
	
	@Override
	public BoilerInformation simplesave(BoilerInformation boilerInformation, String governanceMeasures1,
			String governanceMeasures2, String governanceMeasures3) {
		//郑有权
		boilerInformation.setProject(SpringUtils.getCurrentProject());
		EnterpriseEmissionsManagement eem = new EnterpriseEmissionsManagement();
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		ExhaustionHole exhaustionHole = new ExhaustionHole();
		if(boilerInformation.getEmissionsManagement() !=null && boilerInformation.getEmissionsManagement().getExhaustionHole() != null){
			exhaustionHole.setHeight(boilerInformation.getEmissionsManagement().getExhaustionHole().getHeight());
			eem.setExhaustionHole(exhaustionHoleService.save(exhaustionHole));
		}
        
        if(StringUtils.isNotBlank(governanceMeasures1)){
        	eem.setGovernanceMeasures1(governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures1,EnterpriseType.SIMPLE));
        }
        if(StringUtils.isNotBlank(governanceMeasures2)){
        	eem.setGovernanceMeasures2(governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures2,EnterpriseType.SIMPLE));
        }
        if(StringUtils.isNotBlank(governanceMeasures3)){
        	eem.setGovernanceMeasures3(governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures3,EnterpriseType.SIMPLE));
        }
		
		
		
		boilerInformation.setEmissionsManagement(eem);
		return save(boilerInformation);
	}
	
	@Override
	public BoilerInformation simpleupdate(BoilerInformation boilerInformation, String governanceMeasures1,
			String governanceMeasures2, String governanceMeasures3) {
		//郑有权
		EnterpriseEmissionsManagement emissionsManagement = boilerInformation.getEmissionsManagement();
		if(emissionsManagement == null){
			emissionsManagement = new EnterpriseEmissionsManagement();
			boilerInformation.setEmissionsManagement(emissionsManagement);
		}
		
		ExhaustionHole exhaustionHole = null;
		if(boilerInformation.getEmissionsManagement() != null && boilerInformation.getEmissionsManagement().getExhaustionHole()!= null){
			exhaustionHole = exhaustionHoleService.find(boilerInformation.getEmissionsManagement().getExhaustionHole().getId());
			exhaustionHole.setHeight(boilerInformation.getEmissionsManagement().getExhaustionHole().getHeight());
			emissionsManagement.setExhaustionHole(exhaustionHoleService.update(exhaustionHole));
		}
	    if(StringUtils.isNotBlank(governanceMeasures1)){
	    	GovernanceMeasures governanceMeasuresBack1= governanceMeasuresService.find(boilerInformation.getEmissionsManagement().getGovernanceMeasures1().getId());
	    	governanceMeasuresBack1.setName(governanceMeasures1);
	    	emissionsManagement.setGovernanceMeasures1(governanceMeasuresService.update(governanceMeasuresBack1));
	    	
	    }
	    if(StringUtils.isNotBlank(governanceMeasures2)){
	    	GovernanceMeasures governanceMeasuresBack2= governanceMeasuresService.find(boilerInformation.getEmissionsManagement().getGovernanceMeasures2().getId());
	    	governanceMeasuresBack2.setName(governanceMeasures2);
	    	emissionsManagement.setGovernanceMeasures2(governanceMeasuresService.update(governanceMeasuresBack2));
	    	
	    }
	    if(StringUtils.isNotBlank(governanceMeasures3)){
	    	GovernanceMeasures governanceMeasuresBack3= governanceMeasuresService.find(boilerInformation.getEmissionsManagement().getGovernanceMeasures3().getId());
	    	governanceMeasuresBack3.setName(governanceMeasures3);
	    	emissionsManagement.setGovernanceMeasures3(governanceMeasuresService.update(governanceMeasuresBack3));
	    	
	    }
	    
		
		boilerInformation.setEmissionsManagement(emissionsManagement);
		return update(boilerInformation,"emissionsManagement","project");
	}
	
	@Override
	public BoilerInformation generalsave(BoilerInformation boilerInformation, String governanceMeasures1,
			String governanceMeasures2, String governanceMeasures3, Date putDate1, Date putDate2, Date putDate3) {
		//郑有权
		boilerInformation.setProject(SpringUtils.getCurrentProject());
		EnterpriseEmissionsManagement eem = boilerInformation.getEmissionsManagement();
		if(eem == null){
			eem = new EnterpriseEmissionsManagement();
			boilerInformation.setEmissionsManagement(eem);
		}
		eem.setEnterprise(SpringUtils.getCurrentEnterprise());
		if(eem != null && eem.getExhaustionHole() != null ){
			ExhaustionHole exhaustionHole = new ExhaustionHole();
			exhaustionHole.setHeight(boilerInformation.getEmissionsManagement().getExhaustionHole().getHeight());
			eem.setExhaustionHole(exhaustionHoleService.save(exhaustionHole));
		}
		
		 if(StringUtils.isNotBlank(governanceMeasures1)){
			 GovernanceMeasures g1 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures1,EnterpriseType.GENERAL);
			 g1.setPutDate(putDate1);
			 eem.setGovernanceMeasures1(g1);
		 }
        if(StringUtils.isNotBlank(governanceMeasures2)){
        	GovernanceMeasures g2 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures2,EnterpriseType.GENERAL);
    		g2.setPutDate(putDate2);
    		eem.setGovernanceMeasures2(g2);
        }
        if(StringUtils.isNotBlank(governanceMeasures3)){
        	GovernanceMeasures g3 = governanceMeasuresService.saveGovernanceMeasuresByName(governanceMeasures3,EnterpriseType.GENERAL);
    		g3.setPutDate(putDate3);
    		eem.setGovernanceMeasures3(g3);
        }
		boilerInformation.setEmissionsManagement(eem);
		return save(boilerInformation);
	}

	@Override
	public BoilerInformation generalupdate(BoilerInformation boilerInformation, String governanceMeasures1,
			String governanceMeasures2, String governanceMeasures3, Date putDate1, Date putDate2, Date putDate3) {
		//郑有权
		EnterpriseEmissionsManagement emissionsManagement = boilerInformation.getEmissionsManagement();
		if(emissionsManagement == null){
			emissionsManagement = new EnterpriseEmissionsManagement();
			boilerInformation.setEmissionsManagement(emissionsManagement);
		}
		ExhaustionHole exhaustionHole = null;
		if(boilerInformation.getEmissionsManagement() != null && boilerInformation.getEmissionsManagement().getExhaustionHole()!= null){
			exhaustionHole = exhaustionHoleService.find(boilerInformation.getEmissionsManagement().getExhaustionHole().getId());
			exhaustionHole.setHeight(boilerInformation.getEmissionsManagement().getExhaustionHole().getHeight());
			emissionsManagement.setExhaustionHole(exhaustionHoleService.update(exhaustionHole));
		}
		if(StringUtils.isNotBlank(governanceMeasures1)){
			GovernanceMeasures g1 =  boilerInformation.getEmissionsManagement().getGovernanceMeasures1();
			g1.setName(governanceMeasures1);
			g1.setPutDate(putDate1);
			emissionsManagement.setGovernanceMeasures1(governanceMeasuresService.update(g1));
		}
		
		if(StringUtils.isNotBlank(governanceMeasures2)){
			GovernanceMeasures g2 =  boilerInformation.getEmissionsManagement().getGovernanceMeasures2();
			g2.setName(governanceMeasures2);
			g2.setPutDate(putDate2);
			emissionsManagement.setGovernanceMeasures2(governanceMeasuresService.update(g2));
		}
		if(StringUtils.isNotBlank(governanceMeasures3)){
			GovernanceMeasures g3 =  boilerInformation.getEmissionsManagement().getGovernanceMeasures3();
			g3.setName(governanceMeasures3);
			g3.setPutDate(putDate3);
			emissionsManagement.setGovernanceMeasures3(governanceMeasuresService.update(g3));
		}
		boilerInformation.setEmissionsManagement(emissionsManagement);
		return update(boilerInformation);
	}
	

	/*@Transactional
	public void updateBoilerInformation(BoilerInformation boilerInformation) {
		BoilerInformation boilerInformationEntity = find(boilerInformation.getId());
		EnterpriseEmissionsManagement eem = boilerInformationEntity.getEmissionsManagement();
		if (eem == null) {
			eem = new EnterpriseEmissionsManagement();
			boilerInformationEntity.setEmissionsManagement(eem);
		}
		EnterpriseEmissionsManagement enterpriseEmissionsManagement = boilerInformation.getEmissionsManagement();
		if (enterpriseEmissionsManagement != null) {

			if (enterpriseEmissionsManagement.getGovernanceMeasures1()!=null&&enterpriseEmissionsManagement.getGovernanceMeasures1().getId() != null) {
				GovernanceMeasures g1 = governanceMeasuresService
						.find(enterpriseEmissionsManagement.getGovernanceMeasures1().getId());
				eem.setGovernanceMeasures1(g1);
			}else{
				eem.setGovernanceMeasures1(null);
			}
			
			if (enterpriseEmissionsManagement.getGovernanceMeasures2()!=null&&enterpriseEmissionsManagement.getGovernanceMeasures2().getId() != null) {
				GovernanceMeasures g2 = governanceMeasuresService
						.find(enterpriseEmissionsManagement.getGovernanceMeasures2().getId());
				eem.setGovernanceMeasures2(g2);
			}else{
				eem.setGovernanceMeasures2(null);
			}
			if (enterpriseEmissionsManagement.getGovernanceMeasures3()!=null&&enterpriseEmissionsManagement.getGovernanceMeasures3().getId() != null) {
				GovernanceMeasures g3 = governanceMeasuresService
						.find(enterpriseEmissionsManagement.getGovernanceMeasures3().getId());
				eem.setGovernanceMeasures3(g3);
			}else{
				eem.setGovernanceMeasures3(null);
			}
			
			if (enterpriseEmissionsManagement.getExhaustionHole()!=null&&enterpriseEmissionsManagement.getExhaustionHole().getId() != null) {
				ExhaustionHole e = exhaustionHoleService.find(enterpriseEmissionsManagement.getExhaustionHole().getId());
				eem.setExhaustionHole(e);
			}else{
				eem.setExhaustionHole(null);
			}
		}
		 super.update(boilerInformation, "emissionsManagement","project");
		//

	}*/

    @Override
    public List<BoilerInformation> findSourceNameById(Integer id) {
        return  boilerInformationDao.findSourceNameById(id);
    }

	@Override
	public List<DictionaryData> getSelectData(String param) {
		if(param != null && param.indexOf(":") != -1){
		String name = param.substring(0, param.indexOf(":"));
		String  groupName = param.substring(param.indexOf(":")+1);
		
		//郑有权
//		if("（2）流化床炉".equals(param) || "（3）自动炉排层燃炉".equals(param) || "（4）手动炉排层燃炉".equals(param) || "（7）生物质锅炉".equals(param)){
//			param = "（1）煤粉炉";
//		}else if("（99）其它锅炉".equals(param)){
//			param = "燃料类型";
//		}
		Dictionary dictionary = dictionaryDao.findByName(name);
		if(null == dictionary){
			return null;
		}
		
		List<DictionaryData> list = new ArrayList<>();
		for(DictionaryData dictionaryData:dictionary.getDictionaryDatas()){
			if(dictionaryData.getGroupbyName().indexOf(groupName) != -1){
				list.add(dictionaryData);
			}
		}
//		list.addAll(dictionary.getDictionaryDatas());
		list.sort((o1,o2)->o1.getOrder()-o2.getOrder());
		return list;
		
		}else{
			return null;
		}
	}

	

	

	

	

	
	
	
	
	

}
