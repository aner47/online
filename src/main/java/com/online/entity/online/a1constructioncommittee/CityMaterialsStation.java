package com.online.entity.online.a1constructioncommittee;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.online.annotation.EntityDescription;

/**
 * 住建委-全市道路施工材料站（沥青、灰土等道路建设材料）信息表
 */
@EntityDescription(caption="全市道路施工材料站（沥青、灰土等道路建设材料）信息表")
@Entity
@DiscriminatorValue(value = "CityMaterialsStation")
public class CityMaterialsStation extends BaseBetonStirStation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	
}
