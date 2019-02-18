package com.yinlian.wssc.web.dto;

import java.util.List;

import com.yinlian.Enums.ClassifyPageType;
import com.yinlian.Enums.WebSetEnum;
import com.yinlian.wssc.web.po.Articles;
import com.yinlian.wssc.web.po.Navclassfy;

import data.StringUtil;

public class NavclassfyDto extends Navclassfy {
	
	private List<NavclassfyDto> list;
	
	private List<Articles> articles;
	
	private String webSetName;
	
	private String pageTypeName;

	public List<NavclassfyDto> getList() {
		return list;
	}

	public void setList(List<NavclassfyDto> list) {
		this.list = list;
	}

	public String getWebSetName() {
		String str="";
		if(StringUtil.isNotNull(getWebSet())){
			if (getWebSet().indexOf(WebSetEnum.wap.getValue()+"")>=0) {
				str+=WebSetEnum.wap.name()+",";
			}
			if (getWebSet().indexOf(WebSetEnum.pc.getValue()+"")>=0) {
				str+=WebSetEnum.pc.name()+",";
			}
			if (getWebSet().indexOf(WebSetEnum.app.getValue()+"")>=0) {
				str+=WebSetEnum.app.name()+",";
			}
			if (getWebSet().indexOf(WebSetEnum.wechat.getValue()+"")>=0) {
				str+=WebSetEnum.wechat.name()+",";
			}
			str=str.substring(0,str.length()-1);
			webSetName=str;
		}
		return webSetName;
	}

	public void setWebSetName(String webSetName) {
		this.webSetName = webSetName;
	}

	public String getPageTypeName() {
		if(getPageType()!=null){
			pageTypeName=ClassifyPageType.valueOf(getPageType()).name();
		}
		return pageTypeName;
	}

	public void setPageTypeName(String pageTypeName) {
		this.pageTypeName = pageTypeName;
	}

	public List<Articles> getArticles() {
		return articles;
	}

	public void setArticles(List<Articles> articles) {
		this.articles = articles;
	}
	
}
