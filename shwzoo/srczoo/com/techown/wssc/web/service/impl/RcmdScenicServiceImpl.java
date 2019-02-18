package com.techown.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.techown.wssc.web.mapper.RcmdScenicMapper;
import com.techown.wssc.web.po.RcmdScenic;
import com.techown.wssc.web.service.RcmdScenicService;

@Component("rcmdScenicService")
public class RcmdScenicServiceImpl implements RcmdScenicService {

    @Autowired
    private RcmdScenicMapper rcmdScenicMapper;

    @Override
    public List<RcmdScenic> querylist(String name, String type, Integer state) {
        List<String> list = new ArrayList<String>();
        //1：剧场 2：动物景区 3：开心游乐 4:动物互动
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
        return rcmdScenicMapper.querylist(name, list, state);
    }

    @Override
    public void addRcmdScenic(RcmdScenic rcmdScenic) {
        rcmdScenicMapper.insert(rcmdScenic);
    }

    @Override
    public RcmdScenic selectById(Integer id) {
        return rcmdScenicMapper.selectById(id);
    }

    @Override
    public void updateRcmdScenic(RcmdScenic rcmdScenic) {
        rcmdScenicMapper.updateRcmdScenic(rcmdScenic);
    }

    @Override
    public List<RcmdScenic> getAppRcmdScenic(Integer type) {
        return rcmdScenicMapper.getAppRcmdScenic(type);
    }

}
