package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisMenuAction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_MENU_ACTION")
public class TBasisMenuAction implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisMenu TBasisMenu;
	private TBasisSystemAction TBasisSystemAction;

	// Constructors

	/** default constructor */
	public TBasisMenuAction() {
	}

	/** full constructor */
	public TBasisMenuAction(TBasisMenu TBasisMenu,
			TBasisSystemAction TBasisSystemAction) {
		this.TBasisMenu = TBasisMenu;
		this.TBasisSystemAction = TBasisSystemAction;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU_ID")
	public TBasisMenu getTBasisMenu() {
		return this.TBasisMenu;
	}

	public void setTBasisMenu(TBasisMenu TBasisMenu) {
		this.TBasisMenu = TBasisMenu;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTION_ID")
	public TBasisSystemAction getTBasisSystemAction() {
		return this.TBasisSystemAction;
	}

	public void setTBasisSystemAction(TBasisSystemAction TBasisSystemAction) {
		this.TBasisSystemAction = TBasisSystemAction;
	}

}