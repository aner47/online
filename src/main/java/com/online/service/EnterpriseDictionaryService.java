package com.online.service;

import java.util.List;
import java.util.Set;

import com.online.entity.EnterpriseDictionary;


/**
 * 企业名录库
 */
public interface EnterpriseDictionaryService extends BaseService<EnterpriseDictionary, Integer>{
	
	public EnterpriseDictionary saveEntity(EnterpriseDictionary enterpriseDictionary);
	public EnterpriseDictionary updateEntity(EnterpriseDictionary enterpriseDictionary);
	public void deleteEntity(Integer id);
	/**
	 * 导入名录库
	 * @author 郑有权
	 * @date 创建时间：2018年2月2日 下午2:08:28 
	 * @param filePath
	 * @param projectId
	 */
	public void exportTask(String filePath,Integer projectId) throws Exception;
	
	/**
	 * 查询重复
	 * @author 郑有权
	 * @date 创建时间：2018年2月2日 下午2:07:46 
	 * @return
	 */
	public boolean filtrate(EnterpriseDictionary enterpriseDictionary);
	/**
	 * 生成任务
	 * @author 郑有权
	 * @date 创建时间：2018年2月2日 下午2:07:27 
	 * @param ids
	 * @throws Exception
	 */
	public void createTaskManager(String ids) throws Exception;
	
	
	public void updateRepNull(EnterpriseDictionary enterpriseDictionary);
	
	
}
