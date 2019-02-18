package com.yinlian.wssc.web.mapper;

import com.yinlian.wssc.web.po.AccountNo;

public interface AccountNoMapper {
	Integer getNo() throws Exception;

	int insert(AccountNo orm) throws Exception;
}
