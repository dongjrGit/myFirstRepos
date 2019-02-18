package com.yinlian.wssc.web.util;

/**
 * 菜单的查询类
 * CriteriaMenu.java
 * @author Administrator
 * @version $Id: CriteriaMenu.java, v 0.1 2016年3月14日 下午5:59:34 Administrator Exp $
 */
public class CriteriaMenu extends Criteria {

    private String  name;
    private Integer menutype;
    private Integer type;
    private String rolename;
    
    //菜单名称
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //菜单所属平台类型
    public Integer getMenutype() {
        return menutype;
    }

    public void setMenutype(Integer menutype) {
        this.menutype = menutype;
    }

    //菜单类型
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    //角色名称
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
