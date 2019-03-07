package com.online.service.impl;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.online.Filter;
import com.online.Filter.Operator;
import com.online.Order;
import com.online.Order.Direction;
import com.online.entity.DictionaryData;
import com.online.entity.online.Category;
import com.online.entity.online.Category.CategoryLevel;
import com.online.service.CategoryService;
import com.online.service.SelectService;
/**
 * 
 * @author 左志平
 * 
 * 行业分类服务实现
 *
 */
@Service("categoryServiceImpl")
public class CategoryServiceImpl extends BaseServiceImpl<Category, Integer> implements CategoryService ,SelectService {
	
	
	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		if(StringUtils.isNotEmpty(param)){
			String[] split = param.split(",");
			for (String str : split) {
				
				String[] split2 = str.split(":");
				if(split2[0].equals("categoryLevel")){
					CategoryLevel categoryLevel = CategoryLevel.valueOf(split2[1]);
					if(categoryLevel == CategoryLevel.MAIN){
						String [] strings = {"B","C","D","O"};
						filters.add(new Filter("parent",Operator.in,Arrays.asList(strings)));
					}
					filters.add(new Filter(split2[0],Operator.eq,categoryLevel));
				}else{
					filters.add(new Filter(split2[0],Operator.eq,split2[1]));
				}
				
			}
		}
		List<Order> orders = new ArrayList<>();
		Order order = new Order();
		order.setDirection(Direction.asc);
		orders.add(order);
		List<Category> findList = findList(null, filters, orders);
		List<DictionaryData>  list = new ArrayList<>();
		for (Category category : findList) {
			DictionaryData aData = new DictionaryData();
			aData.setCode(category.getCode());
			aData.setCodeValue(category.getName());
			list.add(aData);
		}
		return list;
	}
}
