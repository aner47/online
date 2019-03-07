package com.online.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.online.Filter;
import com.online.Filter.Operator;
import com.online.dao.SystemUserDao;
import com.online.entity.DictionaryData;
import com.online.entity.SystemDepartment;
import com.online.entity.SystemMenu;
import com.online.entity.SystemRole;
import com.online.entity.SystemUser;
import com.online.entity.SystemUser.UserType;
import com.online.entity.online.Project;
import com.online.service.ProjectService;
import com.online.service.SelectService;
import com.online.service.SystemDepartmentService;
import com.online.service.SystemRoleService;
import com.online.service.SystemUserService;

/**
 * 
 * @author 左志平
 * 
 *         用户服务实现
 *
 */
@Service("systemUserServiceImpl")
public class SystemUserServiceImpl extends BaseServiceImpl<SystemUser, Integer>
		implements SystemUserService, SelectService {
	@Autowired
	private SystemUserDao systemUserDao;
	@Autowired
	private SystemRoleService systemRoleService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private SystemDepartmentService departmentService;

	public SystemUser findByUsername(String username) {
		return systemUserDao.findByUsername(username);
	}

	public List<String> queryAllPerms(Integer id) {

		return systemUserDao.queryAllPerms(id);
	}

	/**
	 * 查询所有用户拥有的菜单项
	 */
	@Override
	public List<SystemMenu> queryAllPerm(Integer id) {

		return systemUserDao.queryAllPerm(id);
	}

	@Transactional
	public void saveUserEntity(SystemUser systemUser, String selectRole, String selectDepartment) throws MySQLIntegrityConstraintViolationException {

		Set<SystemRole> roles = new HashSet<>();
		Set<SystemDepartment> departmentSet = new HashSet<>();
		if (StringUtils.isNotEmpty(selectDepartment)) {
			String[] epartments = selectDepartment.split(",");
			for (String string : epartments) {
				Integer id = Integer.valueOf(string);
				SystemDepartment entry = departmentService.find(id);
				Set<SystemRole> departmentRole = entry.getRoles();
				roles.addAll(departmentRole);
				departmentSet.add(entry);
			}
		}
		if (StringUtils.isNotBlank(selectRole)) {
			String[] roleNum = selectRole.split(",");
			for (String rolelist : roleNum) {
				Integer roleid = new Integer(rolelist);
				SystemRole role = systemRoleService.find(roleid);
				roles.add(role);
			}
		}
		systemUser.setDepartments(departmentSet);
		systemUser.setRoles(roles);

		save(systemUser);
		

	}

	@Override
	public List<SystemUser> queryForms(String username, String phone) {

		return systemUserDao.queryForms(username, phone);
	}

	
	
	@Transactional
	@Override
	public void saveUserAndEnterPrise(String username, String password, String email, String captcha, String name,
			String sicCode, String sicName, String sicCodeMiddle, String sicNameMiddle, String code, String provinces,
			String city, String county, String street, String house_number, String longitude, String latitude,
			String contacts, String contact_number, String captchaCode, String captchaKey, String selectDepartment) throws MySQLIntegrityConstraintViolationException {

		/*SystemUser user = new SystemUser();
		user.setPassword(DigestUtils.md5Hex(password));
		user.setEmail(email);
		user.setUsername(username);
		user.setUserType(UserType.enterprise);
		saveUserEntity(user, "3", selectDepartment);

		Enterprise enterprise = new Enterprise();
		enterprise.setCode(code);
		enterprise.setContacts(contacts);
		enterprise.setContactNumber(contact_number);
		enterprise.setName(name);
		Address address = new Address();
		address.setHouseNumber(house_number);
		address.setLatitude(Double.valueOf(latitude));
		address.setLongitude(Double.valueOf(longitude));
		Area p = areaService.findAreaByAreaCode(provinces);
		address.setProvinces(p);
		address.setCity(areaService.findAreaByAreaCode(city));
		address.setCounty(areaService.findAreaByAreaCode(county));
		address.setStreet(areaService.findAreaByAreaCode(street));
		enterprise.setIndustryCategoryCodeMain(sicCode);
		enterprise.setIndustryCategoryNameMain(sicName);
		enterprise.setIndustryCategoryCodeMiddle(sicCodeMiddle);
		enterprise.setIndustryCategoryNameMiddle(sicCodeMiddle);
		if (user.getId() != null)
			enterprise.setAccount(user.getId());
		enterprise.setAddress(address);
		try {
			enterpriseService.saveEnterprise(enterprise);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	@Override
	public List<SystemUser> queryProjectInvitationCode(String projectCode) {
		// TODO Auto-generated method stub
		// 郑有权
		return findByFilter(new Filter("project.invitationCode", Operator.eq, projectCode));
		//return systemUserDao.queryProjectInvitationCode(projectCode);
	}

	@Override
	public List<DictionaryData> getSelectData(String param) {
		List<Filter> filters = new ArrayList<>();
		List<DictionaryData> datas = new ArrayList<>();
		if(StringUtils.isNotEmpty(param)){
			String[] split = param.split(",");
			for (String str : split) {
				String[] split2 = str.split(":");
				if(split2.length==2){
					//如果是通过项目查询用户，单独处理
					if("project.id".equals(split2[0])){
						filters.add(new Filter("project.id",Operator.eq,Integer.parseInt(split2[1])));
					}else if("userType.ne".equals(split2[0])){
						filters.add(new Filter("userType",Operator.ne,SystemUser.UserType.system));
					}else if("userType".equals(split2[0])){
						filters.add(new Filter("userType",Operator.eq,convert(split2[1])));
					}
					else{
						filters.add(new Filter(split2[0],Operator.eq,split2[1]));
					}
					
				}
			}
		}
		// filters.add(new Filter("id", Operator.ne,
		// SpringUtils.getCurrentSystemUser().getId()));
		List<SystemUser> findList = findList(null, filters, null);
		if (findList != null) {
			findList.forEach((f) -> {
				DictionaryData aData = new DictionaryData();
				aData.setCode(f.getId() + "");
				aData.setCodeValue(f.getUsername());
				datas.add(aData);
			});
		}
		return datas;
	}
	
	public UserType convert(String str){
		switch (str) {
		case "system":
			return UserType.system;
		case "investigator":
			return UserType.investigator;
		case "enterprise":
			return UserType.enterprise;

		}
		return null;
	}

	@Override
	public List<Map<Integer, String>> getUsernameById() {
		return systemUserDao.getUsernameById();
	}

	@Override
	public void updateUserEntity(SystemUser systemUser, String selectRole, Integer projectId,
			String selectDepartment) {
		Set<SystemRole> roles2 = new HashSet<>();
		Set<SystemDepartment> departmentSet = new HashSet<>();

		if (StringUtils.isNotEmpty(selectDepartment)) {
			String[] epartments = selectDepartment.split(",");
			for (String string : epartments) {
				Integer id = Integer.valueOf(string);
				SystemDepartment entry = departmentService.find(id);
				Set<SystemRole> departmentRole = entry.getRoles();
				roles2.addAll(departmentRole);
				departmentSet.add(entry);
			}

		}
		if (StringUtils.isNotEmpty(selectRole)) {
			String[] roles = selectRole.split(",");
			for (String string : roles) {
				Integer roleid = Integer.valueOf(string);
				SystemRole role = systemRoleService.find(roleid);
				roles2.add(role);
			}

		}

		systemUser.setRoles(roles2);
		systemUser.setDepartments(departmentSet);
		Project project = projectService.find(projectId);
		systemUser.setProject(project);
		update(systemUser, "password");

	}

	@Override
	public List<SystemUser> findByRole(String userType,String roleName) {

		/*List<SystemRole> systemRoles = systemRoleService.findByFilter(new Filter("name", Operator.eq, selectRole));
		if (systemRoles != null && systemRoles.size() > 0) {
			Set<SystemRole> sRoles = new HashSet<>();
			sRoles.add(systemRoles.get(0));
			return systemUserDao.findByRole(systemRoles.get(0).getId());
		}*/
		// 郑有权
		return systemUserDao.findByRole(userType,roleName);
	}

	@Override
	public int updateProjectInvitationCode(String oldprojectCode,String projectCode) {
		return systemUserDao.updateProjectInvitationCode(oldprojectCode,projectCode);
	}

}
