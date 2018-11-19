package com.example.rh.ec.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rh.core.fragment.BaseAppFragment;
import com.example.rh.ui.recycler.ItemType;
import com.example.rh.ui.recycler.MultipleFields;
import com.example.rh.ui.recycler.MultipleItemEntity;
import com.example.rh.ec.R;
import com.example.rh.ec.R2;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @author RH
 * @date 2018/11/15
 */
public class ImageFragment extends BaseAppFragment {

    @BindView(R2.id.rv_image_container)
    RecyclerView mRecyclerView = null;

    private static final String ARG_PICTURES = "ARG_PICTURES";

    @Override
    public Object setLayout() {
        return R.layout.fragment_image;
    }

    private void initImages() {
        final ArrayList<String> pictures =
                getArguments().getStringArrayList(ARG_PICTURES);
        final ArrayList<MultipleItemEntity> entities = new ArrayList<>();
        final int size;
        if (pictures != null) {
            size = pictures.size();
            for (int i = 0; i < size; i++) {
                final String imageUrl = pictures.get(i);
                final MultipleItemEntity entity = MultipleItemEntity
                        .builder()
                        .setItemType(ItemType.SINGLE_BIG_IMAGE)
                        .setField(MultipleFields.IMAGE_URL, imageUrl)
                        .build();
                entities.add(entity);
            }
            final RecyclerImageAdapter adapter = new RecyclerImageAdapter(entities);
            mRecyclerView.setAdapter(adapter);
        }
    }

    public static ImageFragment create(ArrayList<String> pictures) {
        final Bundle bundle = new Bundle();
        bundle.putStringArrayList(ARG_PICTURES, pictures);
        final ImageFragment fragment = new ImageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        initImages();
    }
}
