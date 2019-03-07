package com.online.service.impl;



import org.springframework.stereotype.Service;

import com.online.entity.online.ProductUnitConfig;
import com.online.service.ProductUnitConfigService;
/**
 * 
 * @author 左志平
 * 
 * 产品单位配置服务实现
 *
 */
@Service("productUnitConfigServiceImpl")
public class ProductUnitConfigServiceImpl extends BaseServiceImpl<ProductUnitConfig, Integer> implements ProductUnitConfigService {
	@Override
	public ProductUnitConfig save(ProductUnitConfig entity) {
		//郑有权
		entity.setStatus("unsynchronized");
		return super.save(entity);
	}
	
	@Override
	public ProductUnitConfig update(ProductUnitConfig entity) {
		//郑有权
		entity.setStatus("unsynchronized");
		return super.update(entity);
	}
}
