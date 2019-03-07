package com.online.entity.online.embeddable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.commons.lang.StringUtils;

import com.online.entity.Area;

/**
 * 地址类
 * @author DEV2
 *
 */
@Embeddable
public class Address implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = -3395251297046816353L;

    /**
	 * 省
	 */
	private Area provinces ;
	
	/**
	 * 市
	 */
	private Area city ;
	
	/**
	 * 县
	 */
	private Area county ;
	
	/**
	 * 街道
	 */
	private Area street;
	
	/**
	 * 门牌号
	 */
	private String houseNumber;
	
	/**
	 * 经度 
	 */
	private Double longitude;
	
	
	/**
	 * 纬度 
	 */
	private Double latitude;
	
	@Column(columnDefinition="DOUBLE COMMENT '经度'")
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Column(columnDefinition="DOUBLE COMMENT '纬度 '")
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="code")
	public Area getProvinces() {
		return provinces;
	}

	public void setProvinces(Area provinces) {
		this.provinces = provinces;
	}
	@ManyToOne
	@JoinColumn(referencedColumnName="code")
	public Area getCity() {
		return city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="code")
	public Area getCounty() {
		return county;
	}
	
	public void setCounty(Area county) {
		this.county = county;
	}

	@ManyToOne
	@JoinColumn(referencedColumnName="code")
	public Area getStreet() {
		return street;
	}

	public void setStreet(Area street) {
		this.street = street;
	}
	

	@Column(columnDefinition="VARCHAR(255) COMMENT '门牌号'")
	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public Double calcLatitude(){
		return latitude;
	}
	
	public Double calcLonggitude(){
		return longitude;
	}
	
	
	public Address clear(){
		if(provinces!=null&&StringUtils.isEmpty(provinces.getCode())){
			provinces = null;
		}
		if(city!=null&&StringUtils.isEmpty(city.getCode())){
			city = null;
		}
		if(county!=null&&StringUtils.isEmpty(county.getCode())){
			county = null;
		}
		if(street!=null&&StringUtils.isEmpty(street.getCode())){
			street = null;
		}
		return this;
	}
	
	public String calcDetailedAddress(){
		if(provinces == null || city == null || county == null){
			System.out.println("9999999999999999999999999999999999999");
			return "";
		}else{
			System.out.println(provinces.getName() + city.getName() + county.getName() + street + houseNumber);
		}
		return provinces.getName() + city.getName() + county.getName() + street + houseNumber;
	}
	public void setValue(String property,Object value){
		switch (property) {
			case "provinces": setProvinces((Area)value);  break;
			case "city": setCity((Area)value);  break;
			case "county": setCounty((Area)value);  break;
			case "houseNumber": setHouseNumber((String)value); break;
			default:	break;
		}
	}

	@Override
	public String toString() {
		return "Address [provinces=" + provinces + ", city=" + city + ", county=" + county + ", street=" + street
				+ ", houseNumber=" + houseNumber + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}
	
	
}
