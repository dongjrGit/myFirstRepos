package com.yinlian.wssc.web.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.ChangeTableTypeEnum;
import com.yinlian.Enums.FinanceType;
import com.yinlian.Enums.GroupOrderDetailEnum;
import com.yinlian.Enums.GroupOrderEnum;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.Extended.LogType;
import com.yinlian.api.app.dto.GroupOrderDto;
import com.yinlian.api.app.dto.GroupOrderStateDto;
import com.yinlian.wssc.platform.vo.BaseResult;
import com.yinlian.wssc.platform.vo.ReusltItem;
import com.yinlian.wssc.web.dto.GroupByorderDto;
import com.yinlian.wssc.web.dto.GroupbuyorderDto;
import com.yinlian.wssc.web.dto.GroupbuyorderdetailDto;
import com.yinlian.wssc.web.dto.OrderGroupByDto;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.FinancerecordsMapper;
import com.yinlian.wssc.web.mapper.GroupbuyMapper;
import com.yinlian.wssc.web.mapper.GroupbuyhistoryMapper;
import com.yinlian.wssc.web.mapper.GroupbuyorderMapper;
import com.yinlian.wssc.web.mapper.GroupbuyorderMapperCustom;
import com.yinlian.wssc.web.mapper.GrouporderdetailMapper;
import com.yinlian.wssc.web.mapper.ShopMapper;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.mapper.UsersMapper;
import com.yinlian.wssc.web.po.Financerecords;
import com.yinlian.wssc.web.po.GroupbuyWithBLOBs;
import com.yinlian.wssc.web.po.GroupbuyhistoryWithBLOBs;
import com.yinlian.wssc.web.po.Groupbuyorder;
import com.yinlian.wssc.web.po.GroupbuyorderExample;
import com.yinlian.wssc.web.po.Grouporderdetail;
import com.yinlian.wssc.web.po.GrouporderdetailExample;
import com.yinlian.wssc.web.po.Shop;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.Users;
import com.yinlian.wssc.web.service.AccountsService;
import com.yinlian.wssc.web.service.GroupBuyOrderService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.CriteriaGroupBuy;
import com.yinlian.wssc.web.util.MD5Util;
import com.yinlian.wssc.web.util.PageBeanUtil;
import com.yinlian.wssc.web.util.ProductNumUtil;
import com.yinlian.wssc.web.util.SmsUtil;
import com.yl.soft.log.LogHandle;

@Component("groupBuyOrderService")
public class GroupBuyOrderServiceImpl implements GroupBuyOrderService {

	@Autowired
	private GroupbuyorderMapper groupbuyorderMapper;

	@Autowired
	private GroupbuyhistoryMapper groupbuyhistoryMapper;

	@Autowired
	private GrouporderdetailMapper grouporderdetailMapper;
	@Autowired
	private GroupbuyMapper groupbuyMapper;
	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private GroupbuyorderMapperCustom groupbuyorderMapperCustom;
	@Autowired
	private UsercapitalMapper usercapitalMapper;
	@Autowired
	private FinancerecordsMapper financerecordsMapper;
	@Autowired
	private UserfinanceMapper userfinanceMapper;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private AccountsService accountsService;

	/**
	 * 添加团购订单
	 * 
	 * @return
	 * @throws Exception
	 */
	public int insert(int userid, int gid, int count, float discount,
			Integer beans, String username, BaseResult item) throws Exception {
		Groupbuyorder record = new Groupbuyorder();
		GroupbuyWithBLOBs gb = groupbuyMapper.selectByPrimaryKey(gid);
		if (gb == null) {
			item.setCode(-105);
			item.setDesc("根据团购劵ID未能查询到团购信息！");
			return 0;
		}
		if(beans==null){
			beans=0;
		}
		String hacode = gb.getHacode();
		String ordercode = ProductNumUtil.GetOrderNum();
		record.setCount(count);
		record.setOrderprice(count * gb.getCprice());
		record.setDiscount(discount);
		record.setBuyerid(userid);
		record.setOrdercode(ordercode);
		record.setCreatetime(new Date());
		record.setGroupbuycode(hacode);
		record.setShopid(gb.getShopid());
		record.setStarttime(gb.getValiditystart());
		record.setEndtime(gb.getValidityend());
		record.setStatus(GroupOrderEnum.待付款.getValue());
		record.setIsdel(0);
		record.setBeanscount(beans);
		record.setReceivableprice(record.getOrderprice());
		if(beans>record.getOrderprice()){
			record.setPayprice(0.0f);
		}else{
			record.setPayprice(record.getOrderprice()-beans);
		}
		if (groupbuyhistoryMapper.countByHaCode(hacode) == 0) {
			GroupbuyhistoryWithBLOBs his = new GroupbuyhistoryWithBLOBs();
			his.setCprice(gb.getCprice());
			his.setGroupid(gb.getId());
			his.setHacode(gb.getHacode());
			his.setIsanytime(gb.getIsanytime());
			his.setIsbook(gb.getIsbook());
			his.setIsexpired(gb.getIsexpired());
			his.setOprice(gb.getOprice());
			his.setSalescount(gb.getSalescount()+count);
			his.setShopid(gb.getShopid());
			his.setStatus(gb.getStatus());
			his.setStock(gb.getStock());
			his.setType(gb.getType());
			his.setValidityend(gb.getValidityend());
			his.setValiditystart(gb.getValiditystart());
			his.setTitle(gb.getTitle());
			his.setListdesc(gb.getListdesc());
			his.setDetaildesc(gb.getDetaildesc());
			his.setBuynotes(gb.getBuynotes());
			his.setContent(gb.getContent());
			his.setUsesite(gb.getUsesite());
			groupbuyhistoryMapper.insert(his);
		}
		gb.setSalescount(gb.getSalescount()+count);
		groupbuyMapper.updateByPrimaryKey(gb);//更新售量
		int ret = groupbuyorderMapper.insert(record);
		String [] orderparam=new String [2] ;
		if (ret > 0) {
			orderparam[0]=record.getOrdercode();
			orderparam[1]=record.getPayprice().toString();
			item.setData(orderparam);
		}
		return ret;
	}

