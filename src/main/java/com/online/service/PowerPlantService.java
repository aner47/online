package com.online.service;

import java.util.List;

import com.online.entity.online.PowerPlant;
/**
 * 
 * @author zuozhiping
 * 
 * 电厂服务接口
 *
 */
public interface PowerPlantService extends BaseService<PowerPlant, Integer> {

    List<PowerPlant> findSourceNameById(Integer id);

}