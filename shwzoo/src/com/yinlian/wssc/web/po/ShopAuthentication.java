package com.yinlian.wssc.web.po;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;

public class ShopAuthentication {
	private Integer id;

	private Integer shopid;

	private String principalname;

	private Integer principadepartment;

	private String principalpost;

	private String principatelephone;

	private String principalmobile;

	private String principalemail;

	private String companyname;

	private String companyprovince;

	private String companycity;

	private String companyarea;

	private String companyadress;

	private String companypurpose;

	private String companymarket;

	private Integer companypopulation;

	private Integer companyindustry;

	private Integer companytype;

	private String companyhonor;

	private String companyfox;

	private String companyemail;
	@NotEmpty(message = "公司的联系电话不能为空")
	private String companytel;

	private String companycontactname;

	private BigDecimal companysales;

	private String companyweb;

	private String license;

	private String certificate;

	private String organizationtime;

	private String organization;

	private String tax;

	private String bank;

	private String postcode;

	private Integer iseshop;

	private String eshopurl;

	private Integer eshopemployee;

	private Integer eshopproduct;

	private BigDecimal eshopavgprice;

	private String eshopexpress;

	private Integer status;

	private String operatetype;

	private String companylocation;

	private String organizationelec;

	private String comment;

	private String longitude;

	private String latitude;

	private String shopname; // 店铺的名字

	private String supporttel;

	private String licenseNum;

	private String examinereport;

	private String producelicence;

	private String adoctrinecredential;

	private String marketingscope;

	private String bewrite;

	public String getBewrite() {
		return bewrite;
	}

	public void setBewrite(String bewrite) {
		this.bewrite = bewrite;
	}

	public String getSupporttel() {
		return supporttel;
	}

