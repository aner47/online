package com.online.service.a2urbanmanagementbureau;

import com.online.entity.online.a2urbanmanagementbureau.BarbecueRestaurant;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 城管局-全市烧烤店统计信息
 */
public interface BarbecueRestaurantService extends BaseService<BarbecueRestaurant, Integer>,ExportService {
	
	public BarbecueRestaurant saveBarbecueRestaurant(BarbecueRestaurant barbecueRestaurant) throws Exception;
	public BarbecueRestaurant updateBarbecueRestaurant(BarbecueRestaurant barbecueRestaurant) throws Exception;

}