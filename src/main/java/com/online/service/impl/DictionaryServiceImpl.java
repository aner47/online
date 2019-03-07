package com.online.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.DictionaryDao;
import com.online.entity.Dictionary;
import com.online.entity.DictionaryData;
import com.online.service.DictionaryService;
import com.online.service.SelectService;
import com.online.util.SpringUtils;


/**
 * 字典数据
 * @author 左志平
 *
 */
@Service("dictionaryServiceImpl")
public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary, Integer> implements DictionaryService {
	@Autowired
	private DictionaryDao dictionaryDao;
	
	String firstParam = null ;
	String secondParam = null ;
	
	@Override
	public List<DictionaryData> findByname(String name,String param) {
		
		Dictionary dictionary = dictionaryDao.findByName(name);
		if(null == dictionary){
			return null;
		}
		String beanName = dictionary.getBeanName();
		if(StringUtils.isNotEmpty(beanName)){
			SelectService bean = (SelectService) SpringUtils.getBean(beanName);
			return bean.getSelectData(param);
		}
		
		List<DictionaryData> list = new ArrayList<>();
		list.addAll(dictionary.getDictionaryDatas());
		
		
		//如果参数有逗号分隔
		if(param != null&&param.indexOf(",")>0){
			
			
			String[] params  =param.split(",");
			if(params.length>1){
				String [] firstParams = params[0].split(":");
				String [] secondParams = params[1].split(":");
				if(firstParams.length>1){
					firstParam = firstParams[1];
				}
				if(secondParams.length>1){
					secondParam = secondParams[1];
				}
				
				
			}
			
				
			
				
			list = list.stream().filter((o1)->{if(StringUtils.isEmpty(firstParam)) return true;
			else return o1.getGroupbyName()!=null?o1.getGroupbyName().indexOf(firstParam)>=0:false;})
					.filter((o1)->{if(StringUtils.isEmpty(o1.getSecondGroupbyName())){return true;}
								else return o1.getSecondGroupbyName()!=null?o1.getSecondGroupbyName().indexOf(secondParam)>=0:false;
								})
					.sorted((o1,o2)->o1.getOrder()-o2.getOrder()).collect(Collectors.toList());
			
		}else{
			list = list.stream().filter((o1)->{if(StringUtils.isEmpty(param)) return true;
			else return o1.getGroupbyName()!=null?o1.getGroupbyName().indexOf(param)>=0:false;})
					.sorted((o1,o2)->o1.getOrder()-o2.getOrder()).collect(Collectors.toList());
		}
		
		
		return list;
	}
	
}
