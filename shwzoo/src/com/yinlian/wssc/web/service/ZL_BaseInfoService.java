package com.yinlian.wssc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.ZL_BaseInfo;
import com.yinlian.wssc.web.po.ZL_BaseInfoExample;
import com.yinlian.wssc.web.util.Criteria;
/**
 * 中绿基地业务实现层
 * @author NiYongkang
 *
 */
public interface ZL_BaseInfoService {
		int countByExample(ZL_BaseInfoExample example);

	    int deleteByExample(ZL_BaseInfoExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(ZL_BaseInfo record);

	    int insertSelective(ZL_BaseInfo record);

	    /**
	     * 分页查询
	     * @param criteria
	     * @param pc
	     * @param ps
	     * @return
	     */
	    PageBean selectListPage(Criteria criteria, Integer pc, Integer ps);
	    
	    List<ZL_BaseInfo> selectByExample(ZL_BaseInfoExample example);

	    /**
	     * 根据ID查询一个实体
	     * @param id
	     * @return
	     */
	    ZL_BaseInfo selectByPrimaryKey(Integer id);

	    int updateByExampleSelective(@Param("record") ZL_BaseInfo record, @Param("example") ZL_BaseInfoExample example);

	    int updateByExample(@Param("record") ZL_BaseInfo record, @Param("example") ZL_BaseInfoExample example);

	    int updateByPrimaryKeySelective(ZL_BaseInfo record);

	    /**
	     * 分组查询
	     * @return
	     */
	    List<ZL_BaseInfo> selectGroupBy();
	    
	    int updateByPrimaryKey(ZL_BaseInfo record);
}
