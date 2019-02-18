package com.yinlian.Enums;

/**
 * 系统配置类型
 * 
 * @author mjx
 *
 */
public enum ConfigSetTypeEnum {

    /**
     * 所有订单系统自动确认收货间隔天数（单位：天）
     */
    收货间隔天数(0),
    /**
     * 所有订单系统自动确认收货执行时间
     */
    收货执行时间(1),
    /**
     * 商家可经营的商品种类
     */
    经营范围(2),
    /**
     * 店铺最大员工个数
     */
    员工个数(3),
    /**
     * 店铺最多角色个数
     */
    角色个数(4),
    /**
     * 店铺保证金
     */
    店铺保证金(5),
    /**
     * 佣金返利比率
     */
    佣金返利(6),

    /**
     * 积分兑换人民币
     */
    积分兑换人民币(7),

    每人每天抽奖次数(8),

    每人每天中奖次数(9);

    private final int value;

    public int getValue() {
        return value;
    }

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ConfigSetTypeEnum(int value) {
        this.value = value;
    }
}
