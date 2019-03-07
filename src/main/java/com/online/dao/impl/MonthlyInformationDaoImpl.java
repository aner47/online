package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.MonthlyInformationDao;
import com.online.entity.online.MonthlyInformation;

/**
 * 
 * @author 左志平
 * 
 * dao-分月数据实现
 *
 */
@Repository("monthlyInformationDaoImpl")
public class MonthlyInformationDaoImpl extends BaseDaoImpl<MonthlyInformation, Integer> implements MonthlyInformationDao {



}