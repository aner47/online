package com.online.entity.online;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.online.annotation.EntityDescription;


@EntityDescription(caption="详表企业基础信息")
@Entity
@DiscriminatorValue(value = "base")
public class BaseInformation extends Information{
	
	
}
