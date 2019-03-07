package com.online.service;

import com.online.entity.SystemDepartment;
/**
 * 部门
 */
public interface SystemDepartmentService extends BaseService<SystemDepartment, Integer> {
	
	public void saveDepartmentEntity(SystemDepartment systemDepartment, String selectRole);
	
	public void updateDepartmentEntity(SystemDepartment systemDepartment, String selectRole);
	
	
}