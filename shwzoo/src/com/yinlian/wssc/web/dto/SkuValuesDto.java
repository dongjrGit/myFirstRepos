package com.yinlian.wssc.web.dto;

public class SkuValuesDto {

	public Integer valueid;
	
	public String value;
	
	public Integer status;
	
	public Integer specsid;
	
	public String specsname;
	
	public Integer skuvalueid;
	
	public Integer skuid;
	
	/**
	 * 规格值ID
	 * @return
	 */
	public Integer getValueid() {
		return valueid;
	}
	public void setValueid(Integer valueid) {
		this.valueid = valueid;
	}
	
	/**
	 * 规格ID
	 * @return
	 */
	public Integer getSpecsid() {
		return specsid;
	}
	public void setSpecsid(Integer specsid) {
		this.specsid = specsid;
	}
	/**
	 * 规格值
	 * @return
	 */
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 规格值状态
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}	
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 库存关联规格值ID
	 * @return
	 */
	public Integer getSkuvalueid() {
		return skuvalueid;
	}
	public void setSkuvalueid(Integer skuvalueid) {
		this.skuvalueid = skuvalueid;
	}
	/**
	 * 库存ID
	 * @return
	 */
	public Integer getSkuid() {
		return skuid;
	}
	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}

	/**
	 * 规格名称
	 * @return
	 */
	public String getSpecsname() {
		return specsname;
	}
	public void setSpecsname(String specsname) {
		this.specsname = specsname;
	}
	
	
}
