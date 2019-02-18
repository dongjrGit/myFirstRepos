package com.yinlian.api.app.dto;

import com.yinlian.wssc.web.po.Comment;

public class Api_CommentDto extends Comment{
	
	
	private  String   imgs;

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	
}
