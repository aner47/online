package com.online.dao;

import java.util.List;

import com.online.entity.online.Fuel;

/**
 *燃料信息
 */
public interface FuelDao extends BaseDao<Fuel, Integer> {

    List<Fuel> findSourceNameById(Integer id);


}