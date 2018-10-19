package com.example.rh.ec.sign;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author RH
 * @date 2018/10/19
 */
public class SignInFragment extends BaseAppFragment {
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @Override
    protected Object setLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSignUp() {
        if (checkForm()) {
            /*RetrofitClient.builder()
                    .url("")
                    .params("","")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .build()
                    .post();*/
            Toast.makeText(getContext(), "验证通过", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R2.id.icon_sign_in_weChat)
    void onClickWeChat() {
    }

    @OnClick(R2.id.tv_sign_up_link)
    void onClickLink() {
        start(new SignUpFragment());
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
