package com.online.service;

import java.util.List;
import java.util.Map;

import com.online.entity.Area;
import com.online.entity.DictionaryData;
/**
 * 
 * @author zuozhiping
 * 
 * 区域配置服务接口
 *
 */
public interface AreaService extends BaseService<Area, Integer> {

	public List<DictionaryData> getSelectData(String param);

	/**
	 * 根据区域代码查询区域
	 * @param areaName
	 * @return
	 */
	public Area findAreaByAreaCode(String areaCode);
	
	/**
	 * 根据经纬度查询省市县code
	 * @author 郑有权
	 * @date 创建时间：2017年10月30日 下午2:22:29 
	 * @param lngs
	 * @param lats
	 * @return
	 * @throws Exception
	 */
	public String getAreaCode(String lngs, String lats) throws Exception;
	
	/**
	 * 根据县code查询经纬度
	 * @author 郑有权
	 * @date 创建时间：2017年10月30日 下午2:23:00 
	 * @param address
	 * @return
	 */
	public Map<String, String> getGeocoderLatitude(String address);
}