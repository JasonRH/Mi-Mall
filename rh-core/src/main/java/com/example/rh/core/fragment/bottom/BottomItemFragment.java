package com.example.rh.core.fragment.bottom;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh_core.R;

/**
 * @author RH
 * @date 2018/10/24
 */
public abstract class BottomItemFragment extends BaseAppFragment implements View.OnKeyListener {
    private long touchTime = 0;
    private final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - touchTime) > EXIT_TIME) {
                touchTime = System.currentTimeMillis();
                Toast.makeText(getContext(), "双击退出" + getString(R.string.app_name), Toast.LENGTH_SHORT).show();
            } else {
                _mActivity.finish();
                if (touchTime != 0) {
                    touchTime = 0;
                }
            }
            return true;
        }
        return false;
    }
}
