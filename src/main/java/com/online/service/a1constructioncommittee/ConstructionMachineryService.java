package com.online.service.a1constructioncommittee;

import com.online.entity.online.a1constructioncommittee.ConstructionMachinery;
import com.online.service.BaseService;
/**
 * 住建委-工程机械信息
 */
public interface ConstructionMachineryService extends BaseService<ConstructionMachinery, Integer> {
	
	public ConstructionMachinery saveConstructionMachinery(ConstructionMachinery constructionMachinery) throws Exception;
	public ConstructionMachinery updateConstructionMachinery(ConstructionMachinery constructionMachinery) throws Exception;

}