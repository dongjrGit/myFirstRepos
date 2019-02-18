package com.yinlian.wssc.web.po;

import java.util.List;

public class Province {
    private Integer id;

    private String code;

    private String name;
    
    private String bigarea;
    
    public String getBigarea() {
		return bigarea;
	}

	public void setBigarea(String bigarea) {
		this.bigarea = bigarea;
	}

	private List<City> list; // 省下面的所有的市集合

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public List<City> getList() {
		return list;
	}

	public void setList(List<City> list) {
		this.list = list;
	}
    
}