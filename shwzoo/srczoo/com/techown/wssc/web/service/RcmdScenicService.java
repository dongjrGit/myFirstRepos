package com.techown.wssc.web.service;

import java.util.List;

import com.techown.wssc.web.po.RcmdScenic;

public interface RcmdScenicService {

	List<RcmdScenic> querylist(String name, String type, Integer state);

	void addRcmdScenic(RcmdScenic rcmdScenic);

	RcmdScenic selectById(Integer id);

	void updateRcmdScenic(RcmdScenic rcmdScenic);

	/**
	 * 前端推荐景点接口
	 * 
	 * @param type
	 *            类型 1：剧场 ；2：动物 ；3：开心游乐
	 * @return
	 */
	List<RcmdScenic> getAppRcmdScenic(Integer type);

}
