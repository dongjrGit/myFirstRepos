package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.AdvertImgAppDto;
import com.yinlian.api.app.dto.AdvertListDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.util.Criteria;

/**
 * 广告图片业务层
 * @author Administrator
 *
 */
public interface AdverisingService {

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Advertising record) throws Exception;
  
    Advertising selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKey(Advertising record) throws Exception;

    PageBean getListByPage(Criteria criteria, int page, int size) throws Exception;

    int deleteAdvertising(Integer id) throws Exception;

    int updateStatus(Integer status, Integer id) throws Exception;
    
    List<Advertising> getListByType(int pagemark,int webset) throws Exception;
    
    List<Advertising> getListByTypeAndDisplay(int pagemark,int display,int webset) throws Exception;
    
    List<AdvertListDto> getListByTypeApi(int pagemark,int webset) throws Exception;
    
    List<AdvertImgAppDto> getListByTypeAndDisplayApi(int pagemark,int display,int webset) throws Exception;

    /**
     * 获取店铺广告
     * @param pagemark 页标识
     * @param display 显示
     * @param webset 通道标识
     * @param shopid 店铺ID
     * @return
     */
    List<Advertising> getListByTypeAndDisplay(int pagemark,int display,int webset, Integer shopid) throws Exception;

	Advertising getByAdvertact(int type,Integer status) throws Exception;

}
