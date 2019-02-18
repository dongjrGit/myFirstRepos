package com.pushwin.batchwork.dao;

import com.pushwin.batchwork.model.TBasisProduct;

public interface INoticeDao extends IBaseDAO<TBasisProduct>{

	void noticeExpire();

}
