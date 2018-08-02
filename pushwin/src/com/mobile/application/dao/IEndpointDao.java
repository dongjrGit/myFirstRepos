package com.mobile.application.dao;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.mobile.application.entity.Paduser;
import com.mobile.application.entity.Puwshlogin;
import com.mobile.application.entity.TBasisActivity;
import com.mobile.application.entity.TBasisDictBranch;
import com.mobile.application.entity.TBasisUser;
import com.mobile.application.vo.activity.ActivityResponseVO;

public interface IEndpointDao extends IBaseDAO<TBasisUser>{

	
	List<?> IsDeviceOut(String devicePin);

	TBasisUser getUserByCode(String userCode);
	
	Paduser puwshlogin(Puwshlogin puwshlogin);

	 @SuppressWarnings("rawtypes")
	 /**
	  * 
	  * @param parameter updateitime
	  * @param usercode
	  * @param orgCode
	  * @param bei
	  * @return
	  */
	 public Map<String,Object>  ParameterNew(String  parameter,String usercode,String orgCode,String bei );
	
	 @SuppressWarnings("rawtypes")
	 /**
	  * 
	  * @param parameter updateitime
	  * @param usercode
	  * @param orgCode
	  * @param bei
	  * @return
	  */
	 public String  iosVersion(String  bankFlag);
	 @SuppressWarnings("rawtypes")
	 /**
	  * 
	  * @param parameter updateitime
	  * @param usercode
	  * @param orgCode
	  * @param bei
	  * @return
	  */
	 public boolean  qrydevice(String userId,String pinId);
	 @SuppressWarnings("rawtypes")
	 /**
	  * 
	  * @param parameter updateitime
	  * @param usercode
	  * @param orgCode
	  * @param bei
	  * @return
	  */
	 public Map<String,Object>  Parameter(Object  parameter);
	 /**
	 /**
	  * 更新密码
	  * @param userpassword
	  * @param userid
	  */
	 void updatePADUserPassword(String userpassword,String userid);
	 
	 void updatePADUserPassword2(String userpassword,String userid);
	 /**
	  * querylist activity list
	  * @param activitycode
	  */
	 
	List queryactivityMessageByCode(List activitycode,String updateTime);
	 
	 /**
	  * querylist activity file list
	  * @param activitycode
	  */
	 
	List queryactivityMessageFileByCode(List activitycode);
	/**
	  * querylist activity ogr list
	  * @param activitycode
	  */
	List queryactivityMessageOrgByCode(String activitycode);

	List<ActivityResponseVO> qryActivitys(String orgCode, String updateTime)
			throws ParseException, IllegalAccessException, InvocationTargetException;

}
