package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.app.dto.NewsBaseDto;
import com.yinlian.wssc.search.AppNewsCriteria;
import com.yinlian.wssc.search.userNewsCriteria;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SnewsClassMapper;
import com.yinlian.wssc.web.mapper.users_newsMapper;
import com.yinlian.wssc.web.po.users_news;
import com.yinlian.wssc.web.po.users_newsExample;
import com.yinlian.wssc.web.service.users_newsService;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("users_newsService")
public class users_newsServiceImpl implements users_newsService{

	@Autowired
	private users_newsMapper usernewsMapper;
	
	@Override
	public int deleteByExample(users_newsExample example) {
		// TODO Auto-generated method stub
		return usernewsMapper.deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return usernewsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(users_news record) {
		// TODO Auto-generated method stub
		return usernewsMapper.insert(record);
	}

	@Override
	public int insertSelective(users_news record) {
		// TODO Auto-generated method stub
		return usernewsMapper.insert(record);
	}

	@Override
	public List<users_news> selectByExample(users_newsExample example) {
		// TODO Auto-generated method stub
		return usernewsMapper.selectByExample(example);
	}

	@Override
	public users_news selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return usernewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(users_news record) {
		// TODO Auto-generated method stub
		return usernewsMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(users_news record) {
		// TODO Auto-generated method stub
		return usernewsMapper.updateByPrimaryKey(record);
	}

	@Override
	public PageBean usersNewslistPage(userNewsCriteria criteria,
			Integer page, Integer size) {
		
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<users_news> list = usernewsMapper.usersNewslistPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public List<users_news> selectscCount() {
		return usernewsMapper.selectscCount();
	}

	@Override
	public int deleteByuseridAndNewsid(Integer userid, Integer newsid) {
		// TODO Auto-generated method stub
		return usernewsMapper.deleteByuseridAndNewsid(userid, newsid);
	}

	@Override
	public List<users_news> getnewsByUserid(Integer userid) {
		// TODO Auto-generated method stub
		return usernewsMapper.getnewsByUserid(userid);
	}

	@Override
	public PageBean usersNewsPagelist(userNewsCriteria criteria,
			Integer page, Integer size) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<users_news> list = usernewsMapper.newuserslistPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public int deleteBynewsid(Integer newid) {
		// TODO Auto-generated method stub
		return usernewsMapper.deleteBynewsid(newid);
	}

 

}
