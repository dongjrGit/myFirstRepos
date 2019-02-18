package com.yinlian.wssc.web.util;

import java.util.List;

/**
 * 商品的多条件查询类
 * @author Administrator
 *
 */
public class CriteriaCommodity extends Criteria {
	
	private Integer specsid;        // 规格id
	
	private Integer brandid;      //品牌id
	
	private String  brandname;      // 商品品牌的名称
	
	private Integer fatherid;   //分类父级ID
	
	private String ids;        //分类ID集合
	
	private String  name;      // 分类的名称
	
	private Integer  classStatus=-1;      // 分类的状态
	
	private Integer specstypeid;        // 规格类型id
	
	private List<String> idsarray;
	
	public Integer getSpecsid() {
		return specsid;
	}
	
	public void setSpecsid(Integer specsid) {
		this.specsid = specsid;
	}
	
	public String getBrandname() {
		return brandname;
	}
	
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}

	public Integer getFatherid() {
		return fatherid;
	}

	public void setFatherid(Integer fatherid) {
		this.fatherid = fatherid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getClassStatus() {
		return classStatus;
	}

	public void setClassStatus(Integer classStatus) {
		this.classStatus = classStatus;
	}

	public Integer getBrandid() {
		return brandid;
	}

	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}

	public Integer getSpecstypeid() {
		return specstypeid;
	}

	public void setSpecstypeid(Integer specstypeid) {
		this.specstypeid = specstypeid;
	}

	public List<String> getIdsarray() {
		return idsarray;
	}

	public void setIdsarray(List<String> idsarray) {
		this.idsarray = idsarray;
	}
	
}
