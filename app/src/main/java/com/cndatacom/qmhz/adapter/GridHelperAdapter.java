package com.cndatacom.qmhz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.utils.LogUtils;
import com.cndatacom.qmhz.view.DisplayUtils;

import java.util.List;


public class GridHelperAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private int itemMargin;
    private Context context;
    private LayoutHelper mHelper;
    private List<Integer> mData;
    private int mSetviewtyle;

    public GridHelperAdapter(List<Integer> mData, LayoutHelper helper,Context context,int setViewtyle) {
        this.mData = mData;
        this.mHelper=helper;
        this.context=context;
        this.mSetviewtyle =setViewtyle;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grid_layout, parent, false);
        return new RecyclerViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerViewItemHolder recyclerViewHolder = (RecyclerViewItemHolder) holder;
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        int imageCount =mSetviewtyle;
        int width = 0, height = 0;
        int displayW = DisplayUtils.getDisplayWidth(context);
        LogUtils.e("displayW==="+displayW);
        /*if (imageCount == 1) {
            width = displayW;
            height = width;
        } else if (imageCount == 2) {
            width = displayW / 2;
            height = width;
        } else if (imageCount == 3) {
            if (position == 0) {
                width = (int) (displayW * 0.66);
                height = width;
                layoutParams.rightMargin = itemMargin;
                layoutParams.bottomMargin = itemMargin;
            } else {
                if (position == 1 || position == 2) {
                    if (position == 1) {
                        layoutParams.bottomMargin = itemMargin / 2;
                    } else {
                        layoutParams.bottomMargin = itemMargin;
                    }
                }
                width = (int) ((displayW * 0.33));
                height = width;
            }
        } else if (imageCount == 4) {
            if (position == 0) {
                width = displayW;
                height = (int) (width * 0.5);
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 5) {
            if (position == 0 || position == 1) {
                width = (int) (displayW * 0.5);
                height = width;
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 6) {
            if (position == 0) {
                width = (int) (displayW * 0.66);
                height = width;
                layoutParams.rightMargin = 10;
                layoutParams.bottomMargin = 10;
            } else {
                if (position == 1 || position == 2) {
                    if (position == 1) {
                        layoutParams.bottomMargin = itemMargin / 2;
                    } else {
                        layoutParams.bottomMargin = itemMargin;
                    }

                }
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 7) {
            if (position == 0) {
                width = displayW;
                height = (int) (width * 0.5);
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else if (imageCount == 8) {
            if (position == 0 || position == 1) {
                width = (int) (displayW * 0.5);
                height = width;
            } else {
                width = (int) (displayW * 0.33);
                height = width;
            }
        } else {
            width = (int) (displayW * 0.33);
            height = width;
        }*/
        switch (imageCount){
            case 5: width = 200;
                height = 200;
                break;
            case 4: width = 300;
                height = 300;break;
            case 3:
                width = 400;
                height = 400;break;
            case 2: break;
            case 1: break;
            case 0: break;
        }
        LogUtils.e("imageCount(mSetviewtyle)=="+mSetviewtyle);

        layoutParams.width = width;
        layoutParams.height = height;
        recyclerViewHolder.itemView.setLayoutParams(layoutParams);

        recyclerViewHolder.iv_icon.setBackgroundResource(mData.get(position));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * 正常条目的item的ViewHolder
     */
    private class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

        public ImageView iv_icon;

        public RecyclerViewItemHolder(View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_grid);
        }
    }
}
