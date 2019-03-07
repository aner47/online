package com.online.service.impl;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.StatisticsDao;
import com.online.entity.online.Condition;
import com.online.service.StatisticsService;
/**
 * 填报统计
 */
@Service("statisticsServiceImpl")
public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired
	private StatisticsDao statisticsDao;

	public List<Map<String, Object>> findStatistics(String type,Condition condition){
		return statisticsDao.findStatistics(type, condition);
		
	}
	
}
