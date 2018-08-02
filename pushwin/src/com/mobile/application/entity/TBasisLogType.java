package com.mobile.application.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisLogType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_LOG_TYPE")
public class TBasisLogType implements java.io.Serializable {

	// Fields

	private String url;
	private String modelName;
	private String operaName;
	private String description;
	private Set<TBasisLogDetail> TBasisLogDetails = new HashSet<TBasisLogDetail>(
			0);

	// Constructors

	/** default constructor */
	public TBasisLogType() {
	}

	public TBasisLogType(String url) {
		this.url = url;
	}
	
	public TBasisLogType(String url, String modelName, String operaName,
			String description) {
		this.url = url;
		this.modelName = modelName;
		this.operaName = operaName;
		this.description = description;
	}
	
	/** full constructor */
	public TBasisLogType(String modelName, String operaName,
			String description, Set<TBasisLogDetail> TBasisLogDetails) {
		this.modelName = modelName;
		this.operaName = operaName;
		this.description = description;
		this.TBasisLogDetails = TBasisLogDetails;
	}

	// Property accessors
	@Id
	@Column(name = "URL", unique = true, nullable = false, length = 64)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "MODEL_NAME", length = 20)
	public String getModelName() {
		return this.modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@Column(name = "OPERA_NAME", length = 120)
	public String getOperaName() {
		return this.operaName;
	}

	public void setOperaName(String operaName) {
		this.operaName = operaName;
	}

	@Column(name = "DESCRIPTION", length = 120)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "TBasisLogType")
	public Set<TBasisLogDetail> getTBasisLogDetails() {
		return this.TBasisLogDetails;
	}

	public void setTBasisLogDetails(Set<TBasisLogDetail> TBasisLogDetails) {
		this.TBasisLogDetails = TBasisLogDetails;
	}

}