package com.example.rh.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.net.RetrofitClient;
import com.example.rh.core.net.callback.IError;
import com.example.rh.core.net.callback.IFailure;
import com.example.rh.core.net.callback.ISuccess;
import com.example.rh.core.utils.log.MyLogger;
import com.example.rh.core.wechat.MyWeChat;
import com.example.rh.core.wechat.callbacks.IWeChatSignInCallback;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author RH
 * @date 2018/10/19
 * <p>
 * 登录
 */
public class SignInFragment extends BaseAppFragment {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    protected Object setLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    /**
     * 登录
     */
    @OnClick(R2.id.btn_sign_in)
    void onClickSignUp() {
        if (checkForm()) {
            RetrofitClient.builder()
                    //本地测试url，调用本地json文件
                    //.url("myTest/user.json")
                    //远程服务器url
                    .url("user.json")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            MyLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            Toast.makeText(getContext(), "登录失败,请检查网络设置", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.icon_sign_in_weChat)
    void onClickWeChat() {
        MyWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {
                //微信登录成功后返回用户数据
            }
        }).signIn();
    }

    @OnClick(R2.id.tv_sign_up_link)
    void onClickLink() {
        startWithPop(new SignUpFragment());
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;
        /*邮箱*/
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }
        /*密码*/
        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("密码至少6位数");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;
    }
}
