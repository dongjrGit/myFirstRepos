package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_PRODUCT")
public class TBasisProduct implements java.io.Serializable {

	// Fields

	private String id;
	private TBasisProductMenu TBasisProductMenu;
	private String menuId;
	private String productName;
	private String productFolder;
	private String productZip;
	private String zipUptime;
	private String facePage;
	private String status;
	private String onlineTime;
	private String offlineTime;
	private String updateTime;
	private String updateUser;
	private String keyWord;
	private String productDesc;
	private String templateInfo;
	private String zipLength;
	private String createUser;

	// Constructors

	/** default constructor */
	public TBasisProduct() {
	}

	/** full constructor */
	public TBasisProduct(TBasisProductMenu TBasisProductMenu,
			String productName, String productFolder, String productZip,
			String status, String updateTime, String updateUser) {
		this.TBasisProductMenu = TBasisProductMenu;
		this.productName = productName;
		this.productFolder = productFolder;
		this.productZip = productZip;
		this.status = status;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
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
	public TBasisProductMenu getTBasisProductMenu() {
		return this.TBasisProductMenu;
	}

	public void setTBasisProductMenu(TBasisProductMenu TBasisProductMenu) {
		this.TBasisProductMenu = TBasisProductMenu;
	}

	@Column(name = "PRODUCT_NAME", length = 120)
	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "PRODUCT_FOLDER", length = 120)
	public String getProductFolder() {
		return this.productFolder;
	}

	public void setProductFolder(String productFolder) {
		this.productFolder = productFolder;
	}

	@Column(name = "PRODUCT_ZIP", length = 120)
	public String getProductZip() {
		return this.productZip;
	}

	public void setProductZip(String productZip) {
		this.productZip = productZip;
	}

	@Column(name = "UPDATE_TIME", length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UPDATE_USER", length = 32)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Transient
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Column(name = "FACE_PAGE", length = 120)
	public String getFacePage() {
		return facePage;
	}
	public void setFacePage(String facePage) {
		this.facePage = facePage;
	}

	@Column(name = "ZIP_UPTIME", length = 20)
	public String getZipUptime() {
		return zipUptime;
	}

	public void setZipUptime(String zipUptime) {
		this.zipUptime = zipUptime;
	}
	
	@Column(name = "ONLINE_TIME", length = 20)
	public String getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(String onlineTime) {
		this.onlineTime = onlineTime;
	}

	@Column(name = "OFFLINE_TIME", length = 20)
	public String getOfflineTime() {
		return offlineTime;
	}

	public void setOfflineTime(String offlineTime) {
		this.offlineTime = offlineTime;
	}
	
	@Column(name = "KEY_WORD", length = 60)
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Column(name = "TEMPLATE_INFO", length = 4000)
	public String getTemplateInfo() {
		return templateInfo;
	}

	public void setTemplateInfo(String templateInfo) {
		this.templateInfo = templateInfo;
	}
	
	@Column(name = "ZIP_LENGTH", length = 20)
	public String getZipLength() {
		return zipLength;
	}

	public void setZipLength(String zipLength) {
		this.zipLength = zipLength;
	}

	@Column(name = "PRODUCT_DESC", length = 3000)
	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	@Column(name = "CREATE_USER", length = 32)
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
}