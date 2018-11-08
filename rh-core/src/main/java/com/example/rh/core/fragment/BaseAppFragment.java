package com.example.rh.core.fragment;

/**
 * @author RH
 * @date 2018/8/22
 */
public abstract class BaseAppFragment extends PermissionCheckerDelegate {
    /**
     * 得到父布局
     */
    @SuppressWarnings("unchecked")
    public <T extends BaseAppFragment> T getMyParentFragment() {
        return (T) getParentFragment();
    }

}
