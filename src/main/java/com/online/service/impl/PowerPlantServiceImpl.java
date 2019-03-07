package com.online.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.PowerPlantDao;
import com.online.entity.online.PowerPlant;
import com.online.service.PowerPlantService;
/**
 * 
 * @author 左志平
 * 
 * 电厂服务实现
 *
 */
@Service("powerPlantServiceImpl")
public class PowerPlantServiceImpl extends BaseServiceImpl<PowerPlant, Integer> implements PowerPlantService {

    @Autowired
    private PowerPlantDao plantDao;
    
    @Override
    public List<PowerPlant> findSourceNameById(Integer id) {
        return plantDao.findSourceNameById(id);
    }
	
}
