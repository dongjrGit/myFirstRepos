package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisSystem;

public interface ISysDao extends IBaseDAO<TBasisSystem>{

	List<?> qrySysSetting();

}
