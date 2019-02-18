package com.yinlian.wssc.web.service;

import com.yinlian.wssc.web.po.Smsrecord;

public interface SmsService {
	/**
	 * 添加验证码信息
	 * @param sms
	 * @return
	 * @throws Exception
	 */
	public int addSms(Smsrecord sms) throws Exception;
	/**
	 * 通过id查询验证信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Smsrecord selectSmsByPhone(String phone) throws Exception;
}
