package com.yinlian.wssc.web.service;

import java.util.List;

import com.yinlian.wssc.search.userNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.po.users_newsExample;
import com.yinlian.wssc.web.util.PageBeanUtil;
/**
 * 菜谱收藏
 * @author NiYongKang
 */
public interface users_newsService {
	  int deleteByExample(users_newsExample example);

	    int deleteByPrimaryKey(Integer id);

	    int insert(users_news record);

	    int insertSelective(users_news record);

	    List<users_news> selectByExample(users_newsExample example);
	    /**
	     * 根据ID查询
	     * @param id
	     * @return
	     */
	    users_news selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(users_news record);
	    
	    int deleteByuseridAndNewsid(Integer userid,Integer newsid);
	    
	    List<users_news> selectscCount();
	    
	    int deleteBynewsid(Integer newid);

	    int updateByPrimaryKey(users_news record);
	    /**
	     * 分页查询
	     * @param criteria
	     * @param page
	     * @param size
	     * @return
	     */
	    PageBean usersNewslistPage(userNewsCriteria criteria, Integer page, Integer size);
	    
	    List<users_news> getnewsByUserid(Integer userid);
	    PageBean usersNewsPagelist(userNewsCriteria criteria,
				Integer page, Integer size);
}
