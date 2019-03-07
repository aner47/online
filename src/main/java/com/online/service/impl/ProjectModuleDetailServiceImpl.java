package com.online.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.dao.ProjectModuleDetailDao;
import com.online.entity.online.ProjectModuleDetail;
import com.online.service.ProjectModuleDetailService;
/**
 * 
 * @author 左志平
 * 
 * 项目模块明细服务实现
 *
 */
@Service("projectModuleDetailServiceImpl")
public class ProjectModuleDetailServiceImpl extends BaseServiceImpl<ProjectModuleDetail, Integer> implements ProjectModuleDetailService {

    @Autowired
    private ProjectModuleDetailDao projectModuleDetailDao;
    
    @Override
    public void deleteModeleDetailByInfo(Integer id) {
       projectModuleDetailDao.deleteModeleDetailByInfo(id);
        
    }
	
}
