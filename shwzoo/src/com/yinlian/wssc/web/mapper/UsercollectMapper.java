package com.yinlian.wssc.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.pc.dto.ConcernSpuDto;
import com.yinlian.pc.dto.PcShopCollectDto;
import com.yinlian.pc.dto.PcSpuCollectDto;
import com.yinlian.wssc.web.dto.CollectInfo;
import com.yinlian.wssc.web.po.Usercollect;
import com.yinlian.wssc.web.po.UsercollectExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface UsercollectMapper {
    int countByExample(UsercollectExample example);

    int deleteByExample(UsercollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Usercollect record);

    int insertSelective(Usercollect record);

    List<Usercollect> selectByExample(UsercollectExample example);

    Usercollect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Usercollect record, @Param("example") UsercollectExample example);

    int updateByExample(@Param("record") Usercollect record, @Param("example") UsercollectExample example);

    int updateByPrimaryKeySelective(Usercollect record);

    int updateByPrimaryKey(Usercollect record);

    /**
     * 分页查询会员商品收藏
     * @param pageBeanUtil
     * @return
     * @throws Exception
     */
	List<CollectInfo> selectMemberCollectByPage(PageBeanUtil pageBeanUtil);
	/**
	 * 
	 * 分页查询会员店铺收藏
	 * @param pageBeanUtil
	 * @return
	 */
	List<CollectInfo> selectMemberCollectShopByPage(PageBeanUtil pageBeanUtil);
	
	int delShop(Integer userid,Integer shopid,Date delTime);
	/**
	 * 删除多个收藏店铺
	 * @param userid 用户id
	 * @param shopids 店铺id集合
	 * @param delTime 删除时间
	 * @return
	 */
	int delCollectShops(@Param("userid")Integer userid,@Param("shopids")List<Integer> shopids,@Param("delTime")Date delTime);
	int delSpu(Integer userid,Integer spuid,Date delTime);
	/**
	 * 删除多个收藏商品
	 * @param userid 用户id
	 * @param spuids 商品id集合
	 * @param delTime 删除时间
	 * @return
	 */
	int delCollectSpus(@Param("userid")Integer userid,@Param("spuids")List<Integer> spuids,@Param("delTime")Date delTime)throws Exception;
	List<Usercollect>  selectShop(Integer userid,Integer shopid);
	
	List<Usercollect>  selectSpu(Integer userid,Integer spuid);
	
	List<Usercollect>  selectPage(PageBeanUtil pageBeanUtil)throws Exception;
	List<Usercollect>  select(Integer userid,Integer type);
	Integer getCountByShopId(Integer shopid) throws Exception ;
	
	List<ConcernSpuDto> getConcernSkuByUser(Integer userid)throws Exception;

	List<PcSpuCollectDto> selectSpuCollectByPage(PageBeanUtil pageBeanUtil) throws Exception;

	List<PcShopCollectDto> selectShopCollectByPage(PageBeanUtil pageBeanUtil) throws Exception;
	
}