package com.online.dao;


import java.util.HashMap;
import java.util.List;

import com.online.entity.SystemMenu;

/**
 * 
 * @author zuozhiping
 * 
 * dao-菜单
 *
 */
public interface SystemMenuDao extends BaseDao<SystemMenu, Integer> {

	List<SystemMenu> querylist(HashMap<String, Object> hashMap);

	List<String> queryMenus(int id);

	List<SystemMenu> queryActivemenu();


	List<SystemMenu> querymenuByPid(Integer pid);





}