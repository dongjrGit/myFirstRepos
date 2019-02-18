package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.PageMarkType;
import com.yinlian.Enums.TopicMarkEnum;
import com.yinlian.Enums.TopicTypeEnum;
import com.yinlian.api.app.dto.Api_FirstPageChildTopicDto;
import com.yinlian.api.app.dto.Api_FirstPageTopicDto;
import com.yinlian.api.app.dto.Api_TopicBrandDto;
import com.yinlian.api.app.dto.Api_TopicByShopDto;
import com.yinlian.api.app.dto.Api_TopicBySpuDto;
import com.yinlian.api.app.dto.TopicDto;
import com.yinlian.pc.dto.PCTopicDto;
import com.yinlian.wssc.search.Api_TopicBySpuCriteria;
import com.yinlian.wssc.search.Pc_TopicCriteria;
import com.yinlian.wssc.web.dto.FindRelationDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.TopicMapper;
import com.yinlian.wssc.web.mapper.TopicRelateMapper;
import com.yinlian.wssc.web.po.SkuShowtime;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.po.TopicExample;
import com.yinlian.wssc.web.po.TopicExample.Criteria;
import com.yinlian.wssc.web.po.TopicRelateExample;
import com.yinlian.wssc.web.service.SpuService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.CriteriaFindRelate;
import com.yinlian.wssc.web.util.CriteriaTopic;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.TopicCriteria;
import com.yl.soft.uitl.DateUtil;

/**
 * 首页主题service
 * 
 * @author admin
 *
 */
@Component("topicService")
public class TopicServiceImpl implements TopicService {

	private static final Logger logger = LoggerFactory
			.getLogger(AccountsServiceImpl.class);

	@Autowired
	private TopicMapper topicMapper;
	@Autowired
	private TopicRelateMapper topicRelateMapper;
	@Autowired
	private SpuService   spuService;
	

	@Override
	public Topic selectByMark(Integer mark) throws Exception {

		return topicMapper.queryByMark(mark);
	}

	/**
	 * @see com.yinlian.wssc.web.service.TopicService#queryById(java.lang.Integer)
	 */
	@Override
	public Topic queryById(Integer id) throws Exception {
		return topicMapper.selectByPrimaryKey(id);
	}

	/**
	 * 分页查询子专题
	 * 
	 * @see com.yinlian.wssc.web.service.TopicService#queryTopicListByCriteria(com.yinlian.wssc.web.util.CriteriaTopic,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryTopicListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Topic> beanList = topicMapper.selectTopicByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int insert(Topic topic) throws Exception {
		topic.setAddtime(new Date());
		return topicMapper.insert(topic);
	}

	@Override
	public int delete(Integer id) throws Exception {

		return topicMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据主键删除专题
	 * 
	 * @see com.yinlian.wssc.web.service.TopicService#delTopicById(java.lang.Integer)
	 */
	@Override
	public int delTopicById(Topic topic) throws Exception {
		TopicRelateExample example = new TopicRelateExample();
		TopicRelateExample.Criteria criteria = example.createCriteria();
		criteria.andTopicidEqualTo(topic.getId());
		topicRelateMapper.deleteByExample(example);
		return topicMapper.updateByPrimaryKey(topic);
	}

	/**
	 * 更改专题信息
	 */
	@Override
	public int update(Topic topic) throws Exception {

		return topicMapper.updateByPrimaryKey(topic);
	}

	/**
	 * 根据fatherid查询子专题
	 * 
	 * @see com.yinlian.wssc.web.service.TopicService#selectByTopicFatherId(int)
	 */
	@Override
	public List<Topic> selectByTopicFatherId(int fatherid) throws Exception {
		TopicExample example = new TopicExample();
		TopicExample.Criteria criteria = example.createCriteria();
		criteria.andFatheridEqualTo(fatherid);
		return topicMapper.selectByExample(example);
	}

	@Override
	public List<Topic> selectSubTopic(Integer mark) throws Exception {

		return topicMapper.querySubTopic(mark);
	}

	@Override
	public Topic query(Integer type, Integer mark) throws Exception {

		return topicMapper.select(type, mark);
	}

	/**
	 * 分页查询子专题
	 * 
	 * @see com.yinlian.wssc.web.service.TopicService#queryCtopicListByCriteria(com.yinlian.wssc.web.util.CriteriaTopic,
	 *      java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean queryCtopicListByCriteria(CriteriaTopic criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Topic> beanList = topicMapper.selectCtopicByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Topic> queryAll() throws Exception {

		return topicMapper.selectAll();
	}

	@Override
	public List<Api_FirstPageTopicDto> getTopic(Integer imark) throws Exception {
		List<TopicDto> list = topicMapper.getTopicChild(imark);
		if (list.size() == 0)
			return new ArrayList<Api_FirstPageTopicDto>();
		Map<Integer, List<TopicDto>> fMap = sortMapByKey(list.stream().collect(
				Collectors.groupingBy(x -> ((TopicDto) x).getFloorappid())));
		List<Api_FirstPageTopicDto> rList = new ArrayList<Api_FirstPageTopicDto>();
		Api_FirstPageTopicDto dto;

		for (Entry<Integer, List<TopicDto>> entry : fMap.entrySet()) {
			dto = new Api_FirstPageTopicDto();
			dto.setFloorappid(entry.getKey());
			entry.getValue().sort(
					(x, y) -> (x.getSort().compareTo(y.getSort())));
			dto.setChilds(entry
					.getValue()
					.stream()
					.map(x -> {
						Api_FirstPageChildTopicDto cDto = new Api_FirstPageChildTopicDto();
						cDto.setImgurl(x.getImgurl());
						cDto.setTopicid(x.getId());
						cDto.setSort(x.getSort());
						cDto.setUrl(x.getUrl());
						cDto.setType(x.getType());
						cDto.setRelatedid(topicRelateMapper.getRelatedid(x
								.getId()));
						cDto.setName(x.getName());
						cDto.setFloorType(x.getFloorType());
						cDto.setTitle(x.getTitle());
						return cDto;
					}).collect(Collectors.toList()));
			rList.add(dto);
		}
		return rList;
	}

	/**
	 * 使用 Map按key进行排序
	 * 
	 * @param map
	 * @return
	 */
	<T> Map<Integer, T> sortMapByKey(Map<Integer, T> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<Integer, T> sortMap = new TreeMap<Integer, T>(
				new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}

	@Override
	public PageBean getTopicByShop(Integer page, Integer size) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Api_TopicByShopDto> beanlist = topicMapper
				.getTopicByShopPage(pageBeanUtil);
		pageBean.setBeanList(beanlist);
		return pageBean;
	}

