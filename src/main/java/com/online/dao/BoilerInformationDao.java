package com.online.dao;

import java.util.List;

import com.online.entity.online.BoilerInformation;

/**
 * 
 * @author zuozhiping
 * 
 * dao-锅炉信息
 *
 */
public interface BoilerInformationDao extends BaseDao<BoilerInformation, Integer> {

    List<BoilerInformation> findSourceNameById(Integer id);


}