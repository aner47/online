package com.online.service;

import com.online.entity.online.ExhaustionHole;
/**
 * 
 * @author zuozhiping
 * 
 * 排放口服务接口
 *
 */
public interface ExhaustionHoleService extends BaseService<ExhaustionHole, Integer> {
	
	public ExhaustionHole saveExhaustionHole(ExhaustionHole exhaustionHole) throws Exception;
	
	public ExhaustionHole updateExhaustionHole(ExhaustionHole exhaustionHole)throws Exception;
	

}