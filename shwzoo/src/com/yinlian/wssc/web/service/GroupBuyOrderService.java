package com.yinlian.wssc.web.service;


import java.util.List;

import com.yinlian.api.app.dto.GroupOrderStateDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.GroupbuyhistoryWithBLOBs;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.po.Grouporderdetail;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaGroupBuy;

public interface GroupBuyOrderService {

	/**
	 * 添加团购订单
	 * @param gb
	 * @return
	 * @throws Exception
	 */
	    int insert(int useid,int gid,int count,float disc,Integer beans,
				String username,BaseResult item) throws Exception;
/**
 * 根据ID获取订单信息
 * @param id
 * @return
 * @throws Exception
 */
	    Groupbuyorder selectByPrimaryKey(Integer id) throws Exception;
/**
 * 修改订单
 * @param record
 * @return
 * @throws Exception
 */
	    int updateByPrimaryKey(Groupbuyorder record) throws Exception;
	    
	   int updateByPrimaryKeySelective(Groupbuyorder record) throws Exception;
	    /**
	     * 删除团购订单
	     * @param id
	     * @return
	     * @throws Exception
	     */
	    int delOrder(int id) throws Exception;
	    
	    /**
	     * 团购订单付款
	     * @param id
	     * @return
	     * @throws Exception
	     */
	    int updateFK(int id,int userid) throws Exception;
	    /**
	     * 取消团购订单
	     * @param id
	     * @return
	     * @throws Exception
	     */
	    int updateCancel(int id) throws Exception;
	    /**
		 * 团购订单退款
		 * 
		 * @param id
		 * @return
		 * @throws Exception
		 */
		public int updateTK(int id) throws Exception;
//	    /**
//	     * 添加团购劵备份
//	     * @param record
//	     * @return
//	     * @throws Exception
//	     */
//	    int insert(GroupbuyhistoryWithBLOBs record)throws Exception;
//	    /**
//	     * 根据团购劵哈希码查询是否存在
//	     * @param hacode
//	     * @return
//	     * @throws Exception
//	     */
//	    int countByHaCode(String hacode)throws Exception;
	    
	    /**
	     * 根据团购劵哈希码获取团购信息
	     * @param hacode
	     * @return
	     * @throws Exception
	     */
	    GroupbuyhistoryWithBLOBs getByHaCode(String hacode)throws Exception;
	    
	    /**
	     * 根据团购码获取订单信息
	     * @param code
	     * @return
	     * @throws Exception
	     */
	    Grouporderdetail getByCode(String code) throws Exception;
	    
	    /**
	     * 根据订单ID获取团购劵信息
	     * @param orderid
	     * @return
	     * @throws Exception
	     */
	    List<Grouporderdetail> getByOrderID(Integer orderid) throws Exception;
	    /**
	     * 单个使用团购卷
	     * @param status
	     * @param id
	     * @return
	     * @throws Exception
	     */
	    int updateUse(int id,ReusltItem item) throws Exception;
	    
	    /**
	     * 批量使用团购卷
	     * @param ids
	     * @return
	     * @throws Exception
	     */
	    int updateUseList(List<Integer> ids) throws Exception;
	    
	    Grouporderdetail getDetailByID(int id) throws Exception;
	    /**
	     * API获取订单列表
	     * @param pageBeanUtil
	     * @return
	     * @throws Exception
	     */
	    PageBean getGroupByPage(Criteria criteria,int page,int size)throws Exception;
	    
	    /**
	     * 批量删除订单
	     * @param list
	     * @return
	     * @throws Exception
	     */
	    int delOrderList(List<Integer> list) throws Exception;
	    
	    PageBean getGroupOrderByToTalPage(CriteriaGroupBuy criteria, int page,int size) throws Exception;
	    
	    PageBean getGroupOrderByPage(CriteriaGroupBuy criteria, int page,int size) throws Exception;
	    
	    PageBean getListOrderByPage(Criteria criteria , int page,int size)throws Exception;
	    /**
	     * 删除团购订单
	     * 
	     * @param id
	     * @return
	     * @throws Exception
	     */
		int delGroupOrder(Integer id) throws Exception;
		
		/**
		 * 平台或卖家获取团购订单列表
		 * @param criteria
		 * @param page
		 * @param size
		 * @return
		 * @throws Exception
		 */
		PageBean getGroupOrderByPage(Criteria criteria , int page,int size) throws Exception;
		
		public int updateFK(String ordercode,Double money,String trade_no) throws Exception;
		
		public int savePayOrder(String code,Integer userid,String username,BaseResult item) throws Exception;
		
		GroupOrderStateDto selectCount(Integer userid) throws Exception;
		/**
		 * 待付款数量
		 * 
		 * @param userid
		 * @return
		 * @throws Exception
		 */
		int selectDfkCount(Integer userid) throws Exception;
		/**
		 * 待消费数量
		 * 
		 * @param userid
		 * @return
		 * @throws Exception
		 */
		int selectDxfCount(Integer userid) throws Exception;
		/**
		 * 待评论
		 * 
		 * @param userid
		 * @return
		 * @throws Exception
		 */
		int selectDplCount(Integer userid) throws Exception;
		/**
		 * 退款申请中
		 * 
		 * @param userid
		 * @return
		 * @throws Exception
		 */
		int selectTkCount(Integer userid) throws Exception;
		
		public int updateTKSH(int id) throws Exception;
		
		public Groupbuyorder getByOrderCode(String ordercode) throws Exception;
		
		/**
		 * 获取下单一天后未付款的团购订单
		 * @return
		 * @throws Exception
		 */
		List<Groupbuyorder> getUnpaidGroupOrders()throws Exception;
		
		 int updateStatus(int status,int id) throws Exception;
}
