package com.techown.Enums;

import com.fasterxml.jackson.annotation.JsonFormat;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BannerType {
	PRODUCT("商品", 2), SHOP("商店", 1), SCENIC("景点", 3),EDITOR("图文信息",4), Link("外部链接", 0);
	private String name;
	
	private int code;

	private BannerType(String name, int code) {
		this.name = name;
		this.code = code;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

