package com.online.dao.impl.a3roadbureau;

import org.springframework.stereotype.Repository;

import com.online.dao.a3roadbureau.BusInventoryDao;
import com.online.dao.impl.BaseDaoImpl;
import com.online.entity.online.a3roadbureau.BusInventory;

/**
 * 交通局-公交车保有量
 */
@Repository("busInventoryDaoImpl")
public class BusInventoryDaoImpl extends BaseDaoImpl<BusInventory, Integer> implements BusInventoryDao {




}