package com.yinlian.wssc.web.util;
/**
 * 分页查询专题
 * CriteriaTopic.java
 * @author Administrator
 * @version $Id: CriteriaTopic.java, v 0.1 2016年4月8日 下午2:37:22 Administrator Exp $
 */
public class CriteriaFloorproduct extends Criteria {

	private int fid;
	
	/*private int display;*/
	
	private int type;

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	/*public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}*/

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
