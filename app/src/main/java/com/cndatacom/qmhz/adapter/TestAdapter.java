package com.cndatacom.qmhz.adapter;

import android.content.Context;
import android.text.TextUtils;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.holder.RecyclerHolder;
import com.cndatacom.qmhz.bean.TestDataBean;

import java.util.List;


public class TestAdapter extends CommonRecyclerAdapter<TestDataBean> {

    public TestAdapter(Context context, List<TestDataBean> list) {
        super(context, R.layout.item_main_title_layout, list);
    }


    @Override
    public void convert(RecyclerHolder holder, TestDataBean item, int position) {

        //标题
        String title = item.getTitle();
        holder.setText(R.id.tv_main_title,title);
        //图片地址
        String url = item.getUrl();
        holder.setText(R.id.tv_main_content,url);

    }
}