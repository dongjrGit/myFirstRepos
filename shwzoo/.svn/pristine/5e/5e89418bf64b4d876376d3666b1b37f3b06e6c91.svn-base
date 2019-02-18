package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.AdvertListDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.AdvertImgExample;
import com.yinlian.wssc.web.po.Advertimgdictionary;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 广告图片业务层
 * @author Administrator
 *
 */
public interface AdvertImgService {

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(AdvertImg record) throws Exception;

    AdvertImg selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKey(AdvertImg record) throws Exception;

    PageBean getListByPage(Criteria criteria, int page, int size) throws Exception;

    int deleteAdvert(Integer id) throws Exception;

    int updateOrder(Integer sort, Integer id) throws Exception;

    int updateStatus(Integer status, Integer id) throws Exception;

    /**
     * 查询广告的字典列表
     * @return
     */
    List<Advertimgdictionary> queryList() throws Exception;

    /**
     * 根据id查询字典信息
     * @param id
     * @return
     */
    Advertimgdictionary queryDicByMark(Integer mark) throws Exception;
    
    List<AdvertListDto> getListByType(Integer type,Integer position) throws Exception;
    
    List<AdvertImg> selectByexample(AdvertImgExample example) throws Exception;

	List<AdvertListDto> getListByTypes(Integer type, Integer position)
			throws Exception;
	
	/**
	 * 根据shopid查询店铺店内图片
	 * @param shopid
	 * @return
	 * @throws Exception
	 */
	List<AdvertImg> getShopImgList(Integer shopid)throws Exception;

}
