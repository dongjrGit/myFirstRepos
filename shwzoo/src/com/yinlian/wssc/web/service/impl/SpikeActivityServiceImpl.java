package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.ExcitingActDetailDto;
import com.yinlian.api.app.dto.ExcitingActDto;
import com.yinlian.api.app.dto.PromotionDto;
import com.yinlian.api.app.dto.SpikeDto;
import com.yinlian.api.app.dto.SpikeSpuDto;
import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.ExcitingUserDto;
import com.yinlian.wssc.web.dto.SpSpuDto;
import com.yinlian.wssc.web.dto.V_SpikeActivity;
import com.yinlian.wssc.web.dto.sgSpuDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.SpikeSpuMapper;
import com.yinlian.wssc.web.mapper.SpikeactivityMapper;
import com.yinlian.wssc.web.mapper.SpikeactivityMapperCustom;
import com.yinlian.wssc.web.mapper.SpikeshopMapper;
import com.yinlian.wssc.web.mapper.UserSpikeMapper;
import com.yinlian.wssc.web.po.SpikeSpu;
import com.yinlian.wssc.web.po.Spikeactivity;
import com.yinlian.wssc.web.po.Spikeshop;
import com.yinlian.wssc.web.po.Spu;
import com.yinlian.wssc.web.po.UserSpike;
import com.yinlian.wssc.web.service.SpikeActivityService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaActivity;
import com.yinlian.wssc.web.util.MD5Util;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("spikeActivityService")
public class SpikeActivityServiceImpl implements SpikeActivityService {

	@Autowired
	private SpikeSpuMapper spikeSpuMapper;

	@Autowired
	private SpikeactivityMapper spikeactivityMapper;

	@Autowired
	private SpikeshopMapper spikeshopMapper;
	@Autowired
	private SpikeactivityMapperCustom spikeactivityMapperCustom;

	@Autowired
	private UserSpikeMapper userSpikeMapper;

	@Override
	public SpSpuDto getSpSpuDtoBySpikeID(int spikeid, int spuid) throws Exception {
		return spikeSpuMapper.getSpSpuDtoBySpikeID(spikeid, spuid);
	}

	@Override
	public int updateSpikeSpuCountByIds(List<UpDateActDto> spiIds) throws Exception {

		return spikeSpuMapper.updateSpikeSpuCountByIds(spiIds);
	}

	// 秒杀活动管理
	// 添加活动
	public int insert(Spikeactivity record) throws Exception {
		return spikeactivityMapper.insert(record);
	}

	// 根据ID获取活动
	public Spikeactivity selectByID(Integer id) throws Exception {
		return spikeactivityMapper.selectByPrimaryKey(id);
	}

	// 编辑活动
	public int update(Spikeactivity record) throws Exception {
		return spikeactivityMapper.updateByPrimaryKey(record);
	}

