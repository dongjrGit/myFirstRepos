package com.yinlian.wssc.web.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yinlian.pc.dto.GuessLikeDto;
import com.yinlian.wssc.web.po.Shopcartpros;
import com.yinlian.wssc.web.po.ShopcartprosExample;

public interface ShopcartprosMapper {
    int countByExample(ShopcartprosExample example);

    int deleteByExample(ShopcartprosExample example);

    int deleteByPrimaryKey(Integer id) throws Exception;

    int insert(Shopcartpros record) throws Exception;

    int insertSelective(Shopcartpros record);

    List<Shopcartpros> selectByExample(ShopcartprosExample example);

    Shopcartpros selectByPrimaryKey(Integer id) throws Exception;

    int updateByExampleSelective(@Param("record") Shopcartpros record,
                                 @Param("example") ShopcartprosExample example);

    int updateByExample(@Param("record") Shopcartpros record,
                        @Param("example") ShopcartprosExample example);

    int updateByPrimaryKeySelective(Shopcartpros record);

    int updateByPrimaryKey(Shopcartpros record) throws Exception;

    Integer countByuser(Integer userid) throws Exception;

    int updateCount(Integer count, Integer id) throws Exception;

    int deleteByUser(Integer userid) throws Exception;

    List<Shopcartpros> getByuserID(Integer userid) throws Exception;

    List<Shopcartpros> getBycartID(Integer cartid) throws Exception;

    Shopcartpros getByuserIDAndProID(Integer userid, Integer proid,
                                     @Param("protype") Integer protype,Date usetime) throws Exception;

    int deleteByIds(List<Integer> list) throws Exception;
    
    int updateSelect(Shopcartpros record)throws Exception;
    
    int updateSelectList(List<Shopcartpros> list)throws Exception;

	void updateSelectShop(Integer shopid,Integer userid, Integer sel);

	/**
	 * 猜你喜欢
	 * @param shopcartid 购物车ID
	 * @param size 数量
	 * @return
	 */
	List<GuessLikeDto> selectByShopCartID(Integer shopcartid, Integer size);
	
	int deleteByUserTime(@Param("shopcartpros")Shopcartpros shopcartpros) throws Exception;
} 