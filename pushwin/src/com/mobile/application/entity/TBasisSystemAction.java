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
 * TBasisSystemAction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_SYSTEM_ACTION")
public class TBasisSystemAction implements java.io.Serializable {

	// Fields

	private String id;
	private String actionName;
	private String actionUrl;
	private String actionModel;
	private Set<TBasisMenuAction> TBasisMenuActions = new HashSet<TBasisMenuAction>(
			0);

	// Constructors

	/** default constructor */
	public TBasisSystemAction() {
	}

	/** full constructor */
	public TBasisSystemAction(String actionName, String actionUrl,
			String actionModel, Set<TBasisMenuAction> TBasisMenuActions) {
		this.actionName = actionName;
		this.actionUrl = actionUrl;
		this.actionModel = actionModel;
		this.TBasisMenuActions = TBasisMenuActions;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "ACTION_NAME", length = 60)
	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	@Column(name = "ACTION_URL", length = 128)
	public String getActionUrl() {
		return this.actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	@Column(name = "ACTION_MODEL", length = 60)
	public String getActionModel() {
		return this.actionModel;
	}

	public void setActionModel(String actionModel) {
		this.actionModel = actionModel;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBasisSystemAction")
	public Set<TBasisMenuAction> getTBasisMenuActions() {
		return this.TBasisMenuActions;
	}

	public void setTBasisMenuActions(Set<TBasisMenuAction> TBasisMenuActions) {
		this.TBasisMenuActions = TBasisMenuActions;
	}

}