	/**
	 * 根据ID获取订单信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Groupbuyorder selectByPrimaryKey(Integer id) throws Exception {
		return groupbuyorderMapper.selectByPrimaryKey(id);
	}

	/**
	 * 修改订单
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int updateByPrimaryKey(Groupbuyorder record) throws Exception {
		return groupbuyorderMapper.updateByPrimaryKey(record);
	}

	/**
	 * 删除团购订单
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delOrder(int id) throws Exception {
		return groupbuyorderMapper.delOrder(id);
	}

	/**
	 * 取消团购订单
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateCancel(int id) throws Exception {
		int status = GroupOrderEnum.已取消.getValue();
		Groupbuyorder order = groupbuyorderMapper.selectByPrimaryKey(id);
		if (order != null) {
			if (order.getStatus() != GroupOrderEnum.待付款.getValue()) {
				LogHandle.error(LogType.Order, "团购订单状态不是待付款，不能进行取消操作，id:" + id,
						"/grouporder/updateCancel");
				return 0;
			}
		}
		return groupbuyorderMapper.updateStatus(status, id);
	}

	/**
	 * 付款
	 */
	public int updateFK(int id, int userid) throws Exception {
		int status = GroupOrderEnum.待消费.getValue();
		Groupbuyorder order = groupbuyorderMapper.selectByPrimaryKey(id);
		GroupbuyhistoryWithBLOBs gb = new GroupbuyhistoryWithBLOBs();
		String tgm = ""; // 团购码
		if (order != null) {
			if (order.getStatus() != GroupOrderEnum.待付款.getValue()) {
				LogHandle.error(LogType.Order, "团购订单状态不是待付款，不能进行付款操作，id:" + id,
						"/grouporder/updateFK");
				return 0;
			}
			// 更新团购劵销量
			gb = groupbuyhistoryMapper.getByHaCode(order.getGroupbuycode());
			GroupbuyWithBLOBs gbBLOBs = groupbuyMapper.selectByPrimaryKey(gb
					.getGroupid());
			if (gbBLOBs != null) {
				Integer salec = gbBLOBs.getSalescount();
				if (salec == null)
					salec = order.getCount();
				else
					salec += order.getCount();
				groupbuyMapper.updateByPrimaryKey(gbBLOBs);
			}
			List<Grouporderdetail> list = new ArrayList<Grouporderdetail>();
			Grouporderdetail detail = null;
			// 添加订单子表，生成团购码
			for (int i = 1; i <= order.getCount(); i++) {
				String uuid = java.util.UUID.randomUUID().toString();
				String code = MD5Util.encodeByMD516(uuid).replaceAll(
						"\\w{4}(?!$)", "$0-");
				tgm += code + ",";
				detail = new Grouporderdetail();
				detail.setShopid(order.getShopid());
				detail.setCode(code);
				detail.setCreatetime(new Date());
				detail.setGroupbuycode(gb.getHacode());
				detail.setOrderid(order.getId());
				detail.setPrice(gb.getCprice());
				detail.setStatus(GroupOrderDetailEnum.未使用.getValue());
				detail.setIsdel(0);
				list.add(detail);
			}
			grouporderdetailMapper.insertList(list);

		}
		// 买家余额资金变动
		float totalMoney = order.getOrderprice() - order.getDiscount();
		updateUserCapital(userid, totalMoney, order.getOrdercode(),
				CapitalChange_Type.支出.getValue(), "", "团购付款", "");
		// 卖家冻结金额资金变动
		Shop shop = shopMapper.selectByPrimaryKey(order.getShopid());
		changeFreezeMoney(shop.getUserid(), totalMoney, order.getOrdercode(),
				"团购付款冻结金额增加", "", "", true);
		order.setPayprice(totalMoney);
		order.setStatus(status);
		int ret = groupbuyorderMapper.updateFK(order);
		// 发送短信通知用户
		if (ret > 0) {
			sendMessage(userid, gb.getTitle(), order.getPayprice(),
					order.getCount(), tgm);
		}
		return ret;
	}

