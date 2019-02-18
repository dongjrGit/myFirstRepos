package com.yinlian.wssc.web.dto;

import java.util.List;

import com.yinlian.wssc.web.po.Specsvalues;

/**
 * 商品规格及规格值信息
 * 
 * @author Administrator
 *
 */
public class SpecsValueDto {

	private Integer specsID;
	private String specsName;
	private Integer classID;
	private Integer specsStatus;
	private Boolean isEntry;
	private Integer orderBy;
	private Integer specsTypeID;
	private String displayLocation;
	private Integer ismust;
	private List<Specsvalues> valuelist;

	/**
	 * 规格ID
	 * 
	 * @return
	 */
	public Integer getspecsID() {
		return specsID;
	}

	public void setspecsID(Integer id) {
		this.specsID = id;
	}

	/**
	 * 规格名称
	 * @return
	 */
	public String getspecsName() {
		return specsName;
	}

	public void setspecsName(String name) {
		this.specsName=name;
	}
	/**
	 * 规格所属分类ID
	 * @return
	 */
	public Integer getclassID() {
		return classID;
	}

	public void setclassID(Integer id) {
		this.classID = id;
	}
	/**
	 * 规格状态
	 * @return
	 */
	public Integer getspecsStatus() {
		return specsStatus;
	}
	public void setspecsStatus(Integer status) {
		this.specsStatus = status;
	}
	/**
	 * 规格是否可输入
	 * @return
	 */
	public Boolean getisEntry() {
		return isEntry;
	}
	public void setisEntry(Boolean ise) {
		this.isEntry = ise;
	}
	
	/**
	 * 规格排序
	 * @return
	 */
	public Integer getorderBy() {
		return orderBy;
	}
	public void setorderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	
	/**
	 * 规格类型ID
	 * @return
	 */
	public Integer getspecsTypeID() {
		return specsTypeID;
	}
	public void setspecsTypeID(Integer specsTypeID) {
		this.specsTypeID = specsTypeID;
	}
	
	/**
	 * 规格显示位置
	 * @return
	 */
	public String getdisplayLocation() {
		return displayLocation;
	}

	public void setdisplayLocation(String displayLocation) {
		this.displayLocation=displayLocation;
	}	
	
	/**
	 * 对应规格值列表
	 * @return
	 */
	public List<Specsvalues> getvaluelist(){
		return valuelist;
	}
	
	public void setvaluelist(List<Specsvalues> valuelist) {
		this.valuelist = valuelist;
	}

	public Integer getIsmust() {
		return ismust;
	}

	public void setIsmust(Integer ismust) {
		this.ismust = ismust;
	}
}
