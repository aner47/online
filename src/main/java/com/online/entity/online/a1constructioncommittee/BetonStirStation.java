package com.online.entity.online.a1constructioncommittee;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.online.annotation.EntityDescription;

/**
 * 住建委-混凝土搅拌站信息
 */
@EntityDescription(caption="混凝土搅拌站信息")
@Entity
@DiscriminatorValue(value = "BetonStirStation")
public class BetonStirStation extends BaseBetonStirStation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9222660567026914264L;

	
	
}
