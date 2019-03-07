package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.LogDao;
import com.online.entity.Log;

/**
 * 
 * @author 左志平
 * 
 * dao-日志实现
 *
 */
@Repository("logDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log, Long> implements LogDao {



}