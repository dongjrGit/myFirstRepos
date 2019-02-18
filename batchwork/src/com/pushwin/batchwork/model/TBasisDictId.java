package com.pushwin.batchwork.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TbBusinDictId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TBasisDictId implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String businid;
	private String busintypeid;

	// Constructors

	/** default constructor */
	public TBasisDictId() {
	}

	/** full constructor */
	public TBasisDictId(String businid, String busintypeid) {
		this.businid = businid;
		this.busintypeid = busintypeid;
	}

	// Property accessors

	@Column(name = "BUSINID", nullable = false, length = 64)
	public String getBusinid() {
		return this.businid;
	}

	public void setBusinid(String businid) {
		this.businid = businid;
	}

	@Column(name = "BUSINTYPEID", nullable = false, length = 64)
	public String getBusintypeid() {
		return this.busintypeid;
	}

	public void setBusintypeid(String busintypeid) {
		this.busintypeid = busintypeid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TBasisDictId))
			return false;
		TBasisDictId castOther = (TBasisDictId) other;

//		return ((this.getBusinid() == castOther.getBusinid()) || (this
//				.getBusinid() != null && castOther.getBusinid() != null && this
//				.getBusinid().equals(castOther.getBusinid())))
//				&& ((this.getBusintypeid() == castOther.getBusintypeid()) || (this
//						.getBusintypeid() != null
//						&& castOther.getBusintypeid() != null && this
//						.getBusintypeid().equals(castOther.getBusintypeid())));
		boolean isBusinid = false;
		boolean isBusintypeid = false;
		if (this.getBusinid() != null && castOther.getBusinid() != null && this
				.getBusinid().equals(castOther.getBusinid())){
			isBusinid = true;
		}
		if (this.getBusintypeid() != null&& castOther.getBusintypeid() != null && this
				.getBusintypeid().equals(castOther.getBusintypeid())){
			isBusintypeid = true;
		}
		if(isBusinid && isBusintypeid){
			return true;
		}else{
			return false;
		}
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getBusinid() == null ? 0 : this.getBusinid().hashCode());
		result = 37
				* result
				+ (getBusintypeid() == null ? 0 : this.getBusintypeid()
						.hashCode());
		return result;
	}

}