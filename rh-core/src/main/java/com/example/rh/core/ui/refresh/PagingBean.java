package com.example.rh.core.ui.refresh;

/**
 * @author RH
 * @date 2018/10/29
 */
public final class PagingBean {
    /**
     * 当前是第几页
     */
    private int mPageIndex = 0;
    /**
     * 总数居条数
     */
    private int mTotal = 0;
    /**
     * 一页显示几条数据
     */
    private int mPageSize = 0;
    /**
     * 当前已经显示了几条数据
     */
    private int mCurrentCount = 0;
    /**
     * 加载延迟
     */
    private int mDelayed = 0;


    PagingBean addIndex() {
        mPageIndex++;
        return this;
    }

    public int getmPageIndex() {
        return mPageIndex;
    }

    public PagingBean setmPageIndex(int mPageIndex) {
        this.mPageIndex = mPageIndex;
        return this;
    }

    public int getmTotal() {
        return mTotal;
    }

    public PagingBean setmTotal(int mTotal) {
        this.mTotal = mTotal;
        return this;
    }

    public int getmPageSize() {
        return mPageSize;
    }

    public PagingBean setmPageSize(int mPageSize) {
        this.mPageSize = mPageSize;
        return this;
    }

    public int getmCurrentCount() {
        return mCurrentCount;
    }

    public PagingBean setmCurrentCount(int mCurrentCount) {
        this.mCurrentCount = mCurrentCount;
        return this;
    }

    public int getmDelayed() {
        return mDelayed;
    }

    public PagingBean setmDelayed(int mDelayed) {
        this.mDelayed = mDelayed;
        return this;
    }
}
