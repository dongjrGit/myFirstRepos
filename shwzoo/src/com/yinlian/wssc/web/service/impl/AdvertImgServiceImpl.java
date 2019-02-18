package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.AdvertImgAppDto;
import com.yinlian.api.app.dto.AdvertListDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.AdvertImgMapper;
import com.yinlian.wssc.web.mapper.AdvertimgdictionaryMapper;
import com.yinlian.wssc.web.po.AdvertImg;
import com.yinlian.wssc.web.po.AdvertImgExample;
import com.yinlian.wssc.web.po.Advertimgdictionary;
import com.yinlian.wssc.web.po.AdvertimgdictionaryExample;
import com.yinlian.wssc.web.service.AdvertImgService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("advertImgService")
public class AdvertImgServiceImpl implements AdvertImgService {

    @Autowired
    private AdvertImgMapper           advertImgMapper;
    @Autowired
    private AdvertimgdictionaryMapper advertimgdictionaryMapper;

    public int deleteByPrimaryKey(Integer id) throws Exception {
        return advertImgMapper.deleteByPrimaryKey(id);
    }

    public int insert(AdvertImg record) throws Exception {
        return advertImgMapper.insert(record);
    }

    public AdvertImg selectByPrimaryKey(Integer id) throws Exception {
        return advertImgMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(AdvertImg record) throws Exception {
        return advertImgMapper.updateByPrimaryKey(record);
    }

    public PageBean getListByPage(Criteria criteria, int page, int size) throws Exception {
        PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
        PageBean pBean = pBeanUtil.getPage();
        List<AdvertImg> list = advertImgMapper.getListByPage(pBeanUtil);
        pBean.setBeanList(list);
        return pBean;
    }

    public int updateOrder(Integer sort, Integer id) throws Exception {
        return advertImgMapper.updateOrder(sort, id);
    }

    public int updateStatus(Integer status, Integer id) throws Exception {
        return advertImgMapper.updateStatus(status, id);
    }

    public int deleteAdvert(Integer id) throws Exception {
        return advertImgMapper.deleteAdvert(id);
    }

    /**
     * @see com.yinlian.wssc.web.service.AdvertImgService#queryList()
     */
    @Override
    public List<Advertimgdictionary> queryList() throws Exception {
        AdvertimgdictionaryExample example = new AdvertimgdictionaryExample();
        AdvertimgdictionaryExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(1);
        criteria.andIsdelEqualTo(false);
        return advertimgdictionaryMapper.selectByExample(example);
    }

    /**
     * @see com.yinlian.wssc.web.service.AdvertImgService#queryDicById(java.lang.Integer)
     */
    @Override
    public Advertimgdictionary queryDicByMark(Integer mark) throws Exception {
        Advertimgdictionary record = new Advertimgdictionary();
        AdvertimgdictionaryExample example = new AdvertimgdictionaryExample();
        AdvertimgdictionaryExample.Criteria criteria = example.createCriteria();
        if(mark==null){
        	mark=0;
        }
        criteria.andMarkEqualTo(mark);
        List<Advertimgdictionary> list = advertimgdictionaryMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            record = list.get(0);
        }
        return record;
    }

	@Override
	public   List<AdvertListDto>  getListByType(Integer type,Integer position) throws Exception {
		 List<AdvertImgAppDto> list=advertImgMapper.getListByType(type,position);		
		 Map<Integer,List<AdvertImgAppDto>> mlist= list.stream().collect(Collectors.groupingBy(x -> x.getPosition()));
		 
		 List<AdvertListDto> rslist=new  ArrayList<AdvertListDto>();
		 if(mlist!=null&&mlist.size()>0){
		 AdvertListDto dto;
		 for (Entry<Integer, List<AdvertImgAppDto>> itemEntry : mlist.entrySet()) {
			 dto=new AdvertListDto();
			 dto.setPosition(itemEntry.getKey());
			 dto.setList(itemEntry.getValue());
			 rslist.add(dto);
		}
		 }
		return rslist;
	}
	
	@Override
	public   List<AdvertListDto>  getListByTypes(Integer type,Integer position) throws Exception {
		 List<AdvertImgAppDto> list=advertImgMapper.getListByTypes(type,position);		
		 Map<Integer,List<AdvertImgAppDto>> mlist= list.stream().collect(Collectors.groupingBy(x -> x.getPosition()));
		 
		 List<AdvertListDto> rslist=new  ArrayList<AdvertListDto>();
		 if(mlist!=null&&mlist.size()>0){
		 AdvertListDto dto;
		 for (Entry<Integer, List<AdvertImgAppDto>> itemEntry : mlist.entrySet()) {
			 dto=new AdvertListDto();
			 dto.setPosition(itemEntry.getKey());
			 dto.setList(itemEntry.getValue());
			 rslist.add(dto);
		}
		 }
		return rslist;
	}

	@Override
	public List<AdvertImg> selectByexample(AdvertImgExample example)
			throws Exception {
		
		return advertImgMapper.selectByExample(example);
	}

	@Override
	public List<AdvertImg> getShopImgList(Integer shopid) throws Exception {
		return advertImgMapper.getListByShopId(shopid);
	}
}
