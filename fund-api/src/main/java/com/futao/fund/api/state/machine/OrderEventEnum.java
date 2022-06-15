package com.futao.fund.api.state.machine;

/**
 * @author futaosmile@gmail.com
 * @date 2022/6/15
 */
public enum OrderEventEnum {
    /**
     * 下单
     */
    Order,
    /**
     * 支付
     */
    PAY,
    /**
     * 发货
     */
    Ship,
    /**
     * 收货
     */
    Receipt
}
