package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.web.dto.ActivityDto;
import com.yinlian.wssc.web.dto.FullGiftDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.ActivityMarket;
import com.yinlian.wssc.web.po.Fullgift;
import com.yinlian.wssc.web.util.Criteria;

public interface ActivityService {
	/**
	 * 添加活动
	 */
	int add(ActivityMarket activity) throws Exception;

	/**
	 * 修改活动
	 * 
	 * @param activity
	 * @return
	 * @throws Exception
	 */
	int update(ActivityMarket activity) throws Exception;

	/**
	 * 删除活动
	 * 
	 * @param id
	 * @param userid
	 * @return
	 * @throws Exception
	 */
	int delete(int id, int userid) throws Exception;

	/**
	 * 根据ID获取活动表信息
	 * 
	 * @param id
	 *            活动ID
	 * @return
	 * @throws Exception
	 */
	ActivityMarket getById(int id) throws Exception;

	/**
	 * 添加满赠活动赠品信息
	 * 
	 * @param gift
	 * @return
	 * @throws Exception
	 */
	int addGift(Fullgift gift) throws Exception;

	/**
	 * 删除满赠活动赠品信息
	 * 
	 * @param giftid
	 * @return
	 * @throws Exception
	 */
	int deleteGift(int giftid) throws Exception;

	/**
	 * 根据ID获取满赠活动子表信息
	 * 
	 * @param id
	 *            满赠活动ID
	 * @return
	 * @throws Exception
	 */
	Fullgift getDetailByID(int id) throws Exception;

	/**
	 * 根据活动ID获取赠品列表信息
	 * 
	 * @param actid
	 *            活动ID
	 * @return
	 * @throws Exception
	 */
	List<Fullgift> getGiftListByID(int actid) throws Exception;

	/**
	 * 根据活动ID获取赠品列表信息
	 * 
	 * @param actid
	 *            活动ID
	 * @return
	 * @throws Exception
	 */
	List<FullGiftDto> getGiftList(int actid) throws Exception;

	/**
	 * 根据活动ID集合获取赠品列表信息
	 * 
	 * @param actidlist
	 *            活动ID集合
	 * @return
	 * @throws Exception
	 */
	List<FullGiftDto> getGiftListByIDList(List<Integer> actidlist) throws Exception;

	/**
	 * 根据ID修改赠品数量
	 * 
	 * @param id
	 *            ID
	 * @param count
	 *            赠品数量
	 * @return
	 * @throws Exception
	 */
	int changeCount(int id, int count) throws Exception;

	/**
	 * 根据ID修改赠品数量（积分）
	 * 
	 * @param id
	 *            Id
	 * @param points
	 *            积分
	 * @return
	 * @throws Exception
	 */
	int changePoint(int id, int points) throws Exception;

	/**
	 * 获取满赠活动列表
	 * 
	 * @param index
	 *            当前页
	 * @param size
	 *            页大小
	 * @param num
	 *            活动编号
	 * @param name
	 *            活动名称
	 * @param type
	 *            活动类型
	 * @param shopid
	 *            所属店铺ID
	 * @param checkss
	 *            审核状态
	 * @param detailtype
	 *            活动明细类型
	 * @param s1
	 *            开始时间 开始
	 * @param e1
	 *            开始时间 结束
	 * @param s2
	 *            结束时间 开始
	 * @param e2
	 *            结束时间 结束
	 * @return List<ActivityMarket>
	 * @throws Exception
	 */
	PageBean getList(Criteria criteria,Integer page, int size) throws Exception;

	/**
	 * 获取有效的活动 前台
	 * 
	 * @param spuid
	 *            标准商品ID集合
	 * @param shopid
	 *            店铺ID集合
	 * @param isphone
	 *            是否手机专享 true-手机专享 false-pc端
	 * @return
	 * @throws Exception
	 */
	List<ActivityMarket> getAvailableActivity(List<Integer> spuid, List<Integer> shopid, boolean isphone)
			throws Exception;

	/**
	 * 商品详情页查询满减满赠活动
	 * 
	 * @param spuid
	 *            标准商品ID
	 * @return
	 * @throws Exception
	 */
	List<ActivityMarket> getBySpu(int spuid) throws Exception;

	/**
	 * 根据活动ID获取活动信息
	 * 
	 * @param id
	 *            活动ID
	 * @return
	 * @throws Exception
	 */
	ActivityDto getByMarketID(int id) throws Exception;

	/**
	 * 根据活动ID集合获取活动信息
	 * 
	 * @param idlist
	 *            活动ID集合
	 * @return
	 * @throws Exception
	 */
	List<ActivityDto> GetByMarketIDList(List<Integer> idlist) throws Exception;

	/**
	 * 启用/禁用 状态
	 * 
	 * @param id
	 *            活动ID
	 * @param status
	 *            活动状态
	 * @return
	 * @throws Exception
	 */
	int updateStatus(int id, int status) throws Exception;

	/**
	 * 审核
	 * 
	 * @param id
	 *            活动ID
	 * @param ss
	 *            审核状态 1-提交审核中 2-审核通过 3-审核不通过
	 * @return
	 * @throws Exception
	 */
	int checkStatus(int id, int ss) throws Exception;

	/**
	 * 批量审核
	 * 
	 * @param ids活动ID集合
	 * @param ss审核状态1-提交审核中2-审核通3-审核不通过
	 * @return
	 * @throws Exception
	 */
	int checkStatuList(List<Integer> ids, int ss) throws Exception;

	/**
	 * 修改活动内部审核状态
	 * 
	 * @param id
	 *            活动ID
	 * @return
	 * @throws Exception
	 */
	int updateCheck(int id) throws Exception;

	/**
	 * 批量修改活动内部审核状态
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	int updateCheckList(List<Integer> ids) throws Exception;

	/**
	 * 根据FullGift ids 获取FullGift列表
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	List<Fullgift> getByIds(List<Integer> ids) throws Exception;

	/**
	 * 根据FullGift ids 修改库存数量
	 * 
	 * @param ugiftdtos
	 * @return
	 */
	int updateFullGiftCountByIds(List<UpDateActDto> ugiftdtos) throws Exception;

	/**
	 * 根据 活动ids 修改库存数量
	 * 
	 * @param ugiftdtos
	 * @return
	 */
	int updateStockByIds(List<UpDateActDto> actIds);
	
    List<ActivityDto> getActivityDtoByIDs(List<Integer> list)throws Exception;
	
    ActivityDto getActivityDtoByID(Integer id)throws Exception;
    
    /**
     * 根据赠品类型和赠品ID获取赠品信息
     * @param gtype
     * @param gid
     * @return
     * @throws Exception
     */
    Fullgift getbyTypeAndGiftid(int gtype,int gid) throws Exception;

}
