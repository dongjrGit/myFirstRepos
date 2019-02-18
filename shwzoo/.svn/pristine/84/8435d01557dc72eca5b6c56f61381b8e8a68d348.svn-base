package com.yinlian.wssc.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.yinlian.Enums.ActivityTypeEnum;
import com.yinlian.Enums.ManZengTypeEnum;
import com.yinlian.api.app.dto.UpDateActDto;
import com.yinlian.wssc.web.dto.ActivityDto;
import com.yinlian.wssc.web.dto.FullGiftDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.ActivityMarketMapper;
import com.yinlian.wssc.web.mapper.FullgiftMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.SkuMapper;
import com.yinlian.wssc.web.po.ActivityMarket;
import com.yinlian.wssc.web.po.Coupon;
import com.yinlian.wssc.web.po.Fullgift;
import com.yinlian.wssc.web.po.Sku;
import com.yinlian.wssc.web.service.ActivityService;
import com.yinlian.wssc.web.service.CouponService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityMarketMapper activityMarketMapper;

	@Autowired
	private FullgiftMapper fullgiftMapperr;

	@Autowired
	private SkuMapper skuMapper;

	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private CouponService couponService;

	@Override
	public int add(ActivityMarket activity) throws Exception {
		return activityMarketMapper.insert(activity);
	}

	@Override
	public int update(ActivityMarket activity) throws Exception {
		return activityMarketMapper.updateByPrimaryKey(activity);
	}

	@Override
	public int delete(int id, int userid) throws Exception {
		return activityMarketMapper.deleteLogicById(userid, id);
	}

	@Override
	public ActivityMarket getById(int id) throws Exception {
		return activityMarketMapper.selectByPrimaryKey(id);
	}

	@Override
	public int addGift(Fullgift gift) throws Exception {
		activityMarketMapper.isCheck(0, gift.getActivityid());
		return fullgiftMapperr.insert(gift);
	}

	@Override
	public int deleteGift(int giftid) throws Exception {
		Fullgift gift = fullgiftMapperr.selectByPrimaryKey(giftid);
		activityMarketMapper.isCheck(0, gift.getActivityid());
		return fullgiftMapperr.deleteByPrimaryKey(giftid);

	}

	@Override
	public Fullgift getDetailByID(int id) throws Exception {
		return fullgiftMapperr.selectByPrimaryKey(id);
	}

	@Override
	public List<Fullgift> getGiftListByID(int actid) throws Exception {

		return fullgiftMapperr.selectByActId(actid, 0);
	}

	@Override
	public List<FullGiftDto> getGiftList(int actid) throws Exception {
		List<Fullgift> fglist = new ArrayList<Fullgift>();
		fglist = fullgiftMapperr.selectByActId(actid, 1);
		List<FullGiftDto> list = new ArrayList<FullGiftDto>();
		Fullgift gift = new Fullgift();
		if (fglist!=null && fglist.size() > 0) {
			gift = fglist.get(0);
		}else{
			return list;
		}
		if (gift.getGifttype() == ManZengTypeEnum.优惠卷.getValue()) {
			list = fullgiftMapperr.selectGiffandCoupon(actid);
		} else if (gift.getGifttype() == ManZengTypeEnum.商品.getValue()) {
			list = fullgiftMapperr.selectGiffandSku(actid);
		} else {
			list = fullgiftMapperr.selectGiffByActId(actid);
		}
		return list;
	}

	@Override
	public List<FullGiftDto> getGiftListByIDList(List<Integer> actids)
			throws Exception {
		List<Fullgift> fullgifts = fullgiftMapperr.selectByActIds(actids);
		List<Integer> fgids = new ArrayList<Integer>();
		fullgifts.forEach(x -> {
			fgids.add(x.getGiftid());
		});
		List<Sku> skulist = skuMapper.getListByIds(fgids);
		List<FullGiftDto> list = new ArrayList<FullGiftDto>();
		List<Coupon> couponlist = couponService.getListByIds(fgids);
		FullGiftDto dto;
		for (Fullgift a : fullgifts) {
			switch (ManZengTypeEnum.values()[a.getGifttype()]) {
			case 优惠卷:
				Coupon b = null;
				for (Coupon x : couponlist) {
					if (x.getId() == a.getGiftid()) {
						b = x;
						break;
					}
				}
				if (b == null) {
					continue;
				}
				dto = new FullGiftDto();
				dto.setId(a.getId());
				dto.setActivityID(a.getActivityid());
				dto.setGiftType(a.getGifttype());
				dto.setGiftID(b.getId());
				dto.setNum(b.getCouponnum());
				dto.setName(b.getCouponname());
				dto.setPrice(Double.valueOf(b.getFacevalue()));
				dto.setCount(a.getGiftcount());
				list.add(dto);
				break;
			case 商品:
				Sku c = null;
				for (Sku x : skulist) {
					if (x.getId() == a.getGiftid()) {
						c = x;
						break;
					}
				}
				if (c == null) {
					continue;
				}
				dto = new FullGiftDto();
				dto.setId(a.getId());
				dto.setActivityID(a.getActivityid());
				dto.setGiftType(a.getGifttype());
				dto.setCount(a.getGiftcount());
				dto.setName(c.getName());
				dto.setNum(c.getNum());
				dto.setPrice(c.getPrice().doubleValue());
				dto.setImgUrl(c.getImgurl());
				dto.setGiftID(c.getId());
				list.add(dto);
				break;
			case 积分:
				dto = new FullGiftDto();
				dto.setId(a.getId());
				dto.setActivityID(a.getActivityid());
				dto.setGiftType(a.getGifttype());
				dto.setCount(a.getGiftcount());
				dto.setPoints(a.getPoints());
				list.add(dto);
				break;
			}
		}
		return null;
	}

	@Override
	public int changeCount(int id, int count) throws Exception {
		return fullgiftMapperr.updateChangeCount(id, count);
	}

	@Override
	public int changePoint(int id, int points) throws Exception {
		return fullgiftMapperr.updateChangePoint(id, points);
	}

	@Override
	public PageBean getList(Criteria criteria, Integer page, int size)
			throws Exception {
		if (criteria == null) {
			throw new IllegalArgumentException(
					"The parameter Criteria is null!");
		}
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<ActivityMarket> beanList = activityMarketMapper
				.getListPage(pageBeanUtil);
		for (ActivityMarket act : beanList) {
			act.setShopname(shopMapper.selectByPrimaryKey(act.getShopid())
					.getName());
		}
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	@Override
	public List<ActivityMarket> getAvailableActivity(List<Integer> spuid,
			List<Integer> shopid, boolean isphone) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityMarket> getBySpu(int spuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActivityDto getByMarketID(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActivityDto> GetByMarketIDList(List<Integer> idlist)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateStatus(int id, int status) throws Exception {
		return activityMarketMapper.changeStatus(status, id);
	}

	@Override
	public int checkStatus(int id, int ss) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkStatuList(List<Integer> ids, int ss) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCheck(int id) throws Exception {
		return activityMarketMapper.isCheck(1, id);
	}

	@Override
	public int updateCheckList(List<Integer> ids) throws Exception {
		return activityMarketMapper.updateCheckList(ids);
	}

	@Override
	public List<Fullgift> getByIds(List<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return fullgiftMapperr.getByIds(ids);
	}

	@Override
	public int updateFullGiftCountByIds(List<UpDateActDto> ugiftdtos)
			throws Exception {
		return fullgiftMapperr.updateFullGiftCountByIds(ugiftdtos);
	}

	@Override
	public int updateStockByIds(List<UpDateActDto> ugiftdtos) {
		return activityMarketMapper.updateStockByIds(ugiftdtos);
	}

	public List<ActivityDto> getActivityDtoByIDs(List<Integer> list)
			throws Exception {
		List<ActivityDto> activityDtos = activityMarketMapper
				.getActivityDtoByIDs(list);
		if(activityDtos!=null){
			for (ActivityDto Dto : activityDtos) {
				if (Dto.getActtype() == ActivityTypeEnum.满赠.getValue()) {
					Dto.setGiftList(getFullGift(Dto.getId()));
				}
			}
		}
		return activityDtos;
	}

	public ActivityDto getActivityDtoByID(Integer id) throws Exception {
		ActivityDto dto = activityMarketMapper.getActivityDtoByID(id);
		if (dto.getActtype() == ActivityTypeEnum.满赠.getValue()) {
			dto.setGiftList(getFullGift(dto.getId()));
		}
		return dto;
	}

	private List<FullGiftDto> getFullGift(int actid) throws Exception {
		List<FullGiftDto> list = new ArrayList<FullGiftDto>();
		List<Fullgift> giftList = fullgiftMapperr.selectByActId(actid, 0);
		for (Fullgift fg : giftList) {
			ManZengTypeEnum fTypeEnum = ManZengTypeEnum.valueOf(fg.getGifttype());
			switch (fTypeEnum) {
			case 优惠卷:
				Optional<FullGiftDto> opc=fullgiftMapperr.selectGiffandCoupon(actid).
				stream().filter(x->x.getGiftID()!=null && x.getGiftID().equals(fg.getGiftid())).findFirst();
				if (opc.isPresent()){
					list.add(opc.get());
				}					
				break;
			case 商品:
				Optional<FullGiftDto> ops= fullgiftMapperr.selectGiffandSku(actid).
						stream().filter(x->x.getGiftID()!=null && x.getGiftID().equals(fg.getGiftid())).findFirst();
				if (ops.isPresent())
					list.add(ops.get());
				break;
			case 积分:
				Optional<FullGiftDto> opp= fullgiftMapperr.selectGiffByActId(actid).
						stream().filter(x->x.getGiftID()==null).findFirst();
				if (opp.isPresent())
					list.add(opp.get());
				break;
			default:
				break;
			}
		}
		return list;
	}

	@Override
	public Fullgift getbyTypeAndGiftid(int gtype, int gid) throws Exception {
		
		return fullgiftMapperr.getbyTypeAndGiftid(gtype, gid);
	}
}
