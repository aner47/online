package com.online.entity.online.embeddable;

import javax.persistence.Embeddable;

import com.online.entity.online.BaseInformation;
import com.online.entity.online.BoilerInformation;
import com.online.entity.online.GovernanceMeasures;

/**
 * 导出企业类
 * @author DEV2
 *
 */
@Embeddable
public class ExportEnterprise{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BaseInformation baseInformation;
	
	private BoilerInformation boilerInformation;
	
	private GovernanceMeasures governanceMeasures;

	public BaseInformation getBaseInformation() {
		return baseInformation;
	}

	public void setBaseInformation(BaseInformation baseInformation) {
		this.baseInformation = baseInformation;
	}

	public BoilerInformation getBoilerInformation() {
		return boilerInformation;
	}

	public void setBoilerInformation(BoilerInformation boilerInformation) {
		this.boilerInformation = boilerInformation;
	}

	public GovernanceMeasures getGovernanceMeasures() {
		return governanceMeasures;
	}

	public void setGovernanceMeasures(GovernanceMeasures governanceMeasures) {
		this.governanceMeasures = governanceMeasures;
	}
	
	
	
}
