package com.online.service.impl;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.online.DataAccessException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.entity.DictionaryData;
import com.online.entity.online.Enterprise;
import com.online.entity.online.ExhaustionHole;
import com.online.service.ExhaustionHoleService;
import com.online.service.SelectService;
import com.online.util.SpringUtils;
/**
 * 
 * @author 左志平
 * 
 * 排放口服务实现
 *
 */
@Service("exhaustionHoleServiceImpl")
public class ExhaustionHoleServiceImpl extends BaseServiceImpl<ExhaustionHole, Integer> implements ExhaustionHoleService,SelectService {
	
	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		filters.add(new Filter("project.id",Operator.eq,SpringUtils.getProjectId()));
		filters.add(new Filter("enterprise.id",Operator.eq,SpringUtils.getPrincipal().getEnterpriseId()));
		List<ExhaustionHole> findList = findList(null, filters, null);
		List<DictionaryData>  list = new ArrayList<>();
		/*DictionaryData aData1 = new DictionaryData();
		aData1.setCode("-1");
		aData1.setCodeValue("无组织排放");
		list.add(aData1);*/
		for (ExhaustionHole g : findList) {
			DictionaryData aData = new DictionaryData();
			aData.setCode(g.getId().toString());
			aData.setCodeValue("排放口"+g.getExhaustionHoleNum());
			list.add(aData);
		}
		return list;
	}

	@Override
	public ExhaustionHole saveExhaustionHole(ExhaustionHole exhaustionHole) throws DataAccessException {
		//郑有权
		Enterprise enterprise = SpringUtils.getCurrentEnterprise();
		
		if(isScaleOut(exhaustionHole,enterprise)){
			throw new DataAccessException("经纬度范围超出企业10公里");
		}else{
			
		
		exhaustionHole.setEnterprise(enterprise);
		exhaustionHole.setProject(SpringUtils.getCurrentProject());
		
		List<Filter> fList = new ArrayList<Filter>();
		fList.add(new Filter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
		fList.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
		
		List<ExhaustionHole> list = findList(null, fList, null);
		
		Optional<Integer> largest = list.stream().map(o1->o1.getExhaustionHoleNum()).max(Integer::compareTo);
		Integer num = 0;
		if(largest.isPresent()){
			num = largest.get();
		}
		
		exhaustionHole.setExhaustionHoleNum(num+1);
		}
		return save(exhaustionHole);
	}

	@Override
	public ExhaustionHole updateExhaustionHole(ExhaustionHole exhaustionHole) throws DataAccessException {
		//郑有权
		Enterprise enterprise = SpringUtils.getCurrentEnterprise();
		ExhaustionHole exhaustionHoleBack = null;
		if(isScaleOut(exhaustionHole,enterprise)){
			throw new DataAccessException("经纬度范围超出企业10公里");
		}else{
			
			List<Filter> fList = new ArrayList<Filter>();
			fList.add(new Filter("enterprise.id", Operator.eq, SpringUtils.getPrincipal().getEnterpriseId()));
			fList.add(new Filter("project.id", Operator.eq, SpringUtils.getProjectId()));
			
			List<ExhaustionHole> list = findList(null, fList, null);
			
			Optional<Integer> largest = list.stream().map(o1->o1.getExhaustionHoleNum()).max(Integer::compareTo);
			Integer num = 0;
			if(largest.isPresent()){
				num = largest.get();
			}
			if(exhaustionHole.getExhaustionHoleNum() == null){
				exhaustionHole.setExhaustionHoleNum(num+1);
			}
			
			
		
			exhaustionHoleBack = update(exhaustionHole,"enterprise","project");
		}
		return exhaustionHoleBack;
	}
	
	
	
	/**
	 * 是否超出企业经纬度10公里
	 * @author 郑有权
	 * @date 创建时间：2018年5月17日 下午2:38:25 
	 * @param exhaustionHole		排放口
	 * @param enterprise	企业
	 * @return
	 */
	public boolean isScaleOut(ExhaustionHole exhaustionHole,Enterprise enterprise){
		
		double lat1 = exhaustionHole.getLatitude();
		double lng1 = exhaustionHole.getLongitude(); 
		double lat2 = enterprise.getAddress().getLatitude(); 
		double lng2 = enterprise.getAddress().getLongitude();
		
		if(GetDistance(lat1, lng1, lat2,  lng2)>10000){
			return true;
		}else{
			return false;
		}
	}
	
	
	private static double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 计算两个经纬度之间的距离
	 * 
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		// s = Math.round(s * 1000);
		s = Math.round(s);
		System.out.println("经纬度距离" + s);
		return s;
	}
	
}
