package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.FindAppDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FindrecordMapper;
import com.yinlian.wssc.web.po.Findrecord;
import com.yinlian.wssc.web.po.FindrecordExample;
import com.yinlian.wssc.web.service.FindRecordService;
import com.yinlian.wssc.web.util.CriteriaFind;
import com.yinlian.wssc.web.util.PageBeanUtil;
@Component("FindRecordService")
public class FindRecordServiceImpl implements FindRecordService {
	private static final Logger  logger = LoggerFactory.getLogger(FindRecordServiceImpl.class);
	@Autowired
	private FindrecordMapper findrecordMapper;
	@Override
	public List<Findrecord> selectimg() throws Exception {
		// TODO Auto-generated method stub
		return findrecordMapper.selectimg();
	}
	
	
	@Override
	public List<Findrecord> queryAllrecord() throws Exception {
		FindrecordExample example = new FindrecordExample();
		List<Findrecord> list = findrecordMapper.selectByExample(example);
		return list;
	}
	
	@Override
	public List<Findrecord> queryFind() throws Exception {
		
		return findrecordMapper.selectTop();
	}
	
	@Override
	public int add(Findrecord record) throws Exception{
		return findrecordMapper.insert(record);
	}
	
	  @Override
	    public PageBean selectFindrecordListByPage(CriteriaFind criteria, Integer pc, Integer ps)
	                                                                                              throws Exception {

	        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);
	        PageBean pageBean = pageBeanUtil.getPage();
	        List<Findrecord> beanList = findrecordMapper.selectFindRecordPage(pageBeanUtil);
	        pageBean.setBeanList(beanList);
	        return pageBean;
	    }
	  
	  
	  @Override
	    public int deleteById(Integer id) throws Exception {

	        return findrecordMapper.deleteByPrimaryKey(id);
	    }
	  
	  @Override
	  public Findrecord selectFind(Integer id) throws Exception{
		  return findrecordMapper.selectByPrimaryKey(id);
	  }
	  
	  @Override
	  public int updateById(Findrecord record) throws Exception{
		  return findrecordMapper.updateByPrimaryKey(record);
	  }
	  
	  
	  /**
		 * 禁用 或启用
		 * 
		 * @param status
		 * @param id
		 * @return
		 */
		public int updateStatus(Integer status, Integer id) throws Exception {

			return findrecordMapper.updateStatus(status, id);
		}

		/**
		 * 修改排序
		 * 
		 * @param orderby
		 * @param id
		 */
		public int updateOrder(Integer sort, Integer id) throws Exception {
			return findrecordMapper.updateOrder(sort, id);
		}

		/**
		 * 批量修改排序
		 * 
		 * @param orderbys
		 * @param ids
		 * @return
		 * @throws Exception
		 */
		public int updateOrderList(List<Integer> sorts, List<Integer> ids)
				throws Exception {
			Integer sort = 0, id = 0;
			List<Findrecord> list = new ArrayList<Findrecord>();
			for (int i = 0; i < sorts.size(); i++) {
				Findrecord find = new Findrecord();
				sort = sorts.get(i);
				id = ids.get(i);
				find = findrecordMapper.selectByPrimaryKey(id);
				find.setSort(sort);;
				list.add(find);
			}

			return findrecordMapper.updateOrderList(list);
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


		@Override
		public List<FindAppDto> getListByType(Integer type) throws Exception {
			// TODO Auto-generated method stub
			return findrecordMapper.getListByType(type);
		}


		

}
