package com.techown.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.techown.Enums.BannerType;
import com.techown.wssc.web.mapper.RcmdScenicMapper;
import com.techown.wssc.web.mapper.ScenicImageMapper;
import com.techown.wssc.web.mapper.ScenicMapper;
import com.techown.wssc.web.mapper.ScenicTypeMapper;
import com.techown.wssc.web.mapper.ZooBannerMapper;
import com.techown.wssc.web.po.CriteriaScenic;
import com.techown.wssc.web.po.RcmdScenic;
import com.techown.wssc.web.po.Scenic;
import com.techown.wssc.web.po.ScenicApp;
import com.techown.wssc.web.po.ScenicImage;
import com.techown.wssc.web.po.ScenicType;
import com.techown.wssc.web.po.ScenicWithBLOBs;
import com.techown.wssc.web.po.ZooBanner;
import com.techown.wssc.web.service.ScenicService;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.util.PageBeanUtil;

import data.ParseUtil;

@Component("scenicService")
public class ScenicServiceImpl implements ScenicService {

    @Autowired
    private ScenicTypeMapper scenicTypeMapper;

    @Autowired
    private ScenicMapper scenicMapper;

    @Autowired
    private ScenicImageMapper scenicImageMapper;

    @Autowired
    private RcmdScenicMapper rcmdScenicMapper;

    @Autowired
    private ZooBannerMapper zooBannerMapper;

    private static ObjectMapper MAPPER = new ObjectMapper();


    @Override
    public List<ScenicType> queryScenicType(String type) {
        return scenicTypeMapper.selectByType(type);
    }


    @Override
    public PageBean querylist(CriteriaScenic criteria, int index, int size) {
        PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, index, size);
        PageBean pBean = pageBeanUtil.getPage();
        List<Scenic> list = scenicMapper.querylistPage(pageBeanUtil);
        pBean.setBeanList(list);
        return pBean;
    }


    @Override
    public int addScenic(ScenicWithBLOBs scenicWithBLOBs, String imagePaths) {
        String[] paths = imagePaths.split(",");
        List<ScenicImage> list = new ArrayList<ScenicImage>();
        Date date = new Date();
        String imageId = UUID.randomUUID().toString();
        for (int i = 0; i < paths.length; i++) {
            ScenicImage image = new ScenicImage();
            image.setCreatetime(date);
            image.setUpdatetime(date);
            image.setImageid(imageId);
            image.setPath(paths[i]);
            image.setSort(i + 1);
            list.add(image);
        }
        //保存图片
        scenicImageMapper.insertByBatch(list);
        scenicWithBLOBs.setImageid(imageId);
        return scenicMapper.insert(scenicWithBLOBs);
    }


    @Override
    public ScenicWithBLOBs selectById(Integer id) {
        return scenicMapper.selectById(id);
    }


    @Override
    public void updateScenic(ScenicWithBLOBs scenicWithBLOBs, String imagePaths) {
        scenicImageMapper.deleteByImageId(scenicWithBLOBs.getImageid());
        List<ScenicImage> list = new ArrayList<ScenicImage>();
        String[] paths = imagePaths.split(",");
        for (int i = 0; i < paths.length; i++) {
            ScenicImage image = new ScenicImage();
            image.setCreatetime(ParseUtil.parseStringToDate(scenicWithBLOBs.getCreatetimestr()));
            image.setUpdatetime(scenicWithBLOBs.getUpdatetime());
            image.setImageid(scenicWithBLOBs.getImageid());
            image.setPath(paths[i]);
            image.setSort(i + 1);
            list.add(image);
        }
        //保存图片
        scenicImageMapper.insertByBatch(list);
        scenicMapper.updateByPrimaryKeyWithBLOBs(scenicWithBLOBs);
    }


    @Override
    public void dellist(int id, String imageid) {
        scenicMapper.deleteByPrimaryKey(id);
        scenicImageMapper.deleteByImageId(imageid);
    }


    @Override
    public Boolean updateState(int id, int state) {
        if (0 == state) {
            List<RcmdScenic> list = rcmdScenicMapper.selectByScenicId(id);
            //撤销发布
            //判断首页是否有该景点，没有才可以撤销
            if (list.size() > 0) {
                return false;
            }
            List<ZooBanner> banners = zooBannerMapper.selectScenic(BannerType.SCENIC.getCode(), 1, id);
            if (banners.size() > 0) {
                return false;
            }
        }
        scenicMapper.updateState(id, state);
        return true;

    }


    @Override
    public ObjectNode getInitData(String type, Integer state) {
        List<Scenic> scenicList = scenicMapper.getScenicByState(state);
        List<ScenicType> list = scenicTypeMapper.selectByType(type);
        ObjectNode objectNode = MAPPER.createObjectNode();
        objectNode.putPOJO("scenic", scenicList);
        objectNode.putPOJO("type", list);
        return objectNode;
    }


    @Override
    public ScenicApp getAppScenicById(Integer id, Integer state) {
        return scenicMapper.getAppScenicById(id, state);
    }


    @Override
    public List<Scenic> getScenicStartWithName(String type, String name, Integer state) {
        //1：剧场 2：动物景区 3：开心游乐 4:动物互动
        List<String> list = new ArrayList<String>();
        switch (type) {
            case "1":
                list.add("3");
                break;
            case "2":
                list.add("1");
                break;
            case "3":
                list.add("4");
                break;
            case "4":
                list.add("2");
                break;
            default:
                break;
        }
        //1：剧场 2：动物景区 3：开心游乐
//		List<String> list = new ArrayList<String>();
//		if("2".equals(type)){
//			//动物景点
//			list.add("1");
//			//动物互动
//			list.add("2");
//		}else{
//			if("1".equals(type)){
//				list.add("3");
//			}
//			if("3".equals(type)){
//				list.add("4");
//			}
//		}
        return scenicMapper.getScenicStartWithName(list, "%" + name + "%", state);
    }


    @Override
    public boolean checkName(Integer id, String scenicname) {
        List<Map<String, Object>> list = scenicMapper.queryByName(id, scenicname);
        return list.size() != 0;
    }

}
