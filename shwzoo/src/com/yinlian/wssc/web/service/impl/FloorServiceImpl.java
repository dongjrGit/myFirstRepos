package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.dto.FloorDto;
import com.yinlian.wssc.web.dto.FloorProsDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FloorMapper;
import com.yinlian.wssc.web.mapper.FloorproductMapper;
import com.yinlian.wssc.web.po.Floor;
import com.yinlian.wssc.web.service.FloorService;
import com.yinlian.wssc.web.util.CriteriaFloor;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("floorService")
public class FloorServiceImpl implements FloorService {

	/**
	 * 日志
	 */
/*	private static final Logger logger = LoggerFactory
			.getLogger(FloorServiceImpl.class);*/

	@Autowired
	private FloorMapper floorMapper;

	@Autowired
	private FloorproductMapper floorproductMapper;

	/**
	 * 查询所有的楼层
	 */
	@Override
	public List<Floor> queryAll() throws Exception {

		return floorMapper.selectAll();
	}

	/**
	 * 通过ID查询楼层
	 */
	@Override
	public Floor querybyId(Integer id) throws Exception {
		return floorMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改
	 */
	@Override
	public int update(Floor floor) throws Exception {
		return floorMapper.updateByPrimaryKeySelective(floor);
	}

	/**
	 * 插入
	 */
	@Override
	public int insert(Floor floor) throws Exception {

		return floorMapper.insert(floor);
	}

	/**
	 * 分页查询子专题
	 */
	@Override
	public PageBean queryFloorListByCriteria(CriteriaFloor criteria,
			Integer pc, Integer ps) throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, pc, ps);// 还可以
																		// 设置其他的参数
																		// 多条件查询
		PageBean pageBean = pageBeanUtil.getPage();
		List<Floor> beanList = floorMapper.selectFloorByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public int delete(Integer id) throws Exception {

		int i = floorMapper.deleteByPrimaryKey(id);
		int j = floorproductMapper.deleteByFloorId(id);

		return i+j;

	}

	@Override
	public List<FloorDto> selectAllByWebSet(String webset) throws Exception {
		List<FloorProsDto> flpros = floorMapper.selectAllByWebSet(webset);
		List<FloorDto> fldtos = new ArrayList<FloorDto>();
		//Map<Integer, List<FloorProsDto>> floorsMap = flpros.stream().collect(Collectors.groupingBy(FloorProsDto::getFlid));
		//if (floorsMap.size() > 0) {
		if (flpros.size() > 0) {
			//for (Map.Entry<Integer, List<FloorProsDto>> entry : floorsMap.entrySet()) {
			for (FloorProsDto entry : flpros) {
				if (fldtos.size() > 0 && fldtos.stream().anyMatch(x-> x.getFlid().equals(entry.getFlid()))) {
					fldtos.stream().filter(x->x.getFlid().equals(entry.getFlid())).findFirst().get().getFlpros().add(entry);
				}
				else {
					FloorDto fl = new FloorDto();
					fl.setFlname(entry.getFlname());
					fl.setFltype(entry.getFltype());
					fl.setFlid(entry.getFlid());
					fl.setImgurl(entry.getImgurl());
					List<FloorProsDto> pros = new ArrayList<FloorProsDto>();
					pros.add(entry);
					fl.setFlpros(pros);
					fldtos.add(fl);
				}
			}
		}

		return fldtos;
	}

}
