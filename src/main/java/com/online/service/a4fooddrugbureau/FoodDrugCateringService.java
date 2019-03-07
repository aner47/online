package com.online.service.a4fooddrugbureau;

import com.online.entity.online.a4fooddrugbureau.FoodDrugCatering;
import com.online.service.BaseService;
import com.online.service.ExportService;
/**
 * 市食药监局-餐饮行业信息
 */
public interface FoodDrugCateringService extends BaseService<FoodDrugCatering, Integer>,ExportService {
	
	public FoodDrugCatering saveFoodDrugCatering(FoodDrugCatering foodDrugCatering) throws Exception;
	public FoodDrugCatering updateFoodDrugCatering(FoodDrugCatering foodDrugCatering) throws Exception;

}