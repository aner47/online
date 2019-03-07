package com.online.dao.impl.a3roadbureau;

import org.springframework.stereotype.Repository;

import com.online.dao.a3roadbureau.CarWeightDao;
import com.online.dao.impl.BaseDaoImpl;
import com.online.entity.online.a3roadbureau.CarWeight;

/**
 * 交通局-车重信息表
 */
@Repository("carWeightDaoImpl")
public class CarWeightDaoImpl extends BaseDaoImpl<CarWeight, Integer> implements CarWeightDao {




}