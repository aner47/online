package com.online.entity.online.a1constructioncommittee;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 住建委-房屋建筑施工和竣工面积情况统计
 */
@Entity
@DiscriminatorValue(value = "HouseBuildArea")
public class HouseBuildArea extends BaseBuildArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
