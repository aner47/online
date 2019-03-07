package com.online.service;

import java.util.List;

import com.online.entity.online.RawMaterials;
/**
 * 
 * @author zuozhiping
 * 
 * 原辅料服务接口
 *
 */
public interface RawMaterialsService extends BaseService<RawMaterials, Integer> {

	List<RawMaterials> findByEnterpriseid(Integer enterpriseId);
	
	RawMaterials saveEnter(RawMaterials rawMaterials);
	
	RawMaterials updateEnter(RawMaterials rawMaterials);
	
	RawMaterials updateSimpleEnter(RawMaterials rawMaterials);
	
	void deleteEnter(Integer id);
	
}