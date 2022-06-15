package com.futao.fund.api.state.machine;

/**
 * @author futaosmile@gmail.com
 * @date 2022/6/15
 */
public enum OrderStateEnum {
    /**
     * 待支付
     */
    ToBePaid,
    /**
     * 待发货
     */
    ToBeDelivered,
    /**
     * 待收货
     */
    PendingReceipt,
    /**
     * 已完成
     */
    Completed
}
