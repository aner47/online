package com.online.util;

import java.util.Objects;

import org.apache.commons.lang.StringUtils;

import com.online.DataAccessException;
import com.online.entity.online.embeddable.Address;

public class AreaUtils {
	/**
	 * 区域级别-省
	 */
	public final static String LEVEL_PROVINCES ="provinces";

	/**
	 * 区域级别-市
	 */
	public final static String LEVEL_CITY ="city";
	
	/**
	 * 区域级别-县
	 */
	public final static String LEVEL_COUNTY ="county";
	
	/**
	 * 区域级别-街道/乡镇
	 */
	public final static String LEVEL_STREET ="street";
	
	/**
	 * 获得区域级别
	 * @param areaCode
	 * @return provinces(省),city(城市),county(区县),street(街道)
	 * @throws DataAccessException 
	 */
	public static String  getAreaCodeLevel(String areaCode) {
		if(StringUtils.isEmpty(areaCode)){
			try {
				throw new DataAccessException("区域代码不为空！");
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!StringUtils.isNumeric(areaCode)){
			try {
				throw new DataAccessException(String.format("区域代码【%s】必须为数字！", areaCode));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(areaCode.length()!=6&&areaCode.length()!=9){
			try {
				throw new DataAccessException(String.format("区域代码【%s】不合法，长度应该是【6】或者【9】！", areaCode));
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String result = "provinces";
		if(areaCode.split(",").length>0){
			areaCode = areaCode.split(",")[0];
		}
		if(areaCode.endsWith("0000")){
			result = LEVEL_PROVINCES ;
		}else if(areaCode.endsWith("00")){
			result = LEVEL_CITY;
		}else if(areaCode.length() == 6){
			result = LEVEL_COUNTY;
		}else{
			result = LEVEL_STREET;
		}
		return result;
	}
	
	/**
	 * 判断一个地址是否在指定区域内
	 * @param areaCode
	 * @return provinces(省),city(城市),county(区县),street(街道)
	 * @throws DataAccessException 
	 */
	public static boolean  addressIsInAreaCode(Address address,String areaCode) throws DataAccessException{
		boolean result = false;
		if(address == null){
			return false;
		}
		String areaCodeLevel = getAreaCodeLevel(areaCode);
		switch (areaCodeLevel) {
		case LEVEL_PROVINCES:
			result = Objects.equals(address.getProvinces(), areaCode) ;
			break;
		case LEVEL_CITY:
			result = Objects.equals(address.getCity(), areaCode) ;
			break;
		case LEVEL_COUNTY:
			result = Objects.equals(address.getCounty(), areaCode) ;
			break;
		case LEVEL_STREET:
			result = Objects.equals(address.getCounty(), areaCode) ;
			break;
		default:
			break;
		}
		
		return result;
	}
	
	
	
	
	
	
	/**
	 * 活动数据是否在指定区域内
	 * @param areaCode
	 * @return provinces(省),city(城市),county(区县),street(街道)
	 
	public static boolean  eaIsInAreaCode(EmissionBaseDataSuperClass ea ,String areaCode){
		Address address = null;
		if(ea == null){
			return false;
		}
		if(ea.getPollutionSourceInfo()!=null){
			address = ea.getPollutionSourceInfo().getAddress();
		}else if(ea.getEnterprise()!=null){
			address = ea.getEnterprise().getAddress();
		}
		return addressIsInAreaCode(address, areaCode);
	}
	*/
	

}