	@Override
	public PageBean getTopicBySpu(Api_TopicBySpuCriteria atc, int page, int size) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(atc, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<Api_TopicBySpuDto> beanList = topicMapper
				.getTopicBySpuPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Topic> queryByMark(Integer mark) throws Exception {
		return topicMapper.selectByMark(mark);
	}

	@Override
	public List<Topic> queryByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return topicMapper.selectByName(name);
	}
	/**
	 * 获取PC页面专题（分页）
	 * 
	 * @param criteria
	 *            条件
	 * @param pc
	 *            起始页
	 * @param ps
	 *            每页行数
	 * */
	@Override
	public PageBean getPcTopByPage(Pc_TopicCriteria criteria, Integer pc,
			Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 多条件查询

		PageBean pageBean = pageBeanUtil.getPage();
		List<Api_TopicBySpuDto> beanList = topicMapper
				.getPCTopicPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<PCTopicDto> getTopicSpuByPagetag(Integer pagetag, String webset)
			throws Exception {

		return topicMapper.getTopicSpuByPagetag(pagetag, webset);
	}

	@Override
	public PageBean queryFindRelationByCriteria(CriteriaFindRelate criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<FindRelationDto> beanList = topicMapper
				.seleteFindRelationByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Topic> getSpuStartWithNames(String name) throws Exception {
		return topicMapper.getSpuStartWithNames(name);
	}

	@Override
	public List<Topic> findTopicCriteria(int pagemark, int topicmark,
			int topictype, String webset) {
		TopicExample topicExample = new TopicExample();
		Criteria criteria = topicExample.createCriteria();
		criteria.andPageTagEqualTo(pagemark);
		criteria.andMarkEqualTo(topicmark);
		criteria.andTypeEqualTo(topictype);
		criteria.andWebSetLike("%" + webset + "%");
		return topicMapper.selectByExample(topicExample);
	}

	@Override
	public List<Topic> findTopicCriteria(TopicCriteria criteria)
			throws Exception {
		return topicMapper.selectTopicCriteria(criteria);
	}

	@Override
	public List<Api_TopicBySpuDto> getIndexTopic(Integer pagetag, Integer mark,
			Integer type, String webset, Integer size) throws Exception {
		return topicMapper.getTopicSpuList(pagetag, mark, type, webset, size);
	}

	@Override
	public List<Api_TopicBrandDto> getTopicBrand(Integer pagetag, Integer mark,
			Integer type, String webset, Integer size, boolean issys)
			throws Exception {
		return topicMapper.getTopicBrandList(pagetag, mark, type, webset, size,
				issys);
	}

	@Override
	public List<Topic> getTopicStartWithName(String name, Integer webset)
			throws Exception {
		return topicMapper.gettopicStartWithName(name, webset);
	}

	@Override
	public List<Api_TopicBySpuDto> getApiTopicSpu(int pagetag, Integer mark,
			String website, Integer size,Date date) throws Exception {
		List<Api_TopicBySpuDto> list=topicMapper.getTopicSpuList(pagetag, mark,
				TopicTypeEnum.商品.getValue(), website, size);
		Integer year=DateUtil.getYear(date);
		Integer month=DateUtil.getMonth(date);
		Integer day=DateUtil.getDay(date);
		for (Api_TopicBySpuDto dto : list) {
			SkuShowtime  skutime=spuService.getSkuTime(dto.getSpuid(), year, month, day);
			if(skutime!=null){
				dto.setPrice(skutime.getPrice().doubleValue());
			}
		}
		
		return list;
	}

	@Override
	public PageBean getApiTopicSpuPage(CriteriaTopic criteria, int page1,
			int size1)throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page1, size1);// 还可以
		// 设置其他的参数
		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Api_TopicBySpuDto> beanList = topicMapper.getTopicSpuListPage(pageBeanUtil);
		Integer year=DateUtil.getYear(criteria.getNewdate());
		Integer month=DateUtil.getMonth(criteria.getNewdate());
		Integer day=DateUtil.getDay(criteria.getNewdate());
		for (Api_TopicBySpuDto dto : beanList) {
			SkuShowtime  skutime=spuService.getSkuTime(dto.getSpuid(), year, month, day);
			if(skutime!=null){
				dto.setPrice(skutime.getPrice().doubleValue());
			}
		}
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<Topic> getTopicStartWithNameByMark(String name)
			throws Exception {
		return topicMapper.getTopicStartWithNameByMark(name, TopicMarkEnum.猜你喜欢.getValue(),PageMarkType.资讯页.getValue());
	}

}

// 比较器类
class MapKeyComparator implements Comparator<Integer> {
	public int compare(Integer str1, Integer str2) {
		return str1.compareTo(str2);
	}

}
