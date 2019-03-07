package com.online.entity.online.a1constructioncommittee;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 住建委-市政项目建筑工地信息
 */
@Entity
@DiscriminatorValue(value = "CityBuildSite")
public class CityBuildSite extends BaseHouseBuildSite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
