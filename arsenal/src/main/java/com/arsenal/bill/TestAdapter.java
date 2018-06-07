package com.arsenal.bill;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class TestAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public TestAdapter(List<MultiItemEntity> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        if (helper instanceof BaseVH) {
            ((BaseVH) helper).setData(item);
        }
    }
}
