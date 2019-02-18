package com.yinlian.wssc.web.mapper;

import java.util.List;

import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.po.users_newsExample;
import com.yinlian.wssc.web.util.PageBeanUtil;

public interface users_newsMapper {
    int deleteByExample(users_newsExample example);

    int deleteByPrimaryKey(Integer id);
    int deleteBynewsid(Integer newid);
    int insert(users_news record);

    int insertSelective(users_news record);

    List<users_news> selectByExample(users_newsExample example);

    users_news selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(users_news record);

    int updateByPrimaryKey(users_news record); 
    int deleteByuseridAndNewsid(Integer userid,Integer newsid);
    List<users_news> usersNewslistPage(PageBeanUtil pageBeanUtil);
    List<users_news> selectscCount();
    List<users_news> getnewsByUserid(Integer userid);
    
    List<users_news> newuserslistPage(PageBeanUtil pageBeanUtil);
}