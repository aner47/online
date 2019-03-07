package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.EnterpriseDictionaryRepDao;
import com.online.entity.EnterpriseDictionaryRep;


/**
 * 企业名录库
 */
@Repository("enterpriseDictionaryRepDaoImpl")
public class EnterpriseDictionaryRepDaoImpl extends BaseDaoImpl<EnterpriseDictionaryRep, Integer> implements EnterpriseDictionaryRepDao {

}
