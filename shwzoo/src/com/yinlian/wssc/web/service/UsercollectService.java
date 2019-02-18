package com.yinlian.wssc.web.service;

import java.util.Date;
import java.util.List;

import com.yinlian.pc.dto.ConcernSpuDto;
import com.yinlian.wssc.search.Pc_CollectCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaCollect;
/**
 * 会员收藏的业务类
 * @author Administrator
 *
 */
public interface UsercollectService {

	/**
	 * 分页查询会员商品收藏的信息
	 * @param pc
	 * @param ps
	 * @return
	 */
	PageBean queryMemberCollerByCriteria(Criteria criteria,Integer pc, Integer ps) throws Exception;
	/**
	 * 分页查询会员店铺收藏的信息
	 * 
	 * @param criteria
	 * @param pc
	 * @param ps
	 * @return
	 * @throws Exception
	 */
	PageBean queryMemberCollerShopByCriteria(CriteriaCollect criteria,
			Integer pc, Integer ps) throws Exception;

	/**
	 * 根据id修改会员收藏
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int updateMemberCollectById(Usercollect usercollect) throws Exception;

	/**
	 * 根据id查询会员收藏的信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Usercollect queryById(Integer id) throws Exception;
	/**
	 * 添加收藏
	 * @param usercollect
	 * @return
	 * @throws Exception
	 */
	public int add(Usercollect usercollect) throws Exception; 
	/**
	 * 删除收藏的店铺
	 * @param userId
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	public int delShop(Integer userId,Integer shopId,Date delTime)throws Exception;
	/**
	 * 删除多个收藏的店铺
	 * @param userId
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	public int delCollectShops(Integer userid,List<Integer> shopids,Date delTime)throws Exception;
	/**
	 * 删除收藏的商品
	 * @param userId
	 * @param spuId
	 * @return
	 * @throws Exception
	 */
	public int delSpu(Integer userId,Integer spuId,Date delTime) throws Exception;
	/**
	 * 删除多个收藏的商品
	 * @param userid
	 * @param spuids
	 * @param delTime
	 * @return
	 * @throws Exception
	 */
	public int delCollectSpus(Integer userid,List<Integer> spuids,Date delTime) throws Exception;
	/**
	 * 查询会员是否已经收藏给店铺
	 * @param userId
	 * @param shopId
	 * @return
	 * @throws Exception
	 */
	public List<Usercollect>  selectShop(Integer userId,Integer shopId) throws Exception;
	/**
	 * 查询会员是否已收藏该商品
	 * @param userId
	 * @param spuId
	 * @return
	 * @throws Exception
	 */
	public List<Usercollect> selectSpu(Integer userId,Integer spuId) throws Exception;
	/**
	 * 按照类型查询收藏
	 * @param criteria 搜索条件
	 * @param pc 起始页
	 * @param ps 总行数
	 * @return
	 * @throws Exception
	 */
	 
	public PageBean selectPage(CriteriaCollect criteria,Integer pc, Integer ps)throws Exception;
	/**
	 * 按照类型查询收藏
	 * @param userId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<Usercollect>  select(Integer userId,Integer type) throws Exception;

	/**
	 * 根据店铺id 获取收藏数量
	 * @param shopid
	 * @return
	 * @throws Exception
	 */
	Integer getCountByShopId(Integer shopid) throws Exception;

	/**
	 * 判断是否收藏商品
	 * @param userId
	 * @param spuId
	 * @return
	 * @throws Exception
	 */
	public Boolean IsCollectSpu(Integer userId,Integer spuId) throws Exception;
	
	/**
	 * 判断是否收藏店铺
	 * @param userId
	 * @param spuId
	 * @return
	 * @throws Exception
	 */
	public Boolean IsCollectShop(Integer userId,Integer shopid) throws Exception;
	
	List<ConcernSpuDto> getConcernSpuByUser(Integer userid)throws Exception;
	
	PageBean spuListByPage(Pc_CollectCriteria criteria, Integer pc, Integer ps) throws Exception;
	PageBean ShopListByPage(Pc_CollectCriteria criteria, Integer pc, Integer ps) throws Exception;
	
}
