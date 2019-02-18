package com.yinlian.api.app.dto;

import java.util.List;

import com.yinlian.wssc.web.po.CommentAbr;

public class CommentProDetailListDto extends CommentAbr {

 private  List<CommentBaseDto> list;

public List<CommentBaseDto> getList() {
	return list;
}

public void setList(List<CommentBaseDto> list) {
	this.list = list;
}

}
