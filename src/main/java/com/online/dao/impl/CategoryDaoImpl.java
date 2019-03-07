package com.online.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.dao.CategoryDao;
import com.online.entity.online.Category;

/**
 * 
 * @author 左志平
 * 
 * dao-行业分类实现
 *
 */
@Repository("categoryDaoImpl")
public class CategoryDaoImpl extends BaseDaoImpl<Category, Integer> implements CategoryDao {



}