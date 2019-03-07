package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ModuleInfoDao;
import com.online.entity.online.ModuleInfo;

/**
 * 
 * @author 左志平
 * 
 * dao-模块信息实现
 *
 */
@Repository("moduleInfoDaoImpl")
public class ModuleInfoDaoImpl extends BaseDaoImpl<ModuleInfo, Integer> implements ModuleInfoDao {



}