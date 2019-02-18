package com.pushwin.batchwork.dao;

import com.pushwin.batchwork.model.TBasisProduct;

public interface IProductDao extends IBaseDAO<TBasisProduct>{

	void productOffOnline();

}
