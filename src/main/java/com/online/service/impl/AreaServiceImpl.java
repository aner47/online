package com.online.service.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.AreaDao;
import com.online.entity.Area;
import com.online.entity.DictionaryData;
import com.online.service.AreaService;
import com.online.service.SelectService;
import com.online.util.AreaUtils;
import com.online.util.JsonUtil;
import com.online.util.LatitudeUtils;
import com.online.util.PropertyUtil;
import com.online.util.SpringUtils;
/**
 * 
 * @author 左志平
 * 
 * 区域配置服务实现
 *
 */
@Service("areaServiceImpl")
public class AreaServiceImpl extends BaseServiceImpl<Area, Integer> implements AreaService,SelectService {
	
	@Autowired
	private AreaDao areaDao;

	//level:2,code:10001,name:1
	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		if(StringUtils.isNotEmpty(param)){
			String[] split = param.split(",");
			for (String str : split) {
				String[] split2 = str.split(":");
				if(split2.length==2){
					filters.add(new Filter(split2[0],Operator.eq,split2[1]));
				}
			}
		}else{
			filters.add(new Filter("level",Operator.eq,1));
		}
		String areaCode = PropertyUtil.getProperty("areaCode");
		
		List<Area> findList = findList(null, filters, null);
		if(StringUtils.isNotBlank(areaCode) && findList!=null){
			if(AreaUtils.LEVEL_PROVINCES.equals(AreaUtils.getAreaCodeLevel(areaCode))){
				if(AreaUtils.LEVEL_PROVINCES.equals(AreaUtils.getAreaCodeLevel(findList.get(0).getCode()))){
					findList = findList.stream().filter(o1->o1.getCode().substring(0,2).equals(areaCode.substring(0,2))).collect(Collectors.toList());
				}
			}else if(AreaUtils.LEVEL_CITY.equals(AreaUtils.getAreaCodeLevel(areaCode))){
				if(AreaUtils.LEVEL_PROVINCES.equals(AreaUtils.getAreaCodeLevel(findList.get(0).getCode()))){
					findList = findList.stream().filter(o1->o1.getCode().substring(0,2).equals(areaCode.substring(0,2))).collect(Collectors.toList());
				}else if(AreaUtils.LEVEL_CITY.equals(AreaUtils.getAreaCodeLevel(findList.get(0).getCode()))){
					findList = findList.stream().filter(o1->o1.getCode().substring(0,4).equals(areaCode.substring(0,4))).collect(Collectors.toList());
				}
			
			}else if(AreaUtils.LEVEL_COUNTY.equals(AreaUtils.getAreaCodeLevel(areaCode))){
				if(AreaUtils.LEVEL_PROVINCES.equals(AreaUtils.getAreaCodeLevel(findList.get(0).getCode()))){
					findList = findList.stream().filter(o1->o1.getCode().substring(0,2).equals(areaCode.substring(0,2))).collect(Collectors.toList());
				}else if(AreaUtils.LEVEL_CITY.equals(AreaUtils.getAreaCodeLevel(findList.get(0).getCode()))){
					findList = findList.stream().filter(o1->o1.getCode().substring(0,4).equals(areaCode.substring(0,4))).collect(Collectors.toList());
				}else if(AreaUtils.LEVEL_COUNTY.equals(AreaUtils.getAreaCodeLevel(findList.get(0).getCode()))){
					findList = findList.stream().filter(o1->o1.getCode().substring(0,6).equals(areaCode.substring(0,6))).collect(Collectors.toList());
				}
			}
		}
		List<DictionaryData>  list = new ArrayList<>();
		for (Area area : findList) {
			DictionaryData aData = new DictionaryData();
			aData.setCode(area.getCode());
			aData.setCodeValue(area.getName());
			aData.setOtherValue(JsonUtil.toJSON(area));
			list.add(aData);
		}
		return list;
	}

	@Override
	public Area findAreaByAreaCode(String areaCode) {
		return areaDao.findAreaByAreaCode(areaCode);
	}

	@Override
	public String getAreaCode(String lngs, String lats) throws IOException {
		// TODO Auto-generated method stub
		//郑有权
		return LatitudeUtils.getAreaCode(lngs, lats);
	}

	@Override
	public Map<String, String> getGeocoderLatitude(String address) {
		// TODO Auto-generated method stub
		//郑有权
		return LatitudeUtils.getGeocoderLatitude(address);
	}

	
}