	/**
	 * 团购订单退款
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateTK(int id) throws Exception {
		int status = GroupOrderEnum.申请退款.getValue();
		Grouporderdetail detail = grouporderdetailMapper.selectByPrimaryKey(id);
		if (detail == null) {
			return 0;
		}
		Groupbuyorder gorder = groupbuyorderMapper.selectByPrimaryKey(detail
				.getOrderid());
		if (gorder == null) {
			return 0;
		}
		GroupbuyhistoryWithBLOBs gBloBs = groupbuyhistoryMapper
				.getByHaCode(detail.getGroupbuycode());
		if (gBloBs != null) {
			GroupbuyWithBLOBs gb = groupbuyMapper.selectByPrimaryKey(gBloBs
					.getGroupid());
			if (gb != null) {
				gb.setSalescount(gb.getSalescount() - 1);
				groupbuyMapper.updateByPrimaryKey(gb);
			}
		}

		// 发送信息

		grouporderdetailMapper.updateTK(GroupOrderDetailEnum.申请退款.getValue(),
				id);

		groupbuyorderMapper.updateStatus(status, gorder.getId());

		sendMessagetk(gorder.getBuyerid(), detail.getCode());

		return 1;
	}

	/**
	 * 团购订单退款
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateTKSH(int id) throws Exception {
		int status = GroupOrderEnum.已退款.getValue();
		Grouporderdetail detail = grouporderdetailMapper.selectByPrimaryKey(id);
		if (detail == null) {
			return 0;
		}
		Groupbuyorder gorder = groupbuyorderMapper.selectByPrimaryKey(detail
				.getOrderid());
		if (gorder == null) {
			return 0;
		}
		GrouporderdetailExample exampletk = new GrouporderdetailExample();
		GrouporderdetailExample.Criteria criteriatk = exampletk
				.createCriteria();
		criteriatk.andOrderidEqualTo(gorder.getId());
		criteriatk.andIdNotEqualTo(id);
		criteriatk.andStatusEqualTo(GroupOrderDetailEnum.申请退款.getValue());
		List<Grouporderdetail> listtk = grouporderdetailMapper
				.selectByExample(exampletk);
		if (listtk != null && listtk.size() > 0) {
			status = GroupOrderEnum.申请退款.getValue();
		} else {
			GrouporderdetailExample example = new GrouporderdetailExample();
			GrouporderdetailExample.Criteria criteria = example
					.createCriteria();
			criteria.andOrderidEqualTo(gorder.getId());
			criteria.andStatusEqualTo(GroupOrderDetailEnum.未使用.getValue());
			List<Grouporderdetail> list = grouporderdetailMapper
					.selectByExample(example);
			if (list != null && list.size() > 0) {
				status = GroupOrderEnum.待消费.getValue();
			}
		}
		// 发送信息
		groupbuyorderMapper.updateStatus(status, gorder.getId());

		int returns = grouporderdetailMapper.updateTK(
				GroupOrderDetailEnum.已退款.getValue(), id);
		
		//资金变动
		float returnmoney=gorder.getPayprice()/gorder.getCount();
		// 本地用户资金处理
		Userfinance finance = userfinanceMapper.getListByNumberAndType(
				gorder.getOrdercode(), CapitalChange_Type.冻结金额增加.getValue());
		if (finance == null) {
			LogHandle.error(LogType.Order, "根据团购订单获取用户资金失败，detailid:" + id,
					"/grouporder/updateTKSH");
		}
//		if (finance.getUserid() == sellerid) {
			// 卖家资金处理
			changeFreezeMoney(finance.getUserid(),returnmoney, gorder.getOrdercode(),
					"团购订单退款，卖家冻结金额扣除", "", finance.getPaynum(), false);
			// 买家资金处理
			updateUserCapital(gorder.getBuyerid(), returnmoney,
					gorder.getOrdercode(), CapitalChange_Type.收入.getValue(),
					finance.getPaynum(), "团购订单退款，买家资金退回", "");
//		}

		if (returns > 0) {
			sendMessagetksh(gorder.getBuyerid(), detail.getCode());
		}
		
		return 1;
	}

	/**
	 * 根据团购劵哈希码获取团购信息
	 *
	 * @param hacode
	 * @return
	 * @throws Exception
	 */
	public GroupbuyhistoryWithBLOBs getByHaCode(String hacode) throws Exception {

		return groupbuyhistoryMapper.getByHaCode(hacode);
	}

	/**
	 * 根据团购码获取订单子表信息
	 */
	public Grouporderdetail getByCode(String code) throws Exception {
		return grouporderdetailMapper.getByCode(code);
	}

	/**
	 * 付款成功后发送消息
	 * 
	 * @param userid
	 * @param title
	 * @param price
	 * @param code
	 */
	private void sendMessage(int userid, String title, Float price,
			Integer count, String code) throws Exception {
		Users user = usersMapper.selectByPrimaryKey(userid);
		String tel = user.getMobile();
		String message = "【因联商城】您已购买成功团购劵" + title + ",数量" + count + ",金额" + price
				+ "。团购码为" + code.substring(0, code.length() - 1) + "";
		SmsUtil.sendMessage(tel, message);
	}

	private void sendMessagetk(int userid, String code) throws Exception {
		Users user = usersMapper.selectByPrimaryKey(userid);
		String tel = user.getMobile();
		String message = "【因联商城】您的团购劵" + code
				+ "已申请退款成功，卖家会在3-5个工作日处理您的申请，请您及时查看短信。";
		SmsUtil.sendMessage(tel, message);
	}

