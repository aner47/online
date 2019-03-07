package com.online.entity.online.a1constructioncommittee;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 住建委-房屋建筑工地信息
 */
@Entity
@DiscriminatorValue(value = "HouseBuildSite")
public class HouseBuildSite extends BaseHouseBuildSite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
