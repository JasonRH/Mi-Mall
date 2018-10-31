package com.example.rh.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.rh.core.ui.recycler.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RH
 * @date 2018/10/31
 */
public class SectionDataConverter {

    final List<MySectionEntity> convert(String json) {
        final List<MySectionEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //添加title
            final MySectionEntity sectionTitleEntity = new MySectionEntity(true, title);
            sectionTitleEntity.setId(id);
            sectionTitleEntity.setIsMore(true);
            dataList.add(sectionTitleEntity);

            final JSONArray goods = data.getJSONArray("goods");
            //商品内容循环
            final int goodSize = goods.size();
            for (int j = 0; j < goodSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsName = contentItem.getString("goods_name");
                final String goodsThumb = contentItem.getString("goods_thumb");
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoodsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);

                //添加内容
                dataList.add(new MySectionEntity(itemEntity));

            }
            //商品内容循环结束
        }
        //Section循环结束
        return dataList;
    }
}
