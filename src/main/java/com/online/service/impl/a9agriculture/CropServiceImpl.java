package com.online.service.impl.a9agriculture;



import org.springframework.stereotype.Service;

import com.online.entity.online.a9agriculture.Crop;
import com.online.service.a9agriculture.CropService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 农作物产量及秸秆利用服务实现
 *
 */
@Service("cropServiceImpl")
public class CropServiceImpl extends BaseServiceImpl<Crop, Integer> implements CropService {
	
	
	@Override
	public Crop saveCrop(Crop crop) throws Exception {
		crop.setProject(SpringUtils.getCurrentProject());
		return save(crop);
	}


	@Override
	public Crop updateCrop(Crop crop) throws Exception {
		//郑有权
		
		return update(crop,"project");
	}
	
}
