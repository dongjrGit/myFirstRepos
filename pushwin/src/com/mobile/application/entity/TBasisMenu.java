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
 * TBasisMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_MENU")
public class TBasisMenu implements java.io.Serializable {

	// Fields

	private String menuId;
	private String menuName;
	private String menuUrl;
	private String menuPid;
	private Integer menuLevel;
	private String menuSort;
	private String menuIcon;
	private String iconPosition;
	private Set<TBasisRoleMenu> TBasisRoleMenus = new HashSet<TBasisRoleMenu>(0);

	// Constructors

	/** default constructor */
	public TBasisMenu() {
	}

	/** full constructor */
	public TBasisMenu(String menuName, String menuUrl, String menuPid, Integer menuLevel,
			String menuSort, String menuIcon, String iconPosition,
			Set<TBasisRoleMenu> TBasisRoleMenus) {
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuPid = menuPid;
		this.menuLevel = menuLevel;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
		this.iconPosition = iconPosition;
		this.TBasisRoleMenus = TBasisRoleMenus;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "MENU_ID", unique = true, nullable = false, length = 32)
	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Column(name = "MENU_NAME", length = 32)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_URL", length = 128)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "MENU_PID", length = 32)
	public String getMenuPid() {
		return this.menuPid;
	}

	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}
	@Column(name = "MENU_LEVEL", length = 8)
	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	@Column(name = "MENU_SORT", length = 10)
	public String getMenuSort() {
		return this.menuSort;
	}

	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}

	@Column(name = "MENU_ICON", length = 32)
	public String getMenuIcon() {
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Column(name = "ICON_POSITION", length = 20)
	public String getIconPosition() {
		return this.iconPosition;
	}

	public void setIconPosition(String iconPosition) {
		this.iconPosition = iconPosition;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TBasisMenu")
	public Set<TBasisRoleMenu> getTBasisRoleMenus() {
		return this.TBasisRoleMenus;
	}

	public void setTBasisRoleMenus(Set<TBasisRoleMenu> TBasisRoleMenus) {
		this.TBasisRoleMenus = TBasisRoleMenus;
	}
}