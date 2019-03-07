package com.online.service.impl;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.dao.SystemMenuDao;
import com.online.dao.SystemRoleDao;
import com.online.entity.SystemMenu;
import com.online.entity.SystemRole;
import com.online.service.SystemRoleService;
/**
 * 
 * @author xuchen
 * 
 * 角色服务实现
 *
 */
@Service("systemRoleServiceImpl")
public class SystemRoleServiceImpl extends BaseServiceImpl<SystemRole, Integer> implements SystemRoleService {

	@Autowired
	private SystemMenuDao systemMenuDao; 
	@Autowired
	private SystemRoleDao systemRoleDao;
	@Transactional
	public void saveEntity(SystemRole systemRole, String menuIds) {
		 String[] menuList = menuIds.split(",");
		 Set<SystemMenu> menus =new HashSet<>();
		 String name = systemRole.getName();
		 if (StringUtils.isNotBlank(name)) {
		 for (String menu : menuList) {
			Integer menuid =new Integer(menu);
			SystemMenu menu2 = systemMenuDao.find(menuid);
			menus.add(menu2);
		}
		 systemRole.setSystemMenus(menus);
			save(systemRole);
		 }
	}
	@Override
	public List<String> findRoles(int id) {
		
		return systemRoleDao.findRoles(id);
	}
	@Override
	public int deleteRoleMenu(int roleId) {
		return systemRoleDao.deleteRoleMenu(roleId);
	}
	@Override
	public int deleteUserRole(int roleId) {
		return systemRoleDao.deleteUserRole(roleId);
	}
	
	
}