	private void sendMessagetksh(int userid, String code) throws Exception {
		Users user = usersMapper.selectByPrimaryKey(userid);
		String tel = user.getMobile();
		String message = "【因联商城】您的团购劵" + code
				+ "退款申请已审核通过，平台会在3-5个工作日将退款返回您的账户中，请您及时查看。";
		SmsUtil.sendMessage(tel, message);
	}

	private void sendMessageuse(int userid, String code) throws Exception {
		Users user = usersMapper.selectByPrimaryKey(userid);
		String tel = user.getMobile();
		String message = "【因联商城】您的团购劵" + code + "已消费。时间" + new Date();
		SmsUtil.sendMessage(tel, message);
	}

	/**
	 * 根据订单ID获取团购劵信息
	 * 
	 * @param orderid
	 * @return
	 * @throws Exception
	 */
	public List<Grouporderdetail> getByOrderID(Integer orderid)
			throws Exception {
		return grouporderdetailMapper.getByOrderID(orderid);
	}

	/**
	 * 单个使用团购卷
	 * 
	 * @param status
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int updateUse(int id, ReusltItem item) throws Exception {
		int status = GroupOrderEnum.待评价.getValue();
		Grouporderdetail detail = grouporderdetailMapper.selectByPrimaryKey(id);
		if (detail == null) {
			item.setCode(-104);
			item.setDesc("根据团购码未能找到订单信息！");
			return 0;
		}
		if (detail.getUsetime() != null) {
			item.setCode(-105);
			item.setDesc("团购码已使用！");
			return 0;
		}
		Groupbuyorder gorder = groupbuyorderMapper.selectByPrimaryKey(detail
				.getOrderid());
		if (gorder == null) {
			return 0;
		}
		 float totalMoney = 0.0f;
		 // 本地用户资金处理
		 Userfinance finance = userfinanceMapper.getListByNumberAndType(
				 gorder.getOrdercode(),
		 CapitalChange_Type.冻结金额增加.getValue());
		 if (finance == null) {
		 LogHandle.error(LogType.Order, "根据团购订单获取用户资金失败，id:"
		 + gorder.getId(), "/grouporder/updateUse");
		 return 0;
		 }
		 Shop shop = shopMapper.selectByPrimaryKey(gorder.getShopid());
		 BigDecimal price = new BigDecimal(Float.toString(gorder
		 .getPayprice()));
		 BigDecimal count = new BigDecimal(Float.toString(gorder
		 .getCount()));
		 // 卖家冻结金额转余额
		 totalMoney = price.divide(count, 2, BigDecimal.ROUND_HALF_UP)
		 .floatValue();
		 freezeMoneyToBalance(finance.getId(), totalMoney,
		 "团购劵使用，卖家冻结金额转余额", shop.getUserid(), "");
		 
		GrouporderdetailExample example = new GrouporderdetailExample();
		GrouporderdetailExample.Criteria criteria = example.createCriteria();
		criteria.andOrderidEqualTo(gorder.getId());
		criteria.andIdNotEqualTo(id);
		criteria.andStatusEqualTo(GroupOrderDetailEnum.未使用.getValue());
		List<Grouporderdetail> list = grouporderdetailMapper
				.selectByExample(example);
		if (list != null && list.size() > 0) {
			status = GroupOrderEnum.待消费.getValue();
		}
		groupbuyorderMapper.updateStatus(status, gorder.getId());

		grouporderdetailMapper.updateUse(GroupOrderDetailEnum.已消费.getValue(),
				id);
		// 发送短信
		sendMessageuse(gorder.getBuyerid(), detail.getCode());
		return 1;
	}

	/**
	 * 批量使用团购卷
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int updateUseList(List<Integer> ids) throws Exception {
		List<Grouporderdetail> list = new ArrayList<Grouporderdetail>();
		Grouporderdetail detail = null;
		for (Integer id : ids) {
			detail = grouporderdetailMapper.selectByPrimaryKey(id);
			 if (detail != null) {
			 float totalMoney = 0.0f;
			 Groupbuyorder orders = groupbuyorderMapper
			 .selectByPrimaryKey(detail.getOrderid());
			 // 本地用户资金处理
			 Userfinance finance = userfinanceMapper.getListByNumberAndType(
			 orders.getOrdercode(),
			 CapitalChange_Type.冻结金额增加.getValue());
			 if (finance == null) {
			 LogHandle.error(LogType.Order, "根据团购订单获取用户资金失败，id:"
			 + orders.getId(), "/grouporder/updateUse");
			 return 0;
			 }
			 Shop shop = shopMapper.selectByPrimaryKey(orders.getShopid());
			 BigDecimal price = new BigDecimal(Float.toString(orders
			 .getPayprice()));
			 BigDecimal count = new BigDecimal(Float.toString(orders
			 .getCount()));
			 // 卖家冻结金额转余额
			 totalMoney = price.divide(count, 2, BigDecimal.ROUND_HALF_UP)
			 .floatValue();
			 freezeMoneyToBalance(finance.getId(), totalMoney,
			 "团购劵使用，卖家冻结金额转余额", shop.getUserid(), "");
			 }
			detail.setStatus(GroupOrderDetailEnum.已消费.getValue());
			detail.setId(id);
			list.add(detail);
		}
		return grouporderdetailMapper.updateUseList(list);
	}

	public PageBean getGroupByPage(Criteria criteria, int page, int size)
			throws Exception {

		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();

		List<GroupOrderDto> list = groupbuyorderMapperCustom
				.getGroupOrderPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	/**
	 * 根据ID获取订单子表信息
	 */
	public Grouporderdetail getDetailByID(int id) throws Exception {
		return grouporderdetailMapper.selectByPrimaryKey(id);
	}

