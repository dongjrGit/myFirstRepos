package com.gps.entity;


/**
 * 经纬度
 * @author Administrator
 *
 */
public class LonLat {
	private String id ;
	private Home h;	//家访
	private Unit c;	//单位访

	public Home getH() {
		return h;
	}

	public void setH(Home h) {
		this.h = h;
	}

	public Unit getC() {
		return c;
	}

	public void setC(Unit c) {
		this.c = c;
	}

	public LonLat() {
		super();
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LonLat(Home h, Unit c) {
		super();
		this.h = h;
		this.c = c;
	}

	public LonLat(String id, Home h, Unit c) {
		super();
		this.id = id;
		this.h = h;
		this.c = c;
	}

	public LonLat(String id) {
		super();
		this.id = id;
	}

}
