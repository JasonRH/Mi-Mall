package com.example.rh.ec.pay;

/**
 * @author RH
 * @date 2018/11/12
 */
public interface IAlPayResultListener {
    /**
     * 支付成功
     */
    void onPaySuccess();

    /**
     * 支付中
     */
    void onPaying();

    /**
     * 支付失败
     */
    void onPayFail();

    /**
     * 取消支付
     */
    void onPayCancel();

    /**
     * 支付异常
     */
    void onPayConnectError();
}
