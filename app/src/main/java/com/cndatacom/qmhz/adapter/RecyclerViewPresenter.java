package com.cndatacom.qmhz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.listener.OnItemClickListener;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.mode.OpenPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试.
 * Created by hailongqiu on 2016/8/24.
 */
public class RecyclerViewPresenter extends OpenPresenter {

    private final List<String> labels;
    private GeneralAdapter mAdapter;
    private OnItemClickListener itemClickListener;
    private int mPosition;

    public RecyclerViewPresenter(int count) {
        this.labels = new ArrayList<String>(count);
        for (int i = 0; i < count; i++) {
            labels.add(String.valueOf(i));
        }
    }

    @Override
    public void setAdapter(GeneralAdapter adapter) {
        this.mAdapter = adapter;
    }

    public void  setItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }

    /**
     * 用于数据加载更多测试.
     */
    public void addDatas(int count) {
        int sum = labels.size();
        for (int i = sum; i < sum + count; i++) {
            labels.add(String.valueOf(i));
        }
        this.mAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bg_view, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
//        GridViewHolder gridViewHolder = (GridViewHolder) viewHolder;
//        TextView textView = (TextView) gridViewHolder.tv1;
//        textView.setText("item " + labels.get(position));
        mPosition = position;
    }

    public class GridViewHolder extends OpenPresenter.ViewHolder {

        public TextView tv1;
        public TextView tv2;
        public TextView tv3;

        public GridViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.textView1);
            tv2 = (TextView)itemView.findViewById(R.id.textView2);
            tv3 = (TextView)itemView.findViewById(R.id.textView3);

            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(tv1,mPosition);
                }
            });
            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(tv3,mPosition);
                }
            });
            tv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(tv3,mPosition);
                }
            });
        }

    }
}
