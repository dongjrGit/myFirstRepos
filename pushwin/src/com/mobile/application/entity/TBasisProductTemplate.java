package com.mobile.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * TBasisProductTemplate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BASIS_PRODUCT_TEMPLATE")
public class TBasisProductTemplate implements java.io.Serializable {

	// Fields

	private String templateId;
	private String templatePic;
	private String updateUser;
	private String updateTime;
	private String templateDesc;
	private String modifyUrl;
	private String queryUrl;
	private String templateName;

	// Constructors

	/** default constructor */
	public TBasisProductTemplate() {
	}

	public TBasisProductTemplate(String templateId) {
		this.templateId = templateId;
	}
	
	/** full constructor */
	public TBasisProductTemplate(String templatePic, String updateUser,
			String updateTime, String templateDesc, String queryUrl, String modifyUrl,
			String templateName) {
		this.templatePic = templatePic;
		this.updateUser = updateUser;
		this.updateTime = updateTime;
		this.templateDesc = templateDesc;
		this.queryUrl = queryUrl;
		this.modifyUrl = modifyUrl;
		this.templateName = templateName;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "TEMPLATE_ID", unique = true, nullable = false, length = 32)
	public String getTemplateId() {
		return this.templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	@Column(name = "TEMPLATE_PIC", length = 60)
	public String getTemplatePic() {
		return this.templatePic;
	}

	public void setTemplatePic(String templatePic) {
		this.templatePic = templatePic;
	}

	@Column(name = "UPDATE_USER", length = 20)
	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	@Column(name = "UPDATE_TIME", length = 20)
	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "TEMPLATE_DESC", length = 300)
	public String getTemplateDesc() {
		return this.templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	@Column(name = "TEMPLATE_NAME", length = 120)
	public String getTemplateName() {
		return this.templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	@Column(name = "MODIFY_URL", length = 120)
	public String getModifyUrl() {
		return modifyUrl;
	}

	public void setModifyUrl(String modifyUrl) {
		this.modifyUrl = modifyUrl;
	}
	@Column(name = "QUERY_URL", length = 120)
	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
}