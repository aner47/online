package com.online.entity.online.embeddable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.online.entity.online.Enterprise;
import com.online.entity.online.ExhaustionHole;
import com.online.entity.online.GovernanceMeasures;

@Embeddable
public class EnterpriseEmissionsManagement {

	private Enterprise enterprise;
	
	/**
	 * 排放口
	 */
	private ExhaustionHole exhaustionHole;
	
	/**
	 * 治理信息
	 */
	private GovernanceMeasures governanceMeasures1;
	
	/**
	 * 治理信息
	 */
	private GovernanceMeasures governanceMeasures2;
	
	/**
	 * 治理信息
	 */
	private GovernanceMeasures governanceMeasures3;
	
	/**
	 * 治理信息
	 */
	private GovernanceMeasures governanceMeasures4;
	
	@ManyToOne
	public ExhaustionHole getExhaustionHole() {
		return exhaustionHole;
	}

	public void setExhaustionHole(ExhaustionHole exhaustionHole) {
		this.exhaustionHole = exhaustionHole;
	}
	
	@ManyToOne
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	@ManyToOne
	public GovernanceMeasures getGovernanceMeasures1() {
		return governanceMeasures1;
	}

	public void setGovernanceMeasures1(GovernanceMeasures governanceMeasures1) {
		this.governanceMeasures1 = governanceMeasures1;
	}
	
	@ManyToOne
	public GovernanceMeasures getGovernanceMeasures2() {
		return governanceMeasures2;
	}

	public void setGovernanceMeasures2(GovernanceMeasures governanceMeasures2) {
		this.governanceMeasures2 = governanceMeasures2;
	}
	
	@ManyToOne
	public GovernanceMeasures getGovernanceMeasures3() {
		return governanceMeasures3;
	}

	public void setGovernanceMeasures3(GovernanceMeasures governanceMeasures3) {
		this.governanceMeasures3 = governanceMeasures3;
	}
	@ManyToOne
	public GovernanceMeasures getGovernanceMeasures4() {
		return governanceMeasures4;
	}
	
	public void setGovernanceMeasures4(GovernanceMeasures governanceMeasures4) {
		this.governanceMeasures4 = governanceMeasures4;
	}

	
	
	
	
}
