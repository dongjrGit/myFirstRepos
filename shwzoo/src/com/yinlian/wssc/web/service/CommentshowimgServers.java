package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.po.Commentshowimg;

/**
 * 晒单照片的业务类
 * @author Administrator
 *
 */
public interface CommentshowimgServers {

	/**
	 * 根据id查询晒单照片
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Commentshowimg queryById(Integer id) throws Exception;

	/**
	 * 根据id修改晒单照片
	 * @param commentshowimg
	 * @return
	 * @throws Exception
	 */
	int updateById(Commentshowimg commentshowimg) throws Exception;

}
