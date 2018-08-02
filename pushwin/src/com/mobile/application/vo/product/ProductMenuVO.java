package com.mobile.application.vo.product;

public class ProductMenuVO {
	private String menuId;
	private String menuName;
	private String menuDesc;
	private String menuPid;
	private Integer menuLevel;
	private String menuSort;
	private String menuIcon;
	private String keyWords;
	private String templateId;
	private String templateName;
	private String templateDesc;
	private String templatePic;
	private String updateUser;
	private String updateOrg;
	private String createTime;

	public ProductMenuVO() {
		super();
	}

	public ProductMenuVO(String menuId, String menuName, String menuDesc,
			String menuPid, Integer menuLevel, String menuSort,
			String menuIcon, String keyWords) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.menuPid = menuPid;
		this.menuLevel = menuLevel;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
		this.keyWords = keyWords;
	}
	
	public ProductMenuVO(String menuId, String menuName, String menuDesc,
			String menuPid, Integer menuLevel, String menuSort,
			String menuIcon, String keyWords, String templateId) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.menuPid = menuPid;
		this.menuLevel = menuLevel;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
		this.keyWords = keyWords;
		this.templateId = templateId;
	}
	public ProductMenuVO(String menuId, String menuName, String menuDesc,
			String menuPid, Integer menuLevel, String menuSort,
			String menuIcon, String keyWords, String templateId, String templateName, 
			String templateDesc, String templatePic, String updateOrg,String updateUser, String createTime) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.menuPid = menuPid;
		this.menuLevel = menuLevel;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
		this.keyWords = keyWords;
		this.templateId = templateId;
		this.templateName = templateName;
		this.templateDesc = templateDesc;
		this.templatePic = templatePic;
		this.updateOrg = updateOrg;
		this.updateUser = updateUser;
		this.createTime = createTime;
	}
	public ProductMenuVO(String menuId, String menuName, String menuDesc,
			String menuPid, Integer menuLevel, String menuSort,
			String menuIcon, String keyWords, String templateId, String templateName, 
			String templateDesc, String templatePic) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuDesc = menuDesc;
		this.menuPid = menuPid;
		this.menuLevel = menuLevel;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
		this.keyWords = keyWords;
		this.templateId = templateId;
		this.templateName = templateName;
		this.templateDesc = templateDesc;
		this.templatePic = templatePic;
	}
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}

	public String getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public String getTemplatePic() {
		return templatePic;
	}

	public void setTemplatePic(String templatePic) {
		this.templatePic = templatePic;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateOrg() {
		return updateOrg;
	}

	public void setUpdateOrg(String updateOrg) {
		this.updateOrg = updateOrg;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
