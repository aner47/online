package com.online.dao.impl.a9agriculture;

import org.springframework.stereotype.Repository;

import com.online.entity.online.a9agriculture.Plough;
import com.online.dao.a9agriculture.PloughDao;
import com.online.dao.impl.BaseDaoImpl;

/**
 * dao-耕地信息实现
 *
 */
@Repository("ploughDaoImpl")
public class PloughDaoImpl extends BaseDaoImpl<Plough, Integer> implements PloughDao {



}