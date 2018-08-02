package com.mobile.application.vo.menu;

public class MenuVO {
	private String menuId;
	private String menuName;
	private String menuUrl;
	private String menuPid;
	private Integer menuLevel;
	private String menuSort;
	private String menuIcon;
	private String iconPosition;
	
	public MenuVO() {
		super();
	}
	public MenuVO(String menuId, String menuName, String menuUrl,
			String menuPid, Integer menuLevel, String menuSort, String menuIcon,
			String iconPosition) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuPid = menuPid;
		this.menuLevel = menuLevel;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
		this.iconPosition = iconPosition;
	}
	
	public MenuVO(String menuId, String menuName, String menuUrl,
			String menuPid, Integer menuLevel, String menuSort, String menuIcon) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuPid = menuPid;
		this.menuLevel = menuLevel;
		this.menuSort = menuSort;
		this.menuIcon = menuIcon;
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
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
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
	public String getIconPosition() {
		return iconPosition;
	}
	public void setIconPosition(String iconPosition) {
		this.iconPosition = iconPosition;
	}
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}
}
