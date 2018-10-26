package com.example.rh.core.ui.banner;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.rh_core.R;

import java.util.ArrayList;

/**
 * @author RH
 * @date 2018/10/26
 */
public class BannerCreator {
    public static void setDefault(ConvenientBanner<String> convenientBanner,
                                  ArrayList<String> banners,
                                  OnItemClickListener clickListener) {

        convenientBanner
                .setPages(new HolderCreator(), banners)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
               // .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);

    }
}