	public void setSupporttel(String supporttel) {
		this.supporttel = supporttel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShopid() {
		return shopid;
	}

	public void setShopid(Integer shopid) {
		this.shopid = shopid;
	}

	public String getPrincipalname() {
		return principalname;
	}

	public void setPrincipalname(String principalname) {
		this.principalname = principalname == null ? null : principalname.trim();
	}

	public Integer getPrincipadepartment() {
		return principadepartment;
	}

	public void setPrincipadepartment(Integer principadepartment) {
		this.principadepartment = principadepartment;
	}

	public String getPrincipalpost() {
		return principalpost;
	}

	public void setPrincipalpost(String principalpost) {
		this.principalpost = principalpost == null ? null : principalpost.trim();
	}

	public String getPrincipatelephone() {
		return principatelephone;
	}

	public void setPrincipatelephone(String principatelephone) {
		this.principatelephone = principatelephone == null ? null : principatelephone.trim();
	}

	public String getPrincipalmobile() {
		return principalmobile;
	}

	public void setPrincipalmobile(String principalmobile) {
		this.principalmobile = principalmobile == null ? null : principalmobile.trim();
	}

	public String getPrincipalemail() {
		return principalemail;
	}

	public void setPrincipalemail(String principalemail) {
		this.principalemail = principalemail == null ? null : principalemail.trim();
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname == null ? null : companyname.trim();
	}

	public String getCompanyprovince() {
		return companyprovince;
	}

	public void setCompanyprovince(String companyprovince) {
		this.companyprovince = companyprovince == null ? null : companyprovince.trim();
	}

	public String getCompanycity() {
		return companycity;
	}

	public void setCompanycity(String companycity) {
		this.companycity = companycity == null ? null : companycity.trim();
	}

	public String getCompanyarea() {
		return companyarea;
	}

	public void setCompanyarea(String companyarea) {
		this.companyarea = companyarea == null ? null : companyarea.trim();
	}

	public String getCompanyadress() {
		return companyadress;
	}

	public void setCompanyadress(String companyadress) {
		this.companyadress = companyadress == null ? null : companyadress.trim();
	}

	public String getCompanypurpose() {
		return companypurpose;
	}

	public void setCompanypurpose(String companypurpose) {
		this.companypurpose = companypurpose == null ? null : companypurpose.trim();
	}

	public String getCompanymarket() {
		return companymarket;
	}

	public void setCompanymarket(String companymarket) {
		this.companymarket = companymarket == null ? null : companymarket.trim();
	}

	public Integer getCompanypopulation() {
		return companypopulation;
	}

	public void setCompanypopulation(Integer companypopulation) {
		this.companypopulation = companypopulation;
	}

	public Integer getCompanyindustry() {
		return companyindustry;
	}

	public void setCompanyindustry(Integer companyindustry) {
		this.companyindustry = companyindustry;
	}

	public Integer getCompanytype() {
		return companytype;
	}

	public void setCompanytype(Integer companytype) {
		this.companytype = companytype;
	}

	public String getCompanyhonor() {
		return companyhonor;
	}

	public void setCompanyhonor(String companyhonor) {
		this.companyhonor = companyhonor == null ? null : companyhonor.trim();
	}

	public String getCompanyfox() {
		return companyfox;
	}

	public void setCompanyfox(String companyfox) {
		this.companyfox = companyfox == null ? null : companyfox.trim();
	}

	public String getCompanyemail() {
		return companyemail;
	}

	public void setCompanyemail(String companyemail) {
		this.companyemail = companyemail == null ? null : companyemail.trim();
	}

	public String getCompanytel() {
		return companytel;
	}

	public void setCompanytel(String companytel) {
		this.companytel = companytel == null ? null : companytel.trim();
	}

	public String getCompanycontactname() {
		return companycontactname;
	}

	public void setCompanycontactname(String companycontactname) {
		this.companycontactname = companycontactname == null ? null : companycontactname.trim();
	}

	public BigDecimal getCompanysales() {
		return companysales;
	}

	public void setCompanysales(BigDecimal companysales) {
		this.companysales = companysales;
	}

	public String getCompanyweb() {
		return companyweb;
	}

	public void setCompanyweb(String companyweb) {
		this.companyweb = companyweb == null ? null : companyweb.trim();
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license == null ? null : license.trim();
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate == null ? null : certificate.trim();
	}

	public String getOrganizationtime() {
		return organizationtime;
	}

	public void setOrganizationtime(String organizationtime) {
		this.organizationtime = organizationtime == null ? null : organizationtime.trim();
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization == null ? null : organization.trim();
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax == null ? null : tax.trim();
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank == null ? null : bank.trim();
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode == null ? null : postcode.trim();
	}

	public Integer getIseshop() {
		return iseshop;
	}

	public void setIseshop(Integer iseshop) {
		this.iseshop = iseshop;
	}

	public String getEshopurl() {
		return eshopurl;
	}

	public void setEshopurl(String eshopurl) {
		this.eshopurl = eshopurl == null ? null : eshopurl.trim();
	}

	public Integer getEshopemployee() {
		return eshopemployee;
	}

	public void setEshopemployee(Integer eshopemployee) {
		this.eshopemployee = eshopemployee;
	}

	public Integer getEshopproduct() {
		return eshopproduct;
	}

	public void setEshopproduct(Integer eshopproduct) {
		this.eshopproduct = eshopproduct;
	}

	public BigDecimal getEshopavgprice() {
		return eshopavgprice;
	}

	public void setEshopavgprice(BigDecimal eshopavgprice) {
		this.eshopavgprice = eshopavgprice;
	}

	public String getEshopexpress() {
		return eshopexpress;
	}

	public void setEshopexpress(String eshopexpress) {
		this.eshopexpress = eshopexpress == null ? null : eshopexpress.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getOperatetype() {
		return operatetype;
	}

	public void setOperatetype(String operatetype) {
		this.operatetype = operatetype == null ? null : operatetype.trim();
	}

	public String getCompanylocation() {
		return companylocation;
	}

	public void setCompanylocation(String companylocation) {
		this.companylocation = companylocation == null ? null : companylocation.trim();
	}

	public String getOrganizationelec() {
		return organizationelec;
	}

	public void setOrganizationelec(String organizationelec) {
		this.organizationelec = organizationelec == null ? null : organizationelec.trim();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment == null ? null : comment.trim();
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude == null ? null : longitude.trim();
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude == null ? null : latitude.trim();
	}

	/**
	 * Getter method for property <tt>shopname</tt>.
	 * 
	 * @return property value of shopname
	 */
	public String getShopname() {
		return shopname;
	}

	/**
	 * Setter method for property <tt>shopname</tt>.
	 * 
	 * @param shopname
	 *            value to be assigned to property shopname
	 */
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getLicenseNum() {
		return licenseNum;
	}

	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}

	public String getProducelicence() {
		return producelicence;
	}

	public void setProducelicence(String producelicence) {
		this.producelicence = producelicence;
	}

	public String getAdoctrinecredential() {
		return adoctrinecredential;
	}

	public void setAdoctrinecredential(String adoctrinecredential) {
		this.adoctrinecredential = adoctrinecredential;
	}

	public String getExaminereport() {
		return examinereport;
	}

	public void setExaminereport(String examinereport) {
		this.examinereport = examinereport;
	}

	public String getMarketingscope() {
		return marketingscope;
	}

	public void setMarketingscope(String marketingscope) {
		this.marketingscope = marketingscope;
	}

}