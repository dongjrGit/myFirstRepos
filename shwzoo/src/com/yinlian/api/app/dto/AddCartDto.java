package com.yinlian.api.app.dto;
/**
 * 同步客户端购物车数据到数据库
 * @author Administrator
 *
 */
public class AddCartDto {

	private String sid;         //店铺id
	private String proid;       //商品ID
	private String procount;    //商品数量
	private String type;        //组合商品 0-普通商品 1-组合商品 2-秒杀 3-闪购
	private String spikeid;     //秒杀活动ID
	private String issel;       //是否选中 0-否 1-是
	private String usetime;     //使用时间
	
	public String getUsetime() {
		return usetime;
	}
	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getProid() {
		return proid;
	}
	public void setProid(String proid) {
		this.proid = proid;
	}
	public String getProcount() {
		return procount;
	}
	public void setProcount(String procount) {
		this.procount = procount;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSpikeid() {
		return spikeid;
	}
	public void setSpikeid(String spikeid) {
		this.spikeid = spikeid;
	}
	public String getIssel() {
		return issel;
	}
	public void setIssel(String issel) {
		this.issel = issel;
	}
	
}