	// 获取活动列表
	public PageBean getListPage(Criteria criteria, Integer page, Integer size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<Spikeactivity> list = spikeactivityMapper.getListPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	// 编辑状态
	public int updateStatus(int status, int id) throws Exception {
		return spikeactivityMapper.changeStatus(status, id);
	}

	// 删除活动 逻辑删除
	public int deleteSpike(int id, int userid) throws Exception {
		Spikeactivity spike = new Spikeactivity();
		spike.setId(id);
		spike.setIsdel(true);
		spike.setDeltime(new Date());
		spike.setDeluserid(userid);
		return spikeactivityMapper.deleteSpike(spike);
	}

	// 获取店铺申请活动记录
	public PageBean GetApplyBySpikeIDPage(Criteria criteria, Integer page, Integer size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<V_SpikeActivity> list = spikeactivityMapper.GetApplyBySpikeIDPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	// 卖家获取活动信息
	public PageBean GetShopApplyByPage(Criteria criteria, Integer page, Integer size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<V_SpikeActivity> list = spikeactivityMapper.GetShopApplyByPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	// 获取参与活动商品信息
	public PageBean getSpuBySpikeIDPage(Criteria criteria, Integer page, Integer size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<SpSpuDto> list = spikeactivityMapper.getSpuBySpikeIDPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	public List<Spikeactivity> getByDate(Date start, Integer spiketype, Integer spikeid) throws Exception {
		CriteriaActivity cActivity = new CriteriaActivity();
		cActivity.setStartFrom(start);
		if (spikeid > 0)
			cActivity.setSpikeID(spikeid);
		cActivity.setActType(spiketype);
		return spikeactivityMapper.getByDate(cActivity);
	}

	// 店铺申请活动 管理

	public int insertSpikeshop(Spikeshop record) throws Exception {
		return spikeshopMapper.insert(record);
	}

	public Spikeshop getSpikeshopByID(Integer id) throws Exception {
		return spikeshopMapper.selectByPrimaryKey(id);
	}

	public int deleteSpikeshop(Integer id) throws Exception {
		Spikeshop ss = new Spikeshop();
		ss = spikeshopMapper.selectByPrimaryKey(id);
		spikeSpuMapper.deleteByShopID(ss.getShopid(), ss.getSpikeid());
		return spikeshopMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 店铺申请活动
	 * 
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public int updateCheck(int id, int status) throws Exception {
		return spikeshopMapper.updateCheck(id, status);
	}

	/**
	 * 批量审批店铺申请
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int updateCheckList(List<Integer> idlist, int status) throws Exception {
		List<Spikeshop> ssList = new ArrayList<Spikeshop>();
		Spikeshop ss = null;
		for (Integer id : idlist) {
			ss = new Spikeshop();
			ss.setId(id);
			ss.setStatus(status);
			ssList.add(ss);
		}
		return spikeshopMapper.updateCheckList(ssList);
	}

	/**
	 * 根据活动ID获取店铺申请记录
	 * 
	 * @param spikeid
	 * @return
	 * @throws Exception
	 */
	public List<Spikeshop> getBySpikeID(int spikeid) throws Exception {
		return spikeshopMapper.getBySpikeID(spikeid);
	}

	// 参与活动商品管理
	public int deleteSpikeSpu(Integer id) throws Exception {
		return spikeSpuMapper.deleteByPrimaryKey(id);
	}

	public int insertSpikeSpu(SpikeSpu record) throws Exception {
		return spikeSpuMapper.insert(record);
	}

	public int updateSpikeSpu(SpikeSpu record) throws Exception {
		return spikeSpuMapper.updateByPrimaryKey(record);
	}

	public int updateIsPhone(int isphone, int id) throws Exception {
		return spikeSpuMapper.updateIsPhone(isphone, id);
	}

	public SpikeSpu selectSpikeSpuByID(Integer id) throws Exception {
		return spikeSpuMapper.selectByPrimaryKey(id);
	}

	/**
	 * 根据店铺ID和活动ID获取spu列表
	 */
	public List<Spu> getSpuStartwithName(int shopid, int spikeid, String name) throws Exception {
		return spikeactivityMapper.getSpuStartwithName(shopid, spikeid, name);
	}

	/**
	 * 根据店铺ID和活动ID获取spu列表价格
	 */
	public int getSpuPriceStartwithName(int shopid, int spikeid, String name) throws Exception {
		return spikeactivityMapper.getSpuPriceStartwithName(shopid, spikeid, name);
	}

	/**
	 * 获取服务器时间
	 */
	public Date getServiceDate() throws Exception {
		return new Date();
	}

	/**
	 * API获取秒杀活动信息
	 */
	public SpikeDto getmsActivity(CriteriaActivity criteria, int page, int size, ReusltItem item) throws Exception {
		SpikeDto dto = spikeactivityMapperCustom.getmsActivity(criteria.getUsesite());
		if (dto != null) {
			criteria.setSpikeID(dto.getId());
			PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
			PageBean pageBean = pBeanUtil.getPage();
			List<SpikeSpuDto> prolist = spikeactivityMapperCustom.getSpuListByActivityIDPage(pBeanUtil);
			if (prolist == null || prolist.size() == 0) {
				dto = new SpikeDto();
			} else {
				item.setMaxRow(pageBean.getTr());
				item.setPageIndex(pageBean.getPc());
				item.setMaxRow(pageBean.getPs());
				item.setPage(pageBean.getTp());
				for (SpikeSpuDto productList : prolist) {
					dto.productList.add(productList);
				}
			}
		} else {
			dto = new SpikeDto();
		}
		return dto;
	}

	/**
	 * API获取秒杀活动信息
	 */
	public List<SpikeDto> getmsActivityList(CriteriaActivity criteria, int page, int size, ReusltItem item)
			throws Exception {
		List<SpikeDto> dto = spikeactivityMapperCustom.getmsActivityList(criteria.getUsesite());
		if (dto != null) {
			for (SpikeDto spikeDto : dto) {
				criteria.setSpikeID(spikeDto.getId());
				PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
				PageBean pageBean = pBeanUtil.getPage();
				List<SpikeSpuDto> prolist = spikeactivityMapperCustom.getSpuListByActivityIDPage(pBeanUtil);
				if (prolist == null || prolist.size() == 0) {
					spikeDto = new SpikeDto();
				} else {
					item.setMaxRow(pageBean.getTr());
					item.setPageIndex(pageBean.getPc());
					item.setMaxRow(pageBean.getPs());
					item.setPage(pageBean.getTp());
					for (SpikeSpuDto productList : prolist) {
						spikeDto.productList.add(productList);
					}
				}
			}
		} else {
			dto = new ArrayList<SpikeDto>();
		}
		return dto;
	}

	/**
	 * API获取闪购活动信息
	 */
	public PageBean getSgSpuByPage(Criteria criteria, int page, int size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pBeanUtil.getPage();
		List<SpikeSpuDto> list = spikeactivityMapperCustom.getSgSpuByPage(pBeanUtil);
		pageBean.setBeanList(list);
		return pageBean;
	}

	/**
	 * API获取团购活动信息
	 */
	public PageBean gettgSpuByPage(Criteria criteria, int page, int size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pBeanUtil.getPage();
		List<SpikeSpuDto> list = spikeactivityMapperCustom.gettgSpuByPage(pBeanUtil);
		Date date = new Date();
		list.forEach(x -> {
			long l = x.getEndtime().getTime() - date.getTime();
			l = l / 1000;
			long h = l / (60 * 60);
			long m = (l - h * 60 * 60) / 60;
			long s = (l - h * 60 * 60 - m * 60);
			x.setH(h);
			x.setM(m);
			x.setS(s);
			if (x.getSalecount() == null) {
				x.setSalecount(0);
			}
		});
		pageBean.setBeanList(list);
		return pageBean;
	}

	/**
	 * API获取促销活动信息
	 */
	public List<PromotionDto> getcxActivity() throws Exception {
		return spikeactivityMapperCustom.getcxActivity();
	}

	/**
	 * API获取促销活动参与商品信息
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public PageBean getSpuListByID(Criteria criteria, int page, int size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pBeanUtil.getPage();
		List<SpikeSpuDto> prolist = spikeactivityMapperCustom.getSpuListByActivityIDPage(pBeanUtil);
		pageBean.setBeanList(prolist);
		return pageBean;
	}

	/**
	 * API获取经彩活动参与商品信息
	 * 
	 * @param criteria
	 * @param page
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public PageBean getcxSpuByPage(Criteria criteria, int page, int size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pBeanUtil.getPage();
		List<SpikeSpuDto> prolist = spikeactivityMapperCustom.getcxSpuByPage(pBeanUtil);
		pageBean.setBeanList(prolist);
		return pageBean;
	}

	@Override
	public PageBean getExcitingByPage(Criteria criteria, int page, int size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pBeanUtil.getPage();
		List<ExcitingActDto> actlist = spikeactivityMapperCustom.getExcitingByPage(pBeanUtil);
		pageBean.setBeanList(actlist);
		return pageBean;
	}

	@Override
	public String getCode(int spikeid, int userid) throws Exception {
		UserSpike userSpike = userSpikeMapper.getCode(userid, spikeid);
		if (userSpike != null) {
			return userSpike.getSpikecode();
		} else {
			return "";
		}
	}

	@Override
	public String addUserSpike(int spikeid, int userid) throws Exception {
		UserSpike userSpike = userSpikeMapper.getCode(userid, spikeid);
		if (userSpike != null) {
			return userSpike.getSpikecode();
		} else {
			Spikeactivity sc = spikeactivityMapper.selectByPrimaryKey(spikeid);
			if (sc == null) {
				return "";
			}
			userSpike = new UserSpike();
			userSpike.setUserid(userid);
			userSpike.setSpikeid(spikeid);
			String uuid = java.util.UUID.randomUUID().toString();
			String code = MD5Util.encodeByMD516(uuid).replaceAll("\\w{4}(?!$)", "$0-");
			userSpike.setSpikecode(code);
			userSpike.setCreatetime(new Date());
			userSpike.setIsuse(0);
			userSpike.setOuttime(sc.getEndtime());

			if (userSpikeMapper.insert(userSpike) > 0) {
				return code;
			} else {
				return "";
			}
		}

	}

	@Override
	public ExcitingActDetailDto getBySpikeid(Integer id, Integer userid) throws Exception {
		ExcitingActDetailDto dto = spikeactivityMapperCustom.getBySpikeid(id);
		if (userid > 0) {
			UserSpike userSpike = userSpikeMapper.getCode(userid, id);
			if (userSpike != null) {
				dto.setActcode(userSpike.getSpikecode());
			}
		}
		return dto;
	}

	/**
	 * 根据经彩活动ID获取用户列表
	 */
	@Override
	public PageBean getUserListBySpikeID(Criteria criteria, int page, int size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pBeanUtil.getPage();
		List<ExcitingUserDto> prolist = userSpikeMapper.getbySpikeIDPage(pBeanUtil);
		pageBean.setBeanList(prolist);
		return pageBean;
	}

	/**
	 * 使用经彩活动验证码
	 */
	@Override
	public int updateUseCode(Integer id) throws Exception {

		UserSpike userSpike = userSpikeMapper.selectByPrimaryKey(id);
		if (userSpike == null) {
			return 0;
		}
		userSpike.setIsuse(1);
		userSpike.setUsetime(new Date());
		return userSpikeMapper.updateByPrimaryKey(userSpike);
	}

	@Override
	public PageBean getExcitListPage(Criteria criteria, int page, int size) throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<Spikeactivity> list = spikeactivityMapper.getExcitListPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public sgSpuDto getActBySpuid(int spuid, int acttype) throws Exception {
		return spikeactivityMapper.getActBySpuid(spuid, acttype);
	}

	@Override
	public List<Spikeactivity> Spikeactivitylist(Integer spuid, Integer type) throws Exception {
		return spikeactivityMapper.getActListBySpu(spuid, type);
	}

	@Override
	public int seekSeckillCount() {
		return spikeactivityMapperCustom.seekSeckillCount();
	}

	@Override
	public List<Spikeactivity> getByEndDate(Date start,Integer spiketype,Integer spikeid)throws Exception {
		CriteriaActivity cActivity = new CriteriaActivity();
		cActivity.setStartFrom(start);
		if (spikeid > 0)
			cActivity.setSpikeID(spikeid);
		cActivity.setActType(spiketype);
		return spikeactivityMapper.getByEndDate(cActivity);
	}

}
