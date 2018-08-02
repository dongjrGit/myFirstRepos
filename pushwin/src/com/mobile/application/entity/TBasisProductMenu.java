package com.mobile.application.entity;

import javax.persistence.CascadeType;
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
 * TBasisMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_PRODUCT_MENU")
public class TBasisProductMenu implements java.io.Serializable {

	// Fields

	private String menuId;
	private String menuName;
	private String menuDesc;
	private String menuPid;
	private Integer menuLevel;
	private String menuSort;
	private String menuIcon;
	private String keyWords;
	private String updateUser;
	private String createTime;
	private TBasisProductTemplate TBasisProductTemplate;
	// Constructors

	/** default constructor */
	public TBasisProductMenu() {
	}

	public TBasisProductMenu(String menuId) {
		this.menuId = menuId;
	}
	
	/** full constructor */
	public TBasisProductMenu(String menuName, String menuUrl,String menuDesc, String menuPid, Integer menuLevel,
			String menuSort, String menuIcon,String keyWords,String createTime) {
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.menuPid = menuPid;
		this.menuLevel = menuLevel;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
		this.keyWords = keyWords;
		this.createTime = createTime;
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

	@Column(name = "MENU_DESC", length = 128)
	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
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
	@Column(name = "KEY_WORDS", length = 600)
	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}
	
	@Column(name = "UPDATE_USER", length = 32)
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "TEMPLATE_ID")
	public TBasisProductTemplate getTBasisProductTemplate() {
		return this.TBasisProductTemplate;
	}

	public void setTBasisProductTemplate(
			TBasisProductTemplate TBasisProductTemplate) {
		this.TBasisProductTemplate = TBasisProductTemplate;
	}
	@Column(name = "CREATE_TIME", length = 32)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}