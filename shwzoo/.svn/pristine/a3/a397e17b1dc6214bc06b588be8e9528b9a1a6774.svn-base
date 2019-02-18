package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.web.po.Area;

/**
 * 控制面板里的地区管理的业务类
 * @author Administrator
 *
 */

public interface AreaService {

    /**
     * 根据code查询所有的区域
     * @param code
     * @return
     */
    List<Area> selectByCityCode(String code) throws Exception;

    /**
     * 
     * @param id
     * @return
     * @throws Exception
     */
    int deleteAreaByCode(String id) throws Exception;

    int updateAreaByCode(String name, String code) throws Exception;

    /**
     * 根据code查询区域
     * @param code
     * @return
     */
    Area queryByCode(String code) throws Exception;
    
    Area selectByName(String name)throws Exception;

    /**
     * 添加地区
     * 
     * @param name
     * @param code
     * @param fcode
     * @return
     * @throws Exception
     */
	int add(String name, String code1, String fcode) throws Exception;
	/**
	 * 查询所有地区
	 * 
	 * @return
	 * @throws Exception
	 */
	List<Area> query() throws Exception;

	List selectArea(String parentCode) throws Exception;

	Area selectAreaByName(String name, String code) throws Exception;
    

}
