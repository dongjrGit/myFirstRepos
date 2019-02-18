package com.yinlian.api.app.dto;

import java.util.List;


public class ShowtimeDto {

	private Integer showyear;
	
	private List<IdValue> showmonth;

	public Integer getShowyear() {
		return showyear;
	}

	public void setShowyear(Integer showyear) {
		this.showyear = showyear;
	}

	public List<IdValue> getShowmonth() {
		return showmonth;
	}

	public void setShowmonth(List<IdValue> showmonth) {
		this.showmonth = showmonth;
	}
	
	
}
