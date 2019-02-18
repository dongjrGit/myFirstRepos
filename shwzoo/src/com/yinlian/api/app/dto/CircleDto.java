package com.yinlian.api.app.dto;
/**
 * 同步客户端购物车数据到数据库
 * @author Administrator
 *
 */
public class CircleDto {

	private Integer  id;         //商圈id
	private String name;       //商圈name
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static void  main(String[] args) {
		
	}
}
