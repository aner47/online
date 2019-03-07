package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.LeakedCheckDao;
import com.online.entity.online.LeakedCheck;

/**
 * 
 * @author 左志平
 * 
 * dao-设备泄露检测实现
 *
 */
@Repository("leakedCheckDaoImpl")
public class LeakedCheckDaoImpl extends BaseDaoImpl<LeakedCheck, Integer> implements LeakedCheckDao {



}