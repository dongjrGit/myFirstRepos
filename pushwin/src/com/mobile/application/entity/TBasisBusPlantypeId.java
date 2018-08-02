package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TBasisBusPlantypeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisBusPlantypeId implements java.io.Serializable {

	// Fields

	private String busintypeid;
	private String businidbranch;
	private String oraarea;

	// Constructors

	/** default constructor */
	public TBasisBusPlantypeId() {
	}

	/** full constructor */
	public TBasisBusPlantypeId(String busintypeid, String businidbranch,
			String oraarea) {
		this.busintypeid = busintypeid;
		this.businidbranch = businidbranch;
		this.oraarea = oraarea;
	}

	// Property accessors

	@Column(name = "BUSINTYPEID", nullable = false, length = 64)
	public String getBusintypeid() {
		return this.busintypeid;
	}

	public void setBusintypeid(String busintypeid) {
		this.busintypeid = busintypeid;
	}

	@Column(name = "BUSINIDBRANCH", nullable = false, length = 64)
	public String getBusinidbranch() {
		return this.businidbranch;
	}

	public void setBusinidbranch(String businidbranch) {
		this.businidbranch = businidbranch;
	}

	@Column(name = "ORAAREA", nullable = false, length = 64)
	public String getOraarea() {
		return this.oraarea;
	}

	public void setOraarea(String oraarea) {
		this.oraarea = oraarea;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisBusPlantypeId))
			return false;
		TBasisBusPlantypeId castOther = (TBasisBusPlantypeId) other;

		return ((this.getBusintypeid() == castOther.getBusintypeid()) || (this
				.getBusintypeid() != null && castOther.getBusintypeid() != null && this
				.getBusintypeid().equals(castOther.getBusintypeid())))
				&& ((this.getBusinidbranch() == castOther.getBusinidbranch()) || (this
						.getBusinidbranch() != null
						&& castOther.getBusinidbranch() != null && this
						.getBusinidbranch()
						.equals(castOther.getBusinidbranch())))
				&& ((this.getOraarea() == castOther.getOraarea()) || (this
						.getOraarea() != null && castOther.getOraarea() != null && this
						.getOraarea().equals(castOther.getOraarea())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getBusintypeid() == null ? 0 : this.getBusintypeid()
						.hashCode());
		result = 37
				* result
				+ (getBusinidbranch() == null ? 0 : this.getBusinidbranch()
						.hashCode());
		result = 37 * result
				+ (getOraarea() == null ? 0 : this.getOraarea().hashCode());
		return result;
	}

}