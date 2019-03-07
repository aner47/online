package com.online.dao;

import java.util.List;
import java.util.Map;

import com.online.entity.online.Condition;
import com.online.entity.online.Statistics;

/**
 * 填报统计
 */
public interface StatisticsDao extends BaseDao<Statistics, Integer>{

	public List<Map<String, Object>> findStatistics(String type,Condition condition);
}