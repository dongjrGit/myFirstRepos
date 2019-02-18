package com.yinlian.wssc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yinlian.api.app.dto.AlipayAppDto;
import com.yinlian.api.app.dto.IpsAppDto;
import com.yinlian.api.wap.controller.IpsConfig;
import com.yinlian.app.alipay.AppAlipayConfig;
import com.yinlian.wssc.web.interceptor.PageBean;
import com.yinlian.wssc.web.mapper.PayconfigMapper;
import com.yinlian.wssc.web.po.Payconfig;
import com.yinlian.wssc.web.po.PayconfigExample;
import com.yinlian.wssc.web.service.PayconfigService;
import com.yinlian.wssc.web.util.Criteria;
import com.yinlian.wssc.web.util.PageBeanUtil;

@Component("payconfigService")
public class PayconfigServiceImpl implements PayconfigService {

	@Autowired
	private PayconfigMapper payconfigMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {

		return payconfigMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Payconfig record) throws Exception {

		return payconfigMapper.insert(record);
	}

	@Override
	public List<Payconfig> selectByExample(PayconfigExample example)
			throws Exception {

		return payconfigMapper.selectByExample(example);
	}

	@Override
	public Payconfig selectByPrimaryKey(Integer id) throws Exception {

		return payconfigMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(Payconfig record) throws Exception {
		
		return payconfigMapper.updateByPrimaryKey(record);
	}

	@Override
	public Payconfig getByType(Integer paytype,Integer sites) throws Exception {
		
		return payconfigMapper.getByType(paytype,sites);
	}

	@Override
	public PageBean getListByPage(Criteria criteria, int page, int size)
			throws Exception {
		PageBeanUtil pBeanUtil = new PageBeanUtil(criteria, page, size);
		PageBean pBean = pBeanUtil.getPage();
		List<Payconfig> list = payconfigMapper.getListByPage(pBeanUtil);
		pBean.setBeanList(list);
		return pBean;
		
	}

	@Override
	public AlipayAppDto getAppConfig() throws Exception {
		AlipayAppDto dto=new AlipayAppDto();
	    dto.setAppid(AppAlipayConfig.getAlipayConfig().appid);
		dto.setPrivatekey(AppAlipayConfig.getAlipayConfig().private_key);
		dto.setSellerid(AppAlipayConfig.getAlipayConfig().partner);	
		dto.setNotifyurl(AppAlipayConfig.getAlipayConfig().notifyurl);	
		dto.setNotifyurl_tg(AppAlipayConfig.getAlipayConfig().notifyurl_tg);
		return dto;
	}
	@Override
	public IpsAppDto getIpsConfig() throws Exception {
		IpsAppDto dto=new IpsAppDto();
	    dto.setAccount(IpsConfig.getIpsConfig().account);
		dto.setActionUrl(IpsConfig.getIpsConfig().actionUrl);
		dto.setBankCode(IpsConfig.getIpsConfig().bankCode);
		dto.setBillEXP(IpsConfig.getIpsConfig().billEXP);
		dto.setCurrency(IpsConfig.getIpsConfig().currency);
		dto.setGateway(IpsConfig.getIpsConfig().gateway);
		dto.setIsCredit(IpsConfig.getIpsConfig().isCredit);
		dto.setLang(IpsConfig.getIpsConfig().lang);
		dto.setMd5key(IpsConfig.getIpsConfig().md5key);
		dto.setMerCode(IpsConfig.getIpsConfig().merCode);
		dto.setMerName(IpsConfig.getIpsConfig().merName);
		dto.setMsgId(IpsConfig.getIpsConfig().msgId);
		dto.setOrderEncode(IpsConfig.getIpsConfig().orderEncode);
		dto.setProductType(IpsConfig.getIpsConfig().productType);
		dto.setRetEncode(IpsConfig.getIpsConfig().retEncode);
		dto.setRetType(IpsConfig.getIpsConfig().retType);
		dto.setServerUrl(IpsConfig.getIpsConfig().appserverUrl);
		dto.setVersion(IpsConfig.getIpsConfig().version);
		return dto;
	}

}
