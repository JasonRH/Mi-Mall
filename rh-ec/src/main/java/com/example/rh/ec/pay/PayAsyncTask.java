package com.example.rh.ec.pay;

import android.app.Activity;
import android.os.AsyncTask;

import com.alipay.sdk.app.PayTask;
import com.example.rh.core.ui.loader.MyLoader;
import com.example.rh.core.utils.log.MyLogger;

/**
 * @author RH
 * @date 2018/11/12
 */
public class PayAsyncTask extends AsyncTask<String, Void, String> {

    private final Activity ACTIVITY;
    private final IAlPayResultListener LISTENER;

    /**
     * 订单支付成功
     */
    private static final String AL_PAY_STATUS_SUCCESS = "9000";
    /**
     * 订单处理中
     */
    private static final String AL_PAY_STATUS_PAYING = "8000";
    /**
     * 订单支付失败
     */
    private static final String AL_PAY_STATUS_FAIL = "4000";
    /**
     * 用户取消
     */
    private static final String AL_PAY_STATUS_CANCEL = "6001";
    /**
     * 支付网络错误
     */
    private static final String AL_PAY_STATUS_CONNECT_ERROR = "6002";

    public PayAsyncTask(Activity activity, IAlPayResultListener listener) {
        ACTIVITY = activity;
        LISTENER = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        final String alPaySign = params[0];
        final PayTask payTask = new PayTask(ACTIVITY);
        //异步支付，返回支付结果
        return payTask.pay(alPaySign, true);
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        MyLoader.stopLoading();
        final PayResult payResult = new PayResult(result);
        // 支付宝返回此次支付结构及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
        final String resultInfo = payResult.getResult();
        final String resultStatus = payResult.getResultStatus();
        MyLogger.d("AL_PAY_RESULT", resultInfo);
        MyLogger.d("AL_PAY_RESULT", resultStatus);

        switch (resultStatus) {
            case AL_PAY_STATUS_SUCCESS:
                if (LISTENER != null) {
                    LISTENER.onPaySuccess();
                }
                break;
            case AL_PAY_STATUS_FAIL:
                if (LISTENER != null) {
                    LISTENER.onPayFail();
                }
                break;
            case AL_PAY_STATUS_PAYING:
                if (LISTENER != null) {
                    LISTENER.onPaying();
                }
                break;
            case AL_PAY_STATUS_CANCEL:
                if (LISTENER != null) {
                    LISTENER.onPayCancel();
                }
                break;
            case AL_PAY_STATUS_CONNECT_ERROR:
                if (LISTENER != null) {
                    LISTENER.onPayConnectError();
                }
                break;
            default:
                break;
        }
    }
}
