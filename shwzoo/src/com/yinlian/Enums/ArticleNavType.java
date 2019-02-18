package com.yinlian.Enums;
/**
 * 文章分类类别
 * ArticleNavType.java
 * @author Administrator
 * @version $Id: ArticleNavType.java, v 0.1 2016年5月20日 上午11:49:26 Administrator Exp $
 */
public enum ArticleNavType {
	网站服务(0),
    网站资讯(1),
    网站资质(2),
    网站新闻(3),
    安全问题(4);
    	
	
	private final int value;
	
	public int getValue() {
		return value;
	}
	
	public static ArticleNavType valueOf(int value){
		switch (value) {
		case 0:
			return 网站服务;
		case 1:
			return 网站资讯;
		case 2:
			return 网站资质;
		case 3:
			return 网站新闻;
		case 4:
			return 安全问题;
			
		}
		return null;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	ArticleNavType(int value) {
		this.value = value;
	}

	public String getName() {
		return this.name();
	}
	
}
