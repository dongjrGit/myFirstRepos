package com.yinlian.wssc.web.util;

import java.util.Date;

public class CriteriaDdtj extends Criteria{

	private Integer id;   //卖家id
	
	private String  type;       //统计类型
	
	private String  zy;         //直营
	
	private Date  datef;       //开始时间
	
	private Date  datet;       //结束时间
	
	private int aftertype;     //售后类型
	private int tjtype;        //售后统计类型
	private String  datey;
	private String  datem;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getZy() {
		return zy;
	}

	public void setZy(String zy) {
		this.zy = zy;
	}

	public Date getDatef() {
		return datef;
	}

	public void setDatef(Date datef) {
		this.datef = datef;
	}

	public Date getDatet() {
		return datet;
	}

	public void setDatet(Date datet) {
		this.datet = datet;
	}

	public int getAftertype() {
		return aftertype;
	}

	public void setAftertype(int aftertype) {
		this.aftertype = aftertype;
	}

	public String getDatey() {
		return datey;
	}

	public void setDatey(String datey) {
		this.datey = datey;
	}

	public String getDatem() {
		return datem;
	}

	public void setDatem(String datem) {
		this.datem = datem;
	}

	public int getTjtype() {
		return tjtype;
	}

	public void setTjtype(int tjtype) {
		this.tjtype = tjtype;
	}

}
