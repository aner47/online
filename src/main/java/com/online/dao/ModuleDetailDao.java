package com.online.dao;

import com.online.entity.online.ModuleDetail;

/**
 * 
 * @author zuozhiping
 * 
 * dao-模块明细
 *
 */
public interface ModuleDetailDao extends BaseDao<ModuleDetail, Integer> {

    void deleteDetails(Integer ids);


}