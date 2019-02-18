package com.yinlian.Enums;

/**
 * 页面标识
 * 
 * @author mjx
 *
 */
public enum PageMarkType {
	首页(0), 专题页(1), 店铺页(2), 商品列表页(3), 购物车成功页(4), 购物车页(5), 支付成功页(6), 商品详情页(7), 优惠卷领取页(
			8),
	// 订单支付成功页 = 9,
	支付失败页(10), 店铺商品列表页(11), 抢购活动(12), 团购活动(13), 绿色中国首页(14), 绿色中国地方官(15), 发现首页(16),中绿味道(17),
	包邮直送(18),每日鲜(19),秒杀页(20), 闪购页(21), 新品尝鲜(22), 值得购(23), 独家品牌(24),每日鲜PC(25),包邮直送PC(26),
	优惠卷领取页PC(27),app分类广告(28),发现好店(29),店招(30),资讯页(31);
	private final int value;

	public int getValue() {
		return value;
	}

	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	PageMarkType(int value) {
		this.value = value;
	}

	public static String getName(int value) {
		String name = "";
		switch (value) {
		case 0:
			name = "首页";
			break;
		case 1:
			name = "专题页";
			break;
		case 2:
			name = "店铺页";
			break;
		case 3:
			name = "商品列表页";
			break;
		case 4:
			name = "购物车成功页";
			break;
		case 5:
			name = "购物车页";
			break;
		case 6:
			name = "支付成功页";
			break;
		case 7:
			name = "商品详情页";
			break;
		case 8:
			name = "优惠卷领取页";
			break;
		case 10:
			name = "支付失败页";
			break;
		case 11:
			name = "店铺商品列表页";
			break;
		case 12:
			name = "抢购活动";
			break;
		case 13:
			name = "团购活动";
			break;
		case 14:
			name = "绿色中国首页";
			break;
		case 15:
			name = "绿色中国地方官首页";
			break;
		case 16:
			name = "发现首页";
			break;		
		case 17:
			name = "中绿味道";
			break;
		case 18:
			name = "包邮直送";
			break;
		}
		return name;
	}
}
