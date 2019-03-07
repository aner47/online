package com.online.service.a5businessbureau;

import com.online.entity.online.a5businessbureau.PetrolGasStation;
import com.online.service.BaseService;
import com.online.service.ExportService;

/**
 * 
 * @author zuozhiping
 * 
 * 加油站/加气站信息服务接口
 *
 */
public interface PetrolGasStationService extends BaseService<PetrolGasStation, Integer>,ExportService {
		
		public PetrolGasStation savePetrolGasStation(PetrolGasStation petrolGasStation) throws Exception;
		public PetrolGasStation updatePetrolGasStation(PetrolGasStation petrolGasStation) throws Exception;
	
}