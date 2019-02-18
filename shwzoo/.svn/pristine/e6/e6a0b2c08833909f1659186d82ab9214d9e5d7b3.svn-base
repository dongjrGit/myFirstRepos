/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.yinlian.wssc.web.util;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;


/**
 * 模板的过滤器
 * 
 * @author Administrator
 * @version $Id: MySiteMeshFilter.java, v 0.1 2016年3月1日 上午11:53:57 Administrator
 *          Exp $
 */
public class MySiteMeshFilter extends ConfigurableSiteMeshFilter {

	/**
	 * @see org.sitemesh.config.ConfigurableSiteMeshFilter#applyCustomConfiguration(org.sitemesh.builder.SiteMeshFilterBuilder)
	 */
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		super.applyCustomConfiguration(builder);

		builder.addDecoratorPath("/platform/*", "/decorators/platform/decoratorPlatform.jsp")
				.addExcludedPath("/platform/index");
		builder.addDecoratorPath("/zoo/*", "/decorators/platform/decoratorPlatform.jsp");

		builder.addDecoratorPath("/seller/*", "/decorators/seller/decoratorSeller.jsp").addExcludedPath("/seller/index")
				.addExcludedPath("/seller/login").addExcludedPath("/seller/register").addExcludedPath("/seller/findPwd");
		// builder.addDecoratorPath("/seller/*",
		// "/decorators/seller/decoratorSeller.jsp");
				
		builder.addDecoratorPath("/web/*", "/decorators/decoratorsPC.html")
		.addExcludedPath("/web/orderpay.html").addExcludedPath("/web/user/showlogin")
		.addExcludedPath("/web/orderbuynow.html");
		
		/**
		 * 绿色中国
		 */
		builder.addDecoratorPath("/lszg/*", "/decorators/decoratorsLs.html");
		/**
		 * 中绿资讯
		 */
		builder.addDecoratorPath("/zlzx/*", "/decorators/decoratorsZLZX.html");

		builder.addDecoratorPath("/member/*", "/decorators/decoratorsMember.html")
		.addExcludedPath("/member/user/showlogin")
		.addExcludedPath("/member/order/orderPay.html")
		.addExcludedPath("/member/user/register")
		.addExcludedPath("/member/order/orderPaySuccess.html")
		.addExcludedPath("/member/order/orderPayError.html");
		
		builder.addDecoratorPath("/wap/*", "/decorators/wap/decoratorWap.jsp")
		.addExcludedPath("/wap/index_foot").addExcludedPath("/wap/error");
				


	}

}
