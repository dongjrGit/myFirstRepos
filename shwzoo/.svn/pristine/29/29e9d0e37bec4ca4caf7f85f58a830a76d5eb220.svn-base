package com.yinlian.wssc.web.task;

import java.util.TimerTask;

import org.springframework.web.context.WebApplicationContext;

import com.yinlian.Extended.LogType;
import com.yinlian.wssc.web.service.LuceneSearchService;
import com.yl.soft.log.LogHandle;

public class ProductSearchTask extends TimerTask {

	private WebApplicationContext context = null;

	private LuceneSearchService luceneSearchService;

	public ProductSearchTask(WebApplicationContext servletContext) {
		this.context = servletContext;
	}

	@Override
	public void run() {
		// 创建全文检索
		try {
			luceneSearchService = (LuceneSearchService) context.getBean("luceneSearchService");
			luceneSearchService.createIndex();
		} catch (Exception e) {
			LogHandle.error(LogType.searchPro, "全文检索商品数据同步错误：" + e.toString());
		}
	}

}
