package com.online.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.entity.SystemDepartment;
import com.online.entity.SystemRole;
import com.online.service.SystemDepartmentService;
import com.online.service.SystemRoleService;

/**
 *部门
 */
@Service("systemDepartmentServiceImpl")
public class SystemDepartmentServiceImpl extends BaseServiceImpl<SystemDepartment, Integer>
		implements  SystemDepartmentService {
	
	@Autowired
	private SystemRoleService systemRoleService;

	@Transactional
	@Override
	public void saveDepartmentEntity(SystemDepartment systemDepartment, String selectRole) {
		
		if (StringUtils.isNotEmpty(selectRole)) {
		String[] roleNum = selectRole.split(",");
		Set<SystemRole> roles = new HashSet<>();
		for (String rolelist : roleNum) {
			Integer roleid = new Integer(rolelist);
			SystemRole role = systemRoleService.find(roleid);
			roles.add(role);
			systemDepartment.setRoles(roles);
		}
		}
		save(systemDepartment);
		
	}

	@Override
	public void updateDepartmentEntity(SystemDepartment systemDepartment, String selectRole) {
		
		if (StringUtils.isNotEmpty(selectRole)) {
			String[] roles = selectRole.split(",");
			Set<SystemRole> roles2 = new HashSet<>();
			for (String string : roles) {
				Integer roleid = Integer.valueOf(string);
				SystemRole role = systemRoleService.find(roleid);
	            roles2.add(role);
	            systemDepartment.setRoles(roles2);
			}
			
			
		}
		update(systemDepartment);
	}
	
	

}
