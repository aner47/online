package com.online.service;

import com.online.entity.online.ProductOtherName;
/**
 * 
 * @author zuozhiping
 * 
 * 产品名称配置服务接口
 *
 */
public interface ProductOtherNameService extends BaseService<ProductOtherName, Integer> {

	public ProductOtherName save(ProductOtherName productOtherName, String pds);
	public ProductOtherName update(ProductOtherName productOtherName, String pds);
}