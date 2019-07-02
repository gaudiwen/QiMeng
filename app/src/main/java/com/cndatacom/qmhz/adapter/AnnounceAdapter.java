package com.cndatacom.qmhz.adapter;


import android.content.Context;
import android.widget.TextView;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.holder.RecyclerHolder;
import com.cndatacom.qmhz.bean.AnnounceBean;

import java.util.List;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/6/26 16:35.
 */
public class AnnounceAdapter extends CommonRecyclerAdapter<AnnounceBean>{

    int itemTextSize;

    public AnnounceAdapter(Context context, int itemLayoutId, List<AnnounceBean> list) {
        super(context, itemLayoutId, list);
    }

    public AnnounceAdapter(Context context, List<AnnounceBean> list,int itemTextSize) {
        super(context, R.layout.item_announce_recycler, list);
        this.itemTextSize = itemTextSize;
    }



    @Override
    public void convert(RecyclerHolder holder, AnnounceBean item, int position) {

        //标题
//        String title = item.getTitle();
//        holder.setText(R.id.tv_main_title,title);

        TextView tv1 = holder.getView(R.id.tv_main_content);
        if(itemTextSize!=0){
            tv1.setTextSize(itemTextSize);
        }
        //图片地址
        String url = item.getUrl();
        holder.setText(R.id.tv_main_content,url);

    }
}
