package com.yinlian.Enums;

/** 店铺状态 */
public enum ShopStatusEnum {
    /** 审核 */
    Checking(0),
    /** 审核通过 */
    CheckPass(1),
    /** 审核不通过 */
    CheckNoPass(2),
    /** 违规 */
    Violation(3),
    /** 营业 */
    Open(4),
    /** 打烊 */
    Close(5),
    /** 删除 */
    Delete(6);
    private final int value;

    public int getValue() {
        return value;
    }

    // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
    ShopStatusEnum(int value) {
        this.value = value;
    }

    public static String getName(int value) {
        String name = "";
        switch (value) {
            case 0:
                name = "审核中";
                break;

            case 1:
                name = "审核通过";
                break;
            case 2:
                name = "审核不通过";
                break;
            case 3:
                name = "违规";
                break;
            case 4:
                name = "营业";
                break;

            case 5:
                name = "打烊";
                break;
            case 6:
                name = "删除";
                break;
        }
        return name;
    }

}
