package com.online.service;


import java.util.List;
import java.util.Map;

import com.online.entity.SystemMenu;
import com.online.entity.SystemUser;
/**
 * 
 * @author zuozhiping
 * 
 * 用户服务接口
 *
 */

public interface SystemUserService extends BaseService<SystemUser, Integer> {
	
	/**
	 * 通过用户名查找用户信息
	 * @param username
	 * @return
	 */
	public SystemUser findByUsername(String username);
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	public List<String> queryAllPerms(Integer id);
	
	
	public void saveUserEntity(SystemUser systemUser, String selectRole,String selectDepartment) throws Exception;
	/**
	 * 查询所有用户拥有的菜单项
	 */
	List<SystemMenu> queryAllPerm(Integer id);
	
	public List<SystemUser> queryForms(String username, String phone);
	/**
	 * 新建企业用户
	 */
	public void saveUserAndEnterPrise(String username, String password, String email, String captcha, String name,
			String sicCode, String sicName, String sicCodeMiddle, String sicNameMiddle, String code, String provinces,
			String city, String county, String street, String house_number, String longitude, String latitude,
			String contacts, String contact_number, String captchaCode, String captchaKey,String selectDepartment) throws Exception;
	
	List<SystemUser> queryProjectInvitationCode(String projectCode);
    public List<Map<Integer,String>> getUsernameById();
    
    public void updateUserEntity(SystemUser systemUser,String selectRole,Integer projectId,String selectDepartment);
    
    /**
     * 根据角色名称查找用户
     * @author 郑有权
     * @date 创建时间：2017年9月21日 下午4:00:40 
     * @param roleName		角色名称
     * @return
     */
    public List<SystemUser> findByRole(String userType,String roleName);
    
    int updateProjectInvitationCode(String oldprojectCode,String projectCode);

}