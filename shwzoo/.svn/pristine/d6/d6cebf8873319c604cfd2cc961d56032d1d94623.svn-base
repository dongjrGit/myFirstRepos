package com.yinlian.wssc.web.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.giftcard;
import com.yinlian.wssc.web.po.giftcardExample;
import com.yinlian.wssc.web.util.Criteria;

public interface giftcardService {
	
	
	int countByExample(giftcardExample example);

    int deleteByExample(giftcardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(giftcard record);

    int insertSelective(giftcard record);

    /**
     * 条件查询
     * @param exm
     * @return  List<giftcard>
     */
    List<giftcard> selectByExample(giftcardExample exm);
   
    /**
     * 根据ID查询一个实体
     * @param id
     * @return giftcard
     */
    giftcard selectByPrimaryKey(Integer id);
	
    /**
	 * 分页列表
	 * @param criteria
	 * @param page
	 * @param size
	 * @return PageBean
	 */
    PageBean selectListPage(Criteria criteria,Integer page,Integer size);
    
    int updateByExampleSelective(@Param("record") giftcard record, @Param("example") giftcardExample example);

    int updateByExample(@Param("record") giftcard record, @Param("example") giftcardExample example);

   
    int updateByPrimaryKeySelective(giftcard record);
    
    /**
     * 根据实体修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(giftcard record);

	int giftcardRecharge(String code, String password, int usedUserid,
			String phone) throws Exception;
}
