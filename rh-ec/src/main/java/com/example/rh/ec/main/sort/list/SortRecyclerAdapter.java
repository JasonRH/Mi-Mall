package com.example.rh.ec.main.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.core.ui.recycler.ItemType;
import com.example.rh.core.ui.recycler.MultipleFields;
import com.example.rh.core.ui.recycler.MultipleItemEntity;
import com.example.rh.core.ui.recycler.MultipleRecyclerAdapter;
import com.example.rh.core.ui.recycler.MultipleViewHolder;
import com.example.rh.ec.R;
import com.example.rh.ec.main.sort.SortFragment;
import com.example.rh.ec.main.sort.content.ContentFragment;

import java.util.List;

/**
 * @author RH
 * @date 2018/10/30
 */
public class SortRecyclerAdapter extends MultipleRecyclerAdapter {

    private final SortFragment sortFragment;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortFragment sortFragment) {
        super(data);
        this.sortFragment = sortFragment;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = entity.getField(MultipleFields.TEXT);
                final boolean isClicked = entity.getField(MultipleFields.TAG);
                final AppCompatTextView name = holder.getView(R.id.item_vertical_item_name);
                final View line = holder.getView(R.id.item_view_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int mCurrentPosition = holder.getAdapterPosition();
                        if (mPrePosition != mCurrentPosition) {
                            //还原前一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            //更新前一个Item
                            notifyItemChanged(mPrePosition);

                            //更新选中的item
                            entity.setField(MultipleFields.TAG, true);
                            notifyItemChanged(mCurrentPosition);
                            mPrePosition = mCurrentPosition;

                            final int contentId = getData().get(mCurrentPosition).getField(MultipleFields.ID);
                            //显示具体分类详情页面
                            showContent(contentId);
                        }

                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }

                holder.setText(R.id.item_vertical_item_name, text);
                break;

            default:
                break;
        }
    }


    private void showContent(int contentId) {
        final ContentFragment contentFragment = ContentFragment.newInstance(contentId);
        switchContent(contentFragment);
    }

    private void switchContent(ContentFragment fragment) {
        final BaseAppFragment contentFragment = sortFragment.findChildFragment(ContentFragment.class);
        if (contentFragment != null) {
            //不加入返回栈
            contentFragment.replaceFragment(fragment, false);
        }
    }
}
