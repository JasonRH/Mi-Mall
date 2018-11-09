package com.example.rh.ec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.ui.date.DateDialogUtil;
import com.example.rh.core.utils.callback.CallbackManager;
import com.example.rh.core.utils.callback.CallbackType;
import com.example.rh.core.utils.callback.IGlobalCallback;
import com.example.rh.core.utils.log.MyLogger;
import com.example.rh.ec.R;
import com.example.rh.ec.main.personal.list.ListBean;

import java.util.Objects;

/**
 * @author RH
 * @date 2018/11/7
 */
public class UserProfileClickListener extends SimpleClickListener {

    private final BaseAppFragment fragment;
    private String[] mGender = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(BaseAppFragment fragment) {
        this.fragment = fragment;
    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
                //开启图片剪裁回调
                CallbackManager.getInstance().addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(Uri args) {
                        MyLogger.d("ON_CROP", args);
                        //更新本地头像
                        ImageView avatar = view.findViewById(R.id.img_arrow_avatar);
                        Glide.with(fragment).load(args).into(avatar);
                        //将新头像上传给服务器
                        /*RetrofitClient.builder()
                                .url("上传地址")
                                .loader(fragment.getContext())
                                .file(args.getPath())
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        Toast.makeText(fragment.getContext(), "上传成功" + response, Toast.LENGTH_SHORT).show();
                                        final String path = JSON.parseObject(response).getJSONObject("result").getString("path");
                                        //通知服务器更新信息
                                        RetrofitClient.builder()
                                                .url("user.json")
                                                .params("avatar", path)
                                                .loader(fragment.getContext())
                                                .success(new ISuccess() {
                                                    @Override
                                                    public void onSuccess(String response) {
                                                        //获取更新后的用户信息，然后更新本地数据库
                                                        //没有本地数据的APP，每次打开APP都请求API，获取信息
                                                    }
                                                })
                                                .build()
                                                .post();

                                    }
                                })
                                .failure(new IFailure() {
                                    @Override
                                    public void onFailure() {
                                        Toast.makeText(fragment.getContext(), "上传失败", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .build()
                                .upload();*/
                    }
                });
                //开启照相机或选择图片
                fragment.startCameraWithCheck();
                break;
            case 2:
                //设置name
                final BaseAppFragment nameFragment = bean.getmFragment();
                fragment.getSupportDelegate().start(nameFragment);
                break;
            case 3:
                //设置性别
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final TextView textView = view.findViewById(R.id.tv_arrow_value);
                        textView.setText(mGender[which]);
                        dialog.cancel();
                    }
                });
                break;
            case 4:
                DateDialogUtil dateDialogUtil = new DateDialogUtil();
                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
                    @Override
                    public void onDateChange(String date) {
                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
                        textView.setText(date);
                    }
                });
                dateDialogUtil.showDialog(fragment.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(fragment.getContext()));
        builder.setSingleChoiceItems(mGender, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
