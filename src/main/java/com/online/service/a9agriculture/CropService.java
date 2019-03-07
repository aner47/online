package com.online.service.a9agriculture;

import com.online.entity.online.a9agriculture.Crop;
import com.online.service.BaseService;

/**
 * 农作物产量及秸秆利用服务接口
 *
 */
public interface CropService extends BaseService<Crop, Integer> {
		
		public Crop saveCrop(Crop crop) throws Exception;
		public Crop updateCrop(Crop crop) throws Exception;
	
}