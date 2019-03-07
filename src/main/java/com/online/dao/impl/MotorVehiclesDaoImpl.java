package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.MotorVehiclesDao;
import com.online.entity.online.motorvehicles.MotorVehicles;

/**
 * 机动车调查表
 */
@Repository("motorVehiclesDaoImpl")
public class MotorVehiclesDaoImpl extends BaseDaoImpl<MotorVehicles, Integer> implements MotorVehiclesDao {



}