package com.yinlian.pc.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yinlian.Enums.ActivityUsePlatformEnum;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.SpikeSpuDto;
import com.yinlian.pc.dto.SpikeAvticitiDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SpikeactivityMapperCustom;
import com.yinlian.wssc.web.po.Spikeactivity;
import com.yinlian.wssc.web.po.SpikeactivityExample;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.DateUtil;
import com.yinlian.wssc.web.util.DebugConfig;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.StringUtilsEX;
import com.yl.soft.log.LogHandle;

@RestController
@RequestMapping("/pc/activity")
public class PCActivityController {
	
	@Autowired
	private    SpikeActivityService   spikeActivityService;
	@Autowired
	private    SpikeactivityMapperCustom spikeactivityMapperCustom;
	
	/**
	 * 获取闪购列表
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/getSgList", produces = "text/html;charset=UTF-8")
	public String getSgList(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/" + ch + "/getSgList";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			List<SpikeAvticitiDto> listdto=new ArrayList<SpikeAvticitiDto>();
			List<Spikeactivity> list = spikeActivityService.getByEndDate(new Date(), 1, 0);
			for (Spikeactivity spikeactivity : list) {
				SpikeAvticitiDto dto=new SpikeAvticitiDto();
				CriteriaActivity criteria = new CriteriaActivity();
				criteria.setSpikeID(spikeactivity.getId());
				PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
				List<SpikeSpuDto> prolist = spikeactivityMapperCustom.getSpuListByActivityIDPage(pBeanUtil);
				PageBean pageBean=pBeanUtil.getPage();
				dto.setList(prolist);
				dto.setName(spikeactivity.getSpikename());
				dto.setTime(spikeactivity.getEndtime());
				listdto.add(dto);
				item.setMaxRow(pageBean.getTr());
				item.setPageIndex(pageBean.getPc());
			}
			
			
			item.setData(listdto);
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取闪购活动商品列表异常：" + e.toString());
			}else {
				item.setDesc("获取闪购活动商品列表异常");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e), logpath);
		}

		return item.toJson();
	}
	
	
	/**
	 * 获取闪购列表
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/gettglist", produces = "text/html;charset=UTF-8")
	public String gettglist(String ch, String page, String size) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/" + ch + "/getSgList";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(size) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + size);
				return item.toJson();
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("b.orderby");
			criteria.setUsesite(ActivityUsePlatformEnum.pc.getValue());
			PageBean pBean = spikeActivityService.gettgSpuByPage(criteria,
					StringUtilsEX.ToInt(page), StringUtilsEX.ToInt(size));
			item.setData(pBean.getBeanList());
			item.setMaxRow(pBean.getTr());
			item.setPageIndex(pBean.getPc());
			item.setCode(0);
		} catch (Exception e) {
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取闪购活动商品列表异常：" + e.toString());
			}else {
				item.setDesc("获取闪购活动商品列表异常");
			}			
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取闪购活动商品列表异常! 异常信息:{0}", e), logpath);
		}

		return item.toJson();
	}
	
	
	
	
	/**
	 * 获取秒杀活动
	 * 
	 * @return
	 */
	@RequestMapping(value = "/spike", produces = "text/html;charset=UTF-8")
	public @ResponseBody  String getSpike(String ch, String page, String rows) {
		ReusltItem item = new ReusltItem();
		String logpath = "activity/" + ch + "/getSpike";
		try {
			if (!StringUtilsEX.isChannelTypeExist(ch)) {
				item.setCode(-101);
				item.setDesc("登录通道(ch)不正确！");
				return item.toJson();
			}
			if (StringUtilsEX.ToInt(page) <= 0
					|| StringUtilsEX.ToInt(rows) <= 0) {
				item.setCode(-102);
				item.setDesc("分页参数错误，pageindex:" + page + ",pagesize:" + rows);
				return item.toJson();
			}
			CriteriaActivity criteria = new CriteriaActivity();
			criteria.setSort("desc");
			criteria.setOrderByClause("a.orderby");
			criteria.setUsesite(ActivityUsePlatformEnum.pc.getValue());
			int pages=StringUtilsEX.ToInt(page);
			int sizes=StringUtilsEX.ToInt(rows);
			item.setData(spikeActivityService.getmsActivityList(criteria,
					pages,sizes, item));
			item.setCode(0);
			return item.toJson();
		} catch (Exception e) {
			e.printStackTrace();
			item.setCode(-900);
			if(DebugConfig.BLUETOOTH_DEBUG){
				item.setDesc("获取秒杀活动异常：" + e.toString());
			}else {
				item.setDesc("获取秒杀活动异常");
			}
			
			LogHandle.error(LogType.pc,
					MessageFormat.format("获取秒杀活动异常! 异常信息:{0}", e), logpath);
		}
		return item.toJson();
	}
	
}
