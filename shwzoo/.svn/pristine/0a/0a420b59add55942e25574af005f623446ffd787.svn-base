package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.AdvertisingType;
import com.yinlian.api.app.dto.AdvertImgAppDto;
import com.yinlian.api.app.dto.AdvertListDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AdvertisingMapper;
import com.yinlian.wssc.web.po.Advertising;
import com.yinlian.wssc.web.po.Topic;
import com.yinlian.wssc.web.service.AdverisingService;
import com.yinlian.wssc.web.service.TopicService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("AdverisingService")
public class AdverisingServiceImpl implements AdverisingService {

	@Autowired
	private AdvertisingMapper advertisingMapper;
	@Autowired
	private TopicService topicService;

	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {

		return advertisingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Advertising record) throws Exception {

		return advertisingMapper.insertSelective(record);
	}

	@Override
	public Advertising selectByPrimaryKey(Integer id) throws Exception {

		return advertisingMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Advertising record) throws Exception {

		return advertisingMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public PageBean getListByPage(Criteria criteria, int page, int size)
			throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<Advertising> list = advertisingMapper.getListByPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public int deleteAdvertising(Integer id) throws Exception {

		return advertisingMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateStatus(Integer status, Integer id) throws Exception {

		return advertisingMapper.updateStatus(status, id);
	}

	@Override
	public List<Advertising> getListByType(int pagemark, int webset)throws Exception {
		return advertisingMapper.getListByType(pagemark, webset);
	}

	@Override
	public List<Advertising> getListByTypeAndDisplay(int pagemark, int display,
			int webset) throws Exception {

		return advertisingMapper.getListByTypeAndDisplay(pagemark, display, webset,null);
	}

	public List<AdvertImgAppDto> getListByTypeAndDisplayApi(int pagemark,
			int display, int webset) throws Exception {
		List<AdvertImgAppDto> list = new ArrayList<AdvertImgAppDto>();
		List<Advertising> Advertlist = advertisingMapper
				.getListByTypeAndDisplay(pagemark, display, webset,null);
		if (Advertlist != null && Advertlist.size() > 0) {
			AdvertImgAppDto dto = null;
			for (Advertising ad : Advertlist) {
				dto = new AdvertImgAppDto();
				dto.setId(ad.getId());
				dto.setImgurl(ad.getImag());
				dto.setPosition(ad.getDisplay());
				dto.setSort(ad.getOrderby());
				dto.setPagemark(pagemark);
				dto.setUrl(ad.getUrl());
				dto.setType(ad.getType());
				dto.setTypeid(ad.getTypeid());
				if(dto.getType()==AdvertisingType.专题.getValue()){
					Topic topic=topicService.queryById(dto.getTypeid());
					if(topic!=null){
						dto.setTypename(topic.getTitle());
					}
				}
				list.add(dto);
			}
		}
		return list;

	}

	public List<AdvertListDto> getListByTypeApi(int pagemark,int webset) throws Exception {
		List<AdvertImgAppDto> list = new ArrayList<AdvertImgAppDto>();
		List<Advertising> Advertlist = advertisingMapper.getListByType(pagemark,
				webset);
		Map<Integer, List<Advertising>> grouplist = Advertlist.stream()
				.collect(Collectors.groupingBy(x -> x.getDisplay()));

		List<AdvertListDto> rslist = new ArrayList<AdvertListDto>();
		if (grouplist != null && grouplist.size() > 0) {
			AdvertListDto dto;
			for (Entry<Integer, List<Advertising>> itemEntry : grouplist
					.entrySet()) {
				dto = new AdvertListDto();
				dto.setPosition(itemEntry.getKey());
				if (itemEntry.getValue() != null
						&& itemEntry.getValue().size() > 0) {
					AdvertImgAppDto imgdto = null;
					for (Advertising ad : itemEntry.getValue()) {
						imgdto = new AdvertImgAppDto();
						imgdto.setId(ad.getId());
						imgdto.setImgurl(ad.getImag());
						imgdto.setPosition(ad.getDisplay());
						imgdto.setSort(ad.getOrderby());
						imgdto.setPagemark(pagemark);
						imgdto.setUrl(ad.getUrl());
						imgdto.setType(ad.getType());
						imgdto.setTypeid(ad.getTypeid());
						if(imgdto.getType()==AdvertisingType.专题.getValue()){
							Topic topic=topicService.queryById(imgdto.getTypeid());
							if(topic!=null){
								imgdto.setTypename(topic.getTitle());
							}
						}
						list.add(imgdto);
					}
				}
				dto.setList(list);
				rslist.add(dto);
				list=new ArrayList<AdvertImgAppDto>();
			}
		}
		return rslist;
	}

	@Override
	public List<Advertising> getListByTypeAndDisplay(int pagemark, int display, int webset, Integer shopid) throws Exception {
		return advertisingMapper.getListByTypeAndDisplay(pagemark, display, webset,shopid);
	}

	@Override
	public Advertising getByAdvertact(int type,Integer status) throws Exception {
		return advertisingMapper.getByAdvertact(type,status);
	}

}
