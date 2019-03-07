package com.online.dao;

import org.springframework.stereotype.Repository;

import com.online.entity.online.EquipmentLeaked;

/**
 * 
 * @author zuozhiping
 * 
 * dao-设备泄露
 *
 */
@Repository("equipmentLeakedDaoImpl")
public interface EquipmentLeakedDao extends BaseDao<EquipmentLeaked, Integer> {


}