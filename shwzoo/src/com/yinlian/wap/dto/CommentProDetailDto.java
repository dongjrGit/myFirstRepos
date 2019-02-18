package com.yinlian.wap.dto;

import java.util.List;

import com.yinlian.wssc.web.po.CommentAbr;

public class CommentProDetailDto extends CommentAbr {
	 private  List<CommentDto> list;

	 public List<CommentDto> getList() {
	 	return list;
	 }

	 public void setList(List<CommentDto> list) {
	 	this.list = list;
	 }
}
