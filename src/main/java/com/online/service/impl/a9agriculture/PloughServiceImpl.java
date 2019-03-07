package com.online.service.impl.a9agriculture;



import org.springframework.stereotype.Service;

import com.online.entity.online.a9agriculture.Plough;
import com.online.service.a9agriculture.PloughService;
import com.online.service.impl.BaseServiceImpl;
import com.online.util.SpringUtils;
/**
 * 
 * 耕地信息服务实现
 *
 */
@Service("ploughServiceImpl")
public class PloughServiceImpl extends BaseServiceImpl<Plough, Integer> implements PloughService {
	
	
	@Override
	public Plough savePlough(Plough plough) throws Exception {
		plough.setProject(SpringUtils.getCurrentProject());
		return save(plough);
	}


	@Override
	public Plough updatePlough(Plough plough) throws Exception {
		//郑有权
		
		return update(plough,"project");
	}
	
}
