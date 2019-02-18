package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.CriteriaVHpSku;
import com.yinlian.wssc.web.po.Highspecialty;
import com.yinlian.wssc.web.po.V_Hp_Sku;

public interface HighSpecialtyService {

	/**
	 * 获取优质特产列表
	 * 
	 * @param criteria
	 * @param index
	 * @param size
	 * @return
	 * @throws Exception
	 */
	PageBean getList(CriteriaVHpSku criteria, Integer index, Integer size) throws Exception;

	/**
	 * 添加优质特产
	 * 
	 * @param highspecialty
	 * @return
	 * @throws Exception
	 */
	int insertHiSpe(Highspecialty highspecialty) throws Exception;

	/**
	 * 修改优质特产
	 * 
	 * @param highspecialty
	 * @return
	 * @throws Exception
	 */
	int updateHiSpe(Highspecialty highspecialty) throws Exception;

	/**
	 * 删除优质特产
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteHiSpeById(Integer id) throws Exception;

	/**
	 * 根据id查询优质特产
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Highspecialty selListById(Integer id) throws Exception;

	/**
	 * 修改优质特产状态
	 * 
	 * @param id
	 * @param state
	 * @return
	 * @throws Exception
	 */
	int setType(Integer id, Integer state) throws Exception;

	/**
	 * 根据id查询优质特产表视图
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	V_Hp_Sku selVById(Integer id) throws Exception;

	List<Highspecialty> getByRecommend() throws Exception;

	/**
	 * app 查询优质特产 （例如中绿资讯）
	 * 
	 * @param criteria
	 * @param page
	 * @param zise
	 * @throws Exception
	 */
	PageBean getApplist(AppNewsCriteria criteria, Integer page, Integer size) throws Exception;

}
