package com.online.service.impl;



import org.springframework.stereotype.Service;

import com.online.entity.online.Product;
import com.online.service.ProductService;
/**
 * 
 * @author 左志平
 * 
 * 产品服务实现
 *
 */
@Service("productServiceImpl")
public class ProductServiceImpl extends BaseServiceImpl<Product, Integer> implements ProductService {
	
}
