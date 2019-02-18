package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AnnouncementMapper;
import com.yinlian.wssc.web.po.Announcement;
import com.yinlian.wssc.web.po.AnnouncementExample;
import com.yinlian.wssc.web.service.AnnouncementService;
import com.yinlian.wssc.web.util.Criteriaannouncement;
import com.yinlian.wssc.web.util.PageBeanUtil;
@Component("announcementService")
public class AnnouncementServiceImpl implements AnnouncementService {
	private static final Logger  logger = LoggerFactory.getLogger(AnnouncementServiceImpl.class);
	@Autowired
	private AnnouncementMapper announcementMapper;
	@Override
	public int add(Announcement announcement) throws Exception {
		// TODO Auto-generated method stub
		return announcementMapper.insert(announcement);
	}
	
	@Override
	public List<Announcement> queryAllrecord() throws Exception {
		AnnouncementExample example = new AnnouncementExample();
		List<Announcement> list = announcementMapper.selectByExample(example);
		return list;
	}
	
	 @Override
	    public PageBean selectAnnouncementByPage(Criteriaannouncement criteria, Integer pc, Integer ps) throws Exception{
	        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
	        PageBean pageBean = pageBeanUtil.getPage();
	        List<Announcement> beanList = announcementMapper.selectannouncementPage(pageBeanUtil);
	        pageBean.setBeanList(beanList);
	        return pageBean;
	    }
	 
	 @Override
	 public int delete(Integer id) throws Exception{
		 return announcementMapper.deleteByPrimaryKey(id);
	 }
	 
	 @Override
	 public Announcement selectByid(Integer id) throws Exception{
		 return announcementMapper.selectByPrimaryKey(id);
	 }
	 
	 @Override
	 public int updateAnnouncement(Announcement announcement) throws Exception{
		 return announcementMapper.updateByPrimaryKeySelective(announcement);
	 }
	 
	 /**
		 * 修改排序
		 * 
		 * @param sort
		 * @param id
		 */
		public int updateOrder(Integer sort, Integer id) throws Exception {
			return announcementMapper.updateOrder(sort, id);
		}

		/**
		 * 批量修改排序
		 * 
		 * @param sorts
		 * @param ids
		 * @return
		 * @throws Exception
		 */
		public int updateOrderList(List<Integer> sorts, List<Integer> ids)
				throws Exception {
			Integer sort = 0, id = 0;
			List<Announcement> list = new ArrayList<Announcement>();
			for (int i = 0; i < sorts.size(); i++) {
				Announcement announ = new Announcement();
				sort = sorts.get(i);
				id = ids.get(i);
				announ = announcementMapper.selectByPrimaryKey(id);
				announ.setSort(sort);;
				list.add(announ);
			}

			return announcementMapper.updateOrderList(list);
			// 百度搜索 mybatis中最快的更新 有待研究
			// SqlSession sqlSession =
			// sqlSessionFactory.getObject().openSession(ExecutorType.BATCH);
			// try {
			// sqlSession.insert("com.emg.trans.mapper.batchMapper.batchInsert",
			// list);
			// sqlSession.commit();
			// } finally {
			// sqlSession.close();
			// }
		}

}