	public int delOrderList(List<Integer> list) throws Exception {
		return groupbuyorderMapper.delOrderList(list);
	}

	 @Override
	 public PageBean getGroupOrderByToTalPage(CriteriaGroupBuy criteria, int
	 page, int size)
	 throws Exception {
	 PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
	 PageBean pageBean = pageBeanUtil.getPage();
	 List<GroupbuyorderDto> beanList = groupbuyorderMapperCustom
	 .getGroupOrderByToTalPage(pageBeanUtil);
	
	 
	 pageBean.setBeanList(beanList);
	 return pageBean;
	 }
	
	 @Override
	 public PageBean getGroupOrderByPage(CriteriaGroupBuy criteria, int page,
	 int size)
	 throws Exception {
	 PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
	 PageBean pageBean = pageBeanUtil.getPage();
	 List<GroupbuyorderdetailDto> beanList = grouporderdetailMapper
	 .getGroupOrderByToTalPage(pageBeanUtil);
	 pageBean.setBeanList(beanList);
	 return pageBean;
	 }

	@Override
	public PageBean getListOrderByPage(Criteria criteria, int page, int size)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<GroupByorderDto> beanList = groupbuyorderMapper
				.getListOrderByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 删除团购订单
	 * 
	 * @see com.yinlian.wssc.web.service.GroupBuyOrderService#delGroupOrder(java.lang.Integer)
	 */
	@Override
	public int delGroupOrder(Integer id) throws Exception {
		Groupbuyorder groupbuyorder = groupbuyorderMapper
				.selectByPrimaryKey(id);
		groupbuyorder.setIsdel(1);
		groupbuyorder.setDeltime(new Date());
		return groupbuyorderMapper.updateByPrimaryKey(groupbuyorder);
	}

	@Override
	public PageBean getGroupOrderByPage(Criteria criteria, int page, int size)
			throws Exception {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pageBean = pageBeanUtil.getPage();
		List<OrderGroupByDto> beanList = groupbuyorderMapper
				.getGroupOrderByPage(pageBeanUtil);
		pageBean.setBeanList(beanList);
		return pageBean;
	}

	/**
	 * 用户余额变动记录
	 * 
	 * @param userid
	 * @param userip
	 * @param type
	 * @param usercapitalid
	 * @return
	 * @throws Exception
	 */
	private int recordsAdd(int userid, String userip, int type,
			int usercapitalid) throws Exception {
		Financerecords financerecords = new Financerecords();
		financerecords.setUserid(userid);
		financerecords.setUserip(userip);
		financerecords.setType(type);
		financerecords.setCreatetime(new Date());
		financerecords.setStatus(0);
		financerecords.setUsercapitalid(usercapitalid);
		return financerecordsMapper.insert(financerecords);
	}

