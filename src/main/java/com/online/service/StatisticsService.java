package com.online.service;

import java.util.List;
import java.util.Map;

import com.online.entity.online.Condition;

/**
 * 填报统计
 */
public interface StatisticsService{

	public List<Map<String, Object>> findStatistics(String type,Condition condition);
}