package com.mobile.application.dao;

import java.util.List;

public interface IShootMaterDao {

	List<?> qryShootMater(String type);

	List<?> qryMaterbypid(String materType, String materName);

	List<?> qryShootingDict(String lastUpdateTime);

}
