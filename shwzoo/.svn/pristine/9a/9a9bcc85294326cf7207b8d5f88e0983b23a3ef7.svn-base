package com.techown.wssc.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techown.wssc.web.mapper.ZooEditorMapper;
import com.techown.wssc.web.po.CriteriaEditor;
import com.techown.wssc.web.po.ZooBanner;
import com.techown.wssc.web.po.ZooEditor;
import com.techown.wssc.web.service.ZooBannerService;
import com.techown.wssc.web.service.ZooEditorService;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("zooEditorService")
public class ZooEditorServiceImpl implements ZooEditorService {
	@Autowired
	private ZooEditorMapper zooEditorMapper;

	@Autowired
	private ZooBannerService zooBannerService;

	@Override
	public PageBean listByType(CriteriaEditor criteria, int index, int size) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<ZooEditor> list = zooEditorMapper.querylistPage(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public ZooEditor getById(Integer id) {
		return zooEditorMapper.selectByPrimaryKey(id,null);
	}
	
	@Override
	public ZooEditor getById(Integer id, Integer delstate) {
		return zooEditorMapper.selectByPrimaryKey(id,delstate);
	}

	@Override
	public void saveEditor(ZooEditor zooEditor) {
		zooEditorMapper.insert(zooEditor);
	}

	@Override
	public Map<String, String> deleteById(Integer id, Integer type, String operator) {
		Map<String, String> map = new HashMap<>();
		if(1==type){
			List<ZooBanner> list = zooBannerService.listByTypeId(id);
			if (list.size() > 0) {
				String desc = list.stream().map(ZooBanner::getTitle).collect(Collectors.joining("、"));
				map.put("desc", "该行被首页banner图引用，对应的标题是：" + desc);
				map.put("state", "fail");
				return map;
			} 
		}
		zooEditorMapper.deleteById(id, operator);
		map.put("state", "success");
		map.put("desc", "删除成功");
	    return map;
	}

	@Override
	public List<ZooEditor> listByTitle(Integer type, String title) {
		return zooEditorMapper.listByTitle(type, "%" + title + "%");
	}

	@Override
	public int countCheckTitle(Integer id, Integer type, String title) {
		return zooEditorMapper.countCheckTitle(id, type, title);
	}

	@Override
	public void updateById(ZooEditor zooEditor) {
		zooEditorMapper.updateByPrimaryKeySelective(zooEditor);
	}

	

}
