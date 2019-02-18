package com.yinlian.wssc.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.CardMapper;
import com.yinlian.Enums.CapitalChange_Type;
import com.yinlian.Enums.ChangeTableTypeEnum;
import com.yinlian.Enums.FinanceType;
import com.yinlian.Enums.FinanceTypeEnum;
import com.yinlian.Enums.UserFinance_Type;
import com.yinlian.wssc.web.mapper.FinancerecordsMapper;
import com.yinlian.wssc.web.mapper.UsercapitalMapper;
import com.yinlian.wssc.web.mapper.UserfinanceMapper;
import com.yinlian.wssc.web.mapper.giftcardMapper;
import com.yinlian.wssc.web.po.Financerecords;
import com.yinlian.wssc.web.po.Usercapital;
import com.yinlian.wssc.web.po.Userfinance;
import com.yinlian.wssc.web.po.giftcard;
import com.yinlian.wssc.web.po.giftcardExample;
import com.yinlian.wssc.web.service.giftcardService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("giftcardService")
public class giftcardServiceImpl implements giftcardService{

	@Autowired
	private giftcardMapper cardMapper;
	
	@Autowired
	private UsercapitalMapper usercapitalMapper;
	
	@Autowired
	private UserfinanceMapper userfinanceMapper;
	
	@Autowired
	private FinancerecordsMapper financerecordsMapper;
	
	@Override
	public int countByExample(giftcardExample example) {
		// TODO Auto-generated method stub
		return cardMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(giftcardExample example) {
		 
		return deleteByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return cardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(giftcard record) {
		// TODO Auto-generated method stub
		return cardMapper.insert(record);
	}

	@Override
	public int insertSelective(giftcard record) {
		// TODO Auto-generated method stub
		return insertSelective(record);
	}

	@Override
	public PageBean selectListPage(Criteria criteria,Integer page,Integer size) {
		PageBeanUtil pageBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pageBeanUtil.getPage();
		List<giftcard> list=cardMapper.getgiftcardList(pageBeanUtil);
		pBean.setBeanList(list);
		return pBean;
	}

	@Override
	public giftcard selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return cardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByExampleSelective(giftcard record, giftcardExample example) {
		// TODO Auto-generated method stub
		return cardMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateByExample(giftcard record, giftcardExample example) {
		// TODO Auto-generated method stub
		return cardMapper.updateByExample(record, example);
	}

	@Override
	public int updateByPrimaryKeySelective(giftcard record) {
		// TODO Auto-generated method stub
		return cardMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(giftcard record) {
		// TODO Auto-generated method stub
		return cardMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<giftcard> selectByExample(giftcardExample example) {
		// TODO Auto-generated method stub
		return cardMapper.selectByExample(example);
	}
	/***
	 * 礼品卡充值
	 * @param code 礼品卡号
	 * @param password 礼品卡密
	 * @param usedUserid 充值的用户id
	 * @param phone 充值验证手机号
	 * @return 
	 * @throws Exception
	 */
	@Override
	public int giftcardRecharge(String code, String password, int usedUserid, String phone) throws Exception {
		giftcard card = cardMapper.getByCode(code);
		if (card == null || card.getIsdel()) {
			return -1;
		}
		if (!card.getPassword().trim().equals(password)) {
			return -2;
		}
		//更新礼品卡使用信息
		if (card.getIsused()) {
			return -3;
		}
		card.setIsused(true);
		card.setUseduserid(usedUserid);
		card.setUsedmobile(phone);
		card.setRechargetime(new Date());
		cardMapper.updateByPrimaryKey(card);
		//更新用户资金账户
		Usercapital uc = usercapitalMapper.getBalanceRowLockById(usedUserid);
		uc.setBalance(uc.getBalance()+card.getFacevalue());
		usercapitalMapper.updateByPrimaryKey(uc);		
		//添加资金财务变动记录
		Userfinance uf = new Userfinance();
		uf.setBalance(uc.getBalance());
		uf.setCreatetime(new Date());
		uf.setDescription("礼品卡充值");
		uf.setFinancetype(FinanceType.金额.getValue());
		uf.setMoney(card.getFacevalue());
		uf.setNumber(card.getCode());
		uf.setStatus(UserFinance_Type.已支付.getValue());
		uf.setType(FinanceTypeEnum.礼品卡充值.getValue());
		uf.setUserid(usedUserid);
		userfinanceMapper.insert(uf);
		//添加资金表更改记录
		Financerecords fr = new Financerecords();
		fr.setCreatetime(new Date());
		fr.setStatus(0);
		fr.setType(ChangeTableTypeEnum.修改.getValue());
		fr.setUsercapitalid(uc.getId());
		fr.setUserid(usedUserid);
		return financerecordsMapper.insert(fr);
	}
}
