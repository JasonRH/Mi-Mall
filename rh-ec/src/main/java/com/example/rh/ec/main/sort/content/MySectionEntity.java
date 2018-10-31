package com.example.rh.ec.main.sort.content;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author RH
 * @date 2018/10/31
 * <p>
 *
 */
public class MySectionEntity extends SectionEntity<SectionContentItemEntity> {

    private boolean isMore = false;
    private int mId = -1;


    public MySectionEntity(SectionContentItemEntity sectionContentItemEntity) {
        super(sectionContentItemEntity);
    }

    public MySectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public boolean getIsMore() {
        return isMore;
    }

    public void setIsMore(boolean more) {
        isMore = more;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }
}
