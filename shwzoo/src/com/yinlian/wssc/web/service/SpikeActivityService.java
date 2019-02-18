package com.yinlian.wssc.web.service;

import java.util.Date;
import java.util.List;

import com.yinlian.api.app.dto.ExcitingActDetailDto;
import com.yinlian.api.app.dto.PromotionDto;
import com.yinlian.api.app.dto.SpikeDto;
import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.SpSpuDto;
import com.yinlian.wssc.web.dto.sgSpuDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.SpikeSpu;
import com.yinlian.wssc.web.po.Spikeactivity;
import com.yinlian.wssc.web.po.Spikeshop;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaActivity;

public interface SpikeActivityService {

	/**
	 * 根据活动ID和SpuID获取数据
	 * 
	 * @param spikeid
	 *            秒杀活动ID
	 * @param spuid
	 *            标准商品ID
	 * @return
	 * @throws Exception
	 */
	SpSpuDto getSpSpuDtoBySpikeID(int spikeid, int spuid) throws Exception;

	/**
	 * 根据SpikeSpu ids 修改库存数量
	 * 
	 * @param spiIds
	 * @return
	 */
	int updateSpikeSpuCountByIds(List<UpDateActDto> spiIds) throws Exception;

	// 秒杀活动管理
	// 添加活动
	int insert(Spikeactivity record) throws Exception;

	// 根据ID获取活动
	Spikeactivity selectByID(Integer id) throws Exception;
	// 根据spuID获取所有参与的活动
    List<Spikeactivity>Spikeactivitylist(Integer spuid,Integer type) throws Exception;
	// 编辑活动
	int update(Spikeactivity record) throws Exception;

	// 获取活动列表
	PageBean getListPage(Criteria criteria,Integer page,Integer size) throws Exception;

	// 编辑状态
	int updateStatus(int status, int id) throws Exception;

	// 删除活动 逻辑删除
	int deleteSpike(int id,int userid) throws Exception;

	// 获取店铺申请活动记录
	PageBean GetApplyBySpikeIDPage(Criteria criteria,Integer page,Integer size)
			throws Exception;

	// 卖家获取活动信息
	PageBean GetShopApplyByPage(Criteria criteria,Integer page,Integer size)
			throws Exception;

	// 获取参与活动商品信息
	PageBean getSpuBySpikeIDPage(Criteria criteria,Integer page,Integer size)
			throws Exception;
	
	List<Spikeactivity> getByDate(Date start,Integer spiketype,Integer spikeid)throws Exception;
	// 店铺申请活动 管理

	int insertSpikeshop(Spikeshop record) throws Exception;

	Spikeshop getSpikeshopByID(Integer id) throws Exception;

	int deleteSpikeshop(Integer id) throws Exception;
	
	int seekSeckillCount();

	/**
	 * 店铺申请活动
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int updateCheck(int id, int status) throws Exception;

	/**
	 * 批量审批店铺申请
	 * 
	 * @param idlist
	 * @param status
	 * @return
	 * @throws Exception
	 */
	int updateCheckList(List<Integer> idlist,int status) throws Exception;

	/**
	 * 根据活动ID获取店铺申请记录
	 * 
	 * @param spikeid
	 * @return
	 * @throws Exception
	 */
	List<Spikeshop> getBySpikeID(int spikeid) throws Exception;

	// 参与活动商品管理
	int deleteSpikeSpu(Integer id) throws Exception;

	int insertSpikeSpu(SpikeSpu record) throws Exception;
	
	int updateSpikeSpu(SpikeSpu record) throws Exception;
	
	int updateIsPhone(int isphone,int id)throws Exception;

	SpikeSpu selectSpikeSpuByID(Integer id) throws Exception;
	
	List<Spu> getSpuStartwithName(int shopid,int spikeid,String name)throws Exception;
	
	int getSpuPriceStartwithName(int shopid,int spikeid,String name)throws Exception;
	
	Date getServiceDate()throws Exception;
	
	SpikeDto getmsActivity(CriteriaActivity criteria,int page,int size,ReusltItem item)throws Exception;
	
	PageBean getSgSpuByPage(Criteria criteria,int page,int size )throws Exception;
	
	PageBean gettgSpuByPage(Criteria criteria,int page,int size )throws Exception;
	
	List<PromotionDto> getcxActivity()throws Exception;
	
	public PageBean getSpuListByID(Criteria criteria,int page,int size)throws Exception;
	
	public PageBean getcxSpuByPage(Criteria criteria,int page,int size)throws Exception;
	
	public PageBean getExcitingByPage(Criteria criteria,int page,int size)throws Exception;
	
	/**
	 * 根据用户ID和经彩活动ID获取验证码
	 * @param spikeid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	String getCode(int spikeid,int userid) throws Exception;
	
	/**
	 * 用户 参与经彩活动
	 * @param spikeid
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	String addUserSpike(int spikeid,int userid)throws Exception;
	
	ExcitingActDetailDto getBySpikeid(Integer id,Integer userid)throws Exception;
	
	PageBean getUserListBySpikeID(Criteria criteria,int page,int size)throws Exception;

	int updateUseCode(Integer id)throws Exception;
	
	PageBean getExcitListPage(Criteria criteria,int page,int size)throws Exception;
	
	sgSpuDto getActBySpuid(int spuid,int acttype)throws Exception;

	List<SpikeDto> getmsActivityList(CriteriaActivity criteria, int pages, int sizes,
			ReusltItem item) throws Exception;

	 List<Spikeactivity> getByEndDate(Date start,Integer spiketype,Integer spikeid)throws Exception;
}