	/**
	 * 用户冻结资金处理
	 * 
	 * @param sellerid
	 * @param money
	 * @param ordercode
	 * @param desc
	 * @param userip
	 * @param paynum
	 * @param isadd
	 * @param item
	 * @return
	 * @throws Exception
	 */
	private int changeFreezeMoney(int sellerid, float money, String ordercode,
			String desc, String userip, String paynum, boolean isadd)
			throws Exception {
		Usercapital usercapital = usercapitalMapper
				.getBalanceRowLockById(sellerid);
		if (usercapital == null) {
			LogHandle.error(LogType.UserCapital, "资金表无对应用户数据，用户ID：" + sellerid,
					"grouporder/changeFreezeMoney");
			return 0;
		}
		if (isadd == false && usercapital.getBalance() < money
				&& usercapital.getFreezemoney() < money) {
			LogHandle.error(LogType.UserCapital, "用户资金不足，用户ID：" + sellerid,
					"grouporder/changeFreezeMoney");
			return 0;
		}
		float totalMoney = money;
		int type = 0;
		if (isadd) {
			usercapital.setFreezemoney(usercapital.getFreezemoney() + money);
			type = CapitalChange_Type.冻结金额增加.getValue();
		} else {
			type = CapitalChange_Type.冻结金额扣除.getValue();
			if (usercapital.getFreezemoney() > money) {
				usercapital
						.setFreezemoney(usercapital.getFreezemoney() - money);
			} else {
				usercapital.setBalance(usercapital.getBalance() - money);
			}
		}
		usercapitalMapper.updateByPrimaryKey(usercapital);
		// 资金变动记录
		financeAdd(sellerid, usercapital.getFreezemoney(), totalMoney, desc,
				paynum, userip, type, ordercode,
				UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		return recordsAdd(sellerid, "", ChangeTableTypeEnum.修改.getValue(),
				usercapital.getId());
	}

	/**
	 * 用户资金处理
	 * 
	 * @param userid
	 * @param money
	 * @param ordercode
	 * @param type
	 * @param paynum
	 * @param desc
	 * @param userip
	 * @param item
	 * @return
	 * @throws Exception
	 */
	public int updateUserCapital(int userid, float money, String ordercode,
			int type, String paynum, String desc, String userip)
			throws Exception {
		Usercapital usercapital = usercapitalMapper
				.getBalanceRowLockById(userid);
		if (usercapital == null) {
			LogHandle.error(LogType.UserCapital, "资金表无对应用户数据，用户ID：" + userid,
					"grouporder/changeUserCapital");
			return 0;
		}

		if (type == CapitalChange_Type.支出.getValue()
				&& usercapital.getBalance() < money) {
			LogHandle.error(LogType.UserCapital, "用户资金不足，用户ID：" + userid,
					"grouporder/changeUserCapital");
			return 0;
		}
		if (type == CapitalChange_Type.支出.getValue()) {
			usercapital.setBalance(usercapital.getBalance() - money);
		}
		if (type == CapitalChange_Type.收入.getValue()) {
			usercapital.setBalance(usercapital.getBalance() + money);
		}
		usercapitalMapper.updateByPrimaryKey(usercapital);
		// 资金变动记录
		financeAdd(userid, usercapital.getBalance(), money, desc, paynum,
				userip, type, ordercode, UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		return recordsAdd(userid, userip, ChangeTableTypeEnum.修改.getValue(),
				usercapital.getId());
	}

	/**
	 * 冻结金额解冻-使用团购劵
	 * 
	 * @param financeid
	 * @param desc
	 * @param userid
	 * @param userip
	 * @return
	 * @throws Exception
	 */
	private int freezeMoneyToBalance(int financeid, float money, String desc,
			int userid, String userip) throws Exception {
		Userfinance userfinance = new Userfinance();
		userfinance = userfinanceMapper.selectByPrimaryKey(financeid);
		if (userfinance == null) {
			LogHandle.error(LogType.UserCapital, "查询不到用户资金数据，ID：" + financeid,
					"grouporder/updateUse");
			return 0;
		}
		Usercapital usercapital = usercapitalMapper
				.getBalanceRowLockById(userid);
		if (usercapital == null) {
			LogHandle.error(LogType.UserCapital, "资金表无对应用户数据，用户ID：" + userid,
					"grouporder/updateUse");
			return 0;
		}
		if (money < 0 || usercapital.getFreezemoney() < money) {
			LogHandle.error(LogType.UserCapital, "用户冻结资金数据错误，用户ID：" + userid,
					"grouporder/updateUse");
			return 0;
		}
		usercapital.setBalance(usercapital.getBalance() + money);
		usercapitalMapper.updateByPrimaryKey(usercapital);
		// 资金变动记录
		financeAdd(userid, usercapital.getBalance(), money, desc,
				userfinance.getPaynum(), userip,
				CapitalChange_Type.解冻.getValue(), userfinance.getNumber(),
				UserFinance_Type.已支付.getValue());

		// 用户资金更改记录表
		return recordsAdd(userid, userip, ChangeTableTypeEnum.修改.getValue(),
				usercapital.getId());
	}

	/**
	 * 付款完成更改销量和状态
	 */
	public int updateFK(String ordercode, Double money,String trade_no) throws Exception {
		int status = GroupOrderEnum.待消费.getValue();
		Groupbuyorder order = groupbuyorderMapper.getByOrderCode(ordercode);
		GroupbuyhistoryWithBLOBs gb = new GroupbuyhistoryWithBLOBs();
		String tgm = ""; // 团购码
		if (order == null) {
			LogHandle.error(LogType.Order, "根据团购订单编号未能检索到数据，code:" + ordercode,
					"/grouporder/updateFK");
			return 0;
		}
		if (order.getStatus() != GroupOrderEnum.待付款.getValue()) {
			LogHandle.error(LogType.Order, "团购订单状态不是待付款，不能进行付款操作，code:"
					+ ordercode, "/grouporder/updateFK");
			return 0;
		}
		// 验证支付金额
		double totalMoney =Double.valueOf(order.getPayprice().toString()) ;
		if (money != totalMoney) {
			LogHandle.error(LogType.Order, "团购订单支付金额不匹配，price:" + totalMoney,
					"/grouporder/updateFK");
			return 0;
		}
		// 更新团购劵销量
		gb = groupbuyhistoryMapper.getByHaCode(order.getGroupbuycode());
		GroupbuyWithBLOBs gbBLOBs = groupbuyMapper.selectByPrimaryKey(gb
				.getGroupid());
		if (gbBLOBs != null) {
			Integer salec = gbBLOBs.getSalescount();
			if (salec == null)
				salec = order.getCount();
			else
				salec += order.getCount();
			groupbuyMapper.updateByPrimaryKey(gbBLOBs);
		}
		List<Grouporderdetail> list = new ArrayList<Grouporderdetail>();
		Grouporderdetail detail = null;
		// 添加订单子表，生成团购码
		for (int i = 1; i <= order.getCount(); i++) {
			String uuid = java.util.UUID.randomUUID().toString();
			String code = MD5Util.encodeByMD516(uuid).replaceAll("\\w{4}(?!$)",
					"$0-");
			tgm += code + ",";
			detail = new Grouporderdetail();
			detail.setShopid(order.getShopid());
			detail.setCode(code);
			detail.setCreatetime(new Date());
			detail.setGroupbuycode(gb.getHacode());
			detail.setOrderid(order.getId());
			detail.setPrice(gb.getCprice());
			detail.setStatus(GroupOrderDetailEnum.未使用.getValue());
			detail.setIsdel(0);
			list.add(detail);
		}
		grouporderdetailMapper.insertList(list);
		order.setStatus(status);
		int ret = groupbuyorderMapper.updateFK(order);
		financeAdd(order.getBuyerid(), 0.0f, (float)totalMoney,
				"团购订单买家在线支付", trade_no, "", CapitalChange_Type.支出.getValue(),
				ordercode, UserFinance_Type.已支付.getValue());

		// 卖家冻结金额资金变动
		Shop shop = shopMapper.selectByPrimaryKey(order.getShopid());
		changeFreezeMoney(shop.getUserid(), (float)totalMoney, order.getOrdercode(),
				"团购付款冻结金额增加", "", "", true);
		// 发送短信通知用户
		if (ret > 0) {
			sendMessage(order.getBuyerid(), gb.getTitle(), order.getPayprice(),
					order.getCount(), tgm);
		}
		return ret;
	}
	/**
	 * 添加用户资金变更
	 * 
	 * @param userid
	 *            用户id
	 * @param balance
	 *            用户余额
	 * @param money
	 *            变更金额
	 * @param desc
	 *            说明
	 * @param paynum
	 *            流水号
	 * @param userip
	 *            客户端ip
	 * @param type
	 *            类型
	 * @param ordercode
	 * @param status
	 * @return
	 * @throws Exception
	 */
	private int financeAdd(int userid, double balance, double money,
			String desc, String paynum, String userip, int type,
			String ordercode, int status) throws Exception {
		Userfinance userfinance = new Userfinance();
		userfinance.setUserid(userid);
		userfinance.setCreatetime(new Date());
		userfinance.setBalance(balance);
		userfinance.setMoney(money);
		userfinance.setDescription(desc);
		userfinance.setPaynum(paynum);
		userfinance.setUserip(userip);
		userfinance.setType(type);
		userfinance.setNumber(ordercode);
		userfinance.setStatus(status);
		userfinance.setFinancetype(FinanceType.金额.getValue());
		return userfinanceMapper.insert(userfinance);
	}
	/**
	 * 从团购订单列表去支付
	 */
	public int savePayOrder(String ordercode, Integer userid, String username,
			BaseResult item) throws Exception {
		Groupbuyorder order = groupbuyorderMapper.getByOrderCode(ordercode);
		if (order == null) {
			LogHandle.error(LogType.Order, "根据团购订单号未能检索到数据，ordercode:"
					+ ordercode, "/grouporder/payOrder");
			return 0;
		}
		if (order.getStatus() != GroupOrderEnum.待付款.getValue()) {
			LogHandle.error(LogType.Order, "团购订单状态不是待付款，不能进行付款操作，ordercode:"
					+ ordercode, "/grouporder/payOrder");
			return 0;
		}

		Integer beans = order.getBeanscount();
		if (beans != null && beans > 0) {
			Users users = new Users();
			if (users.getAvailablebeans() < beans) {
				item.setDesc("积分不够");
				LogHandle.warn(LogType.Order, "团购订单提交失败积分不足",
						"GroupBuyOrderServiceImpl/add");
				return -2;
			}
		} else {
			beans = 0;
		}
		GroupbuyhistoryWithBLOBs gb = new GroupbuyhistoryWithBLOBs();
		String tgm = ""; // 团购码
		// 更新团购劵销量
		gb = groupbuyhistoryMapper.getByHaCode(order.getGroupbuycode());
		GroupbuyWithBLOBs gbBLOBs = groupbuyMapper.selectByPrimaryKey(gb
				.getGroupid());
		if (gbBLOBs != null) {
			Integer salec = gbBLOBs.getSalescount();
			if (salec == null)
				salec = order.getCount();
			else
				salec += order.getCount();
			groupbuyMapper.updateByPrimaryKey(gbBLOBs);
		}
		List<Grouporderdetail> list = new ArrayList<Grouporderdetail>();
		Grouporderdetail detail = null;
		// 添加订单子表，生成团购码
		for (int i = 1; i <= order.getCount(); i++) {
			String uuid = java.util.UUID.randomUUID().toString();
			String code = MD5Util.encodeByMD516(uuid).replaceAll("\\w{4}(?!$)",
					"$0-");
			tgm += code + ",";
			detail = new Grouporderdetail();
			detail.setShopid(order.getShopid());
			detail.setCode(code);
			detail.setCreatetime(new Date());
			detail.setGroupbuycode(gb.getHacode());
			detail.setOrderid(order.getId());
			detail.setPrice(gb.getCprice());
			detail.setStatus(GroupOrderDetailEnum.未使用.getValue());
			detail.setIsdel(0);
			list.add(detail);
		}
		grouporderdetailMapper.insertList(list);

		// 买家余额资金变动
		float totalMoney = order.getPayprice();
	     int res=updateUserCapital(userid, totalMoney, order.getOrdercode(),
				CapitalChange_Type.支出.getValue(), "", "团购付款", "");
	     if(res<=0){
	    	 return res;
	     }
		// 卖家冻结金额资金变动
		Shop shop = shopMapper.selectByPrimaryKey(order.getShopid());
		changeFreezeMoney(shop.getUserid(), order.getReceivableprice(), order.getOrdercode(),
				"团购付款冻结金额增加", "", "", true);
//		order.setPayprice(totalMoney);
		order.setStatus(GroupOrderEnum.待消费.getValue());
		int ret = groupbuyorderMapper.updateFK(order);
		// 发送短信通知用户
		if (ret > 0) {
			sendMessage(userid, gb.getTitle(), order.getPayprice(),
					order.getCount(), tgm);
		}
		return 1;
	}

	@Override
	public GroupOrderStateDto selectCount(Integer userid) throws Exception {

		return groupbuyorderMapper.getCount(userid);
	}

	@Override
	public int updateByPrimaryKeySelective(Groupbuyorder record)
			throws Exception {

		return groupbuyorderMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 获取待付款数量
	 * 
	 * @see com.yinlian.wssc.web.service.GroupBuyOrderService#selectDfkCount(java.lang.Integer)
	 */
	@Override
	public int selectDfkCount(Integer userid) throws Exception {
		GroupbuyorderExample example = new GroupbuyorderExample();
		GroupbuyorderExample.Criteria criteria = example.createCriteria();
		criteria.andBuyeridEqualTo(userid);
		criteria.andStatusEqualTo(GroupOrderEnum.待付款.getValue());
		List<Groupbuyorder> list = groupbuyorderMapper.selectByExample(example);
		List<Groupbuyorder> subList=list.stream().filter(x->x.getIsdel()==0).collect(Collectors.toList());
		return subList.size();
	}

	/**
	 * 待消费数量
	 * 
	 * @see com.yinlian.wssc.web.service.GroupBuyOrderService#selectDxfCount(java.lang.Integer)
	 */
	@Override
	public int selectDxfCount(Integer userid) throws Exception {
		GroupbuyorderExample example = new GroupbuyorderExample();
		GroupbuyorderExample.Criteria criteria = example.createCriteria();
		criteria.andBuyeridEqualTo(userid);
		criteria.andStatusEqualTo(GroupOrderEnum.待消费.getValue());
		List<Groupbuyorder> list = groupbuyorderMapper.selectByExample(example);
		List<Groupbuyorder> subList=list.stream().filter(x->x.getIsdel()==0).collect(Collectors.toList());
		return subList.size();
		// int dxf = 0;
		// GroupbuyorderExample example = new GroupbuyorderExample();
		// GroupbuyorderExample.Criteria criteria = example.createCriteria();
		// criteria.andBuyeridEqualTo(userid);
		// List<Groupbuyorder> list =
		// groupbuyorderMapper.selectByExample(example);
		// for (Groupbuyorder groupbuyorder : list) {
		// int id = groupbuyorder.getId();
		// GrouporderdetailExample example2 = new GrouporderdetailExample();
		// GrouporderdetailExample.Criteria criteria2 = example2
		// .createCriteria();
		// criteria2.andOrderidEqualTo(id);
		// List<Grouporderdetail> list2 = grouporderdetailMapper
		// .selectByExample(example2);
		// if (groupbuyorder.getCount() > list2.size()) {
		// dxf++;
		// }
		//
		// }
		// return dxf;
	}

	/**
	 * 待评论数量
	 * 
	 * @see com.yinlian.wssc.web.service.GroupBuyOrderService#selectDplCount(java.lang.Integer)
	 */
	@Override
	public int selectDplCount(Integer userid) throws Exception {
		GroupbuyorderExample example = new GroupbuyorderExample();
		GroupbuyorderExample.Criteria criteria = example.createCriteria();
		criteria.andBuyeridEqualTo(userid);
		criteria.andStatusEqualTo(GroupOrderEnum.待评价.getValue());
		List<Groupbuyorder> list = groupbuyorderMapper.selectByExample(example);
		List<Groupbuyorder> subList=list.stream().filter(x->x.getIsdel()==0).collect(Collectors.toList());
		return subList.size();
	}

	/**
	 * 退款申请中数量
	 * 
	 * @see com.yinlian.wssc.web.service.GroupBuyOrderService#selectTkCount(java.lang.Integer)
	 */
	@Override
	public int selectTkCount(Integer userid) throws Exception {
		GroupbuyorderExample example = new GroupbuyorderExample();
		GroupbuyorderExample.Criteria criteria = example.createCriteria();
		criteria.andBuyeridEqualTo(userid);
		criteria.andStatusEqualTo(GroupOrderEnum.申请退款.getValue());
		List<Groupbuyorder> list = groupbuyorderMapper.selectByExample(example);
		List<Groupbuyorder> subList=list.stream().filter(x->x.getIsdel()==0).collect(Collectors.toList());
		return subList.size();
	}
	
	public Groupbuyorder getByOrderCode(String ordercode) throws Exception{
		return groupbuyorderMapper.getByOrderCode(ordercode);
	}

	@Override
	public List<Groupbuyorder> getUnpaidGroupOrders() throws Exception {
		
		return groupbuyorderMapper.getUnpaidGroupOrders();
	}

	@Override
	public int updateStatus(int status, int id) throws Exception {
		return groupbuyorderMapper.updateStatus(status, id);
	}
}
