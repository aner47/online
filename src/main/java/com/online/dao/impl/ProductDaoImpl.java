package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.ProductDao;
import com.online.entity.online.Product;

/**
 * 
 * @author 左志平
 * 
 * dao-产品实现
 *
 */
@Repository("productDaoImpl")
public class ProductDaoImpl extends BaseDaoImpl<Product, Integer> implements ProductDao {



}