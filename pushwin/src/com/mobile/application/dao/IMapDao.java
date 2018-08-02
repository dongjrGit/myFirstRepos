package com.mobile.application.dao;

import java.util.List;

import com.mobile.application.entity.TBasisPosition;
import com.mobile.application.vo.session.SessionVO;

public interface IMapDao  extends IBaseDAO<TBasisPosition>{

	List<?> qryDevicePosition(String userId, String date);

	List<?> qryRealTimePosition(String day, String orgId,SessionVO sessionVO);

}
