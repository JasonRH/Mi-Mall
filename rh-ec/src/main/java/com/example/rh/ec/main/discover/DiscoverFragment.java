package com.example.rh.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.rh.core.fragment.bottom.BottomItemFragment;
import com.example.rh.core.fragment.web.WebFragmentImpl;
import com.example.rh.ec.R;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author RH
 * @date 2018/10/24
 */
public class DiscoverFragment extends BottomItemFragment {
    @Override
    protected Object setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebFragmentImpl fragment = WebFragmentImpl.create("index.html");
        //获取的是EcBottomFragment
        fragment.setTopFragment(getMyParentFragment());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, fragment);
    }

    /**
     * Fragment切换动画
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
