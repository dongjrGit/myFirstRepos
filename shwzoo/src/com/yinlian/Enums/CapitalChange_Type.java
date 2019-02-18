package com.yinlian.Enums;

/**
 * 
 * @author mjx
 *
 */
public enum CapitalChange_Type {

	支出(0), 收入(1), 充值(2), 冻结(3), 解冻(4), 冻结金额增加(5), 冻结金额扣除(6), 余额转入保证金(7), 后台管理添加(8),保证金充值(9),退款扣除(12),
	退款返还(11);
	private Integer value;

	public Integer getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	CapitalChange_Type(Integer value) {
		this.value = value;
	}

}
