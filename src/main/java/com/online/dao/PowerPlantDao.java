package com.online.dao;

import java.util.List;

import com.online.entity.online.PowerPlant;

/**
 * 
 * @author zuozhiping
 * 
 * dao-电厂
 *
 */
public interface PowerPlantDao extends BaseDao<PowerPlant, Integer> {

    List<PowerPlant> findSourceNameById(Integer id);


}