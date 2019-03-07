package com.online.service;

import java.util.HashMap;
import java.util.List;

import com.online.entity.SystemMenu;
/**
 * 
 * @author zuozhiping
 * 
 * 菜单服务接口
 *
 */
public interface SystemMenuService extends BaseService<SystemMenu, Integer> {

	List<SystemMenu> queryList(HashMap<String, Object> hashMap);

	List<String> queryMenus(int id);

	List<SystemMenu> queryActivemenu();

	List<SystemMenu> querymenuByPid(Integer pid );



}