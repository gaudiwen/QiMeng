package com.cndatacom.qmhz.delegates;

import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.RecyclerViewPresenter;
import com.cndatacom.qmhz.listener.OnItemClickListener;
import com.cndatacom.qmhz.utils.ToastUtil;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.GridLayoutManagerTV;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

import java.util.HashMap;

import butterknife.BindView;

import static android.view.KeyEvent.KEYCODE_DPAD_LEFT;
import static android.view.KeyEvent.KEYCODE_DPAD_RIGHT;
import static android.view.ViewGroup.FOCUS_AFTER_DESCENDANTS;
import static android.view.ViewGroup.FOCUS_BEFORE_DESCENDANTS;


/**
 * 描述: 背景设置页
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/5/22 10:25.
 */
public class BackgroundDelegate extends PlaneDelegate implements OnItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerViewTV recyclerView;

    private MainUpView mainUpView1;
    private HashMap<String, Object> loginMap = new HashMap<>();
    private RecyclerViewPresenter recyclerViewPresenter;
    private GeneralAdapter mGeneralAdapter;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;


    public static BackgroundDelegate newInstance() {

        Bundle args = new Bundle();
        BackgroundDelegate fragment = new BackgroundDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_background;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void init() {

        //mainUpView1 = (MainUpView)(getRootView()).findViewById(R.id.mainUpView1);
        mainUpView1 = new MainUpView(_mActivity);
        mainUpView1.attach2Window(_mActivity);
        mainUpView1.setEffectBridge(new RecyclerViewBridge());
        // 注意这里，需要使用 RecyclerViewBridge 的移动边框 Bridge.
        mRecyclerViewBridge = (RecyclerViewBridge) mainUpView1.getEffectBridge();
        // mRecyclerViewBridge.setUpRectResource(R.drawable.ic_sp_block_focus);
        float density = getResources().getDisplayMetrics().density;
        RectF receF = new RectF(getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density,
                getDimension(R.dimen.w_45) * density, getDimension(R.dimen.h_40) * density);
        mRecyclerViewBridge.setDrawUpRectPadding(receF);

//        mainUpView1 = new MainUpView(_mActivity);
//        mainUpView1.attach2Window(_mActivity);
//        // 默认是 OpenEff...，建议使用 NoDraw... ...
//        mainUpView1.setEffectBridge(new EffectNoDrawBridge());
//        EffectNoDrawBridge bridget = (EffectNoDrawBridge) mainUpView1.getEffectBridge();
//        bridget.setTranDurAnimTime(200);
//        //
//        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
//        mainUpView1.setDrawUpRectPadding(new Rect(25, 25, 23, 23)); // 边框图片设置间距.

        testRecyclerViewGridLayout(GridLayoutManager.VERTICAL);
    }

    /**
     * 背景GridLayout.
     */
    private void testRecyclerViewGridLayout(int orientation) {
        GridLayoutManagerTV gridlayoutManager = new GridLayoutManagerTV(_mActivity, 4); // 解决快速长按焦点丢失问题.
        gridlayoutManager.setOrientation(orientation);
        recyclerView.setLayoutManager(gridlayoutManager);
        recyclerView.setFocusable(false);
        recyclerView.setSelectedItemAtCentered(true); // 设置item在中间移动.
        recyclerViewPresenter = new RecyclerViewPresenter(25);
        recyclerViewPresenter.setItemClickListener(this);
        mGeneralAdapter = new GeneralAdapter(recyclerViewPresenter);
        recyclerView.setAdapter(mGeneralAdapter);
        recyclerView.setPagingableListener(new RecyclerViewTV.PagingableListener() {
            @Override
            public void onLoadMoreItems() {
                // 加载更多测试.
            }
        });
        recyclerView.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                TextView tv1 = itemView.findViewById(R.id.textView1);
                tv1.requestFocus();

            }
        });
        recyclerView.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {

                if (itemView.findViewById(R.id.textView1).isFocused()) {
                } else {
                    FrameLayout framelayout_setting = itemView.findViewById(R.id.framelayout_setting);
                    framelayout_setting.setVisibility(View.GONE);
                }
            }

            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {

                FrameLayout framelayout_setting = itemView.findViewById(R.id.framelayout_setting);
                framelayout_setting.setVisibility(View.VISIBLE);

                FrameLayout framelayout_root = itemView.findViewById(R.id.framelayout_root);
             //   framelayout_root.setNextFocusDownId(R.id.textView1);
             //   framelayout_root.setNextFocusUpId(R.id.textView1);

                TextView tv1 = itemView.findViewById(R.id.textView1);
                TextView tv2 = itemView.findViewById(R.id.textView2);
                TextView tv3 = itemView.findViewById(R.id.textView3);

               // tv1.requestFocus();

                tv1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {

                        if (v.getId() == R.id.textView1 && hasFocus) {

                            mRecyclerViewBridge.setFocusView(v, 1.2f);
                            oldView = v;
                        } else {

                            mRecyclerViewBridge.setUnFocusView(oldView);

                            if(!tv2.isFocused()){
                                FrameLayout framelayout_setting = itemView.findViewById(R.id.framelayout_setting);
                                framelayout_setting.setVisibility(View.GONE);
                            }
                        }
                    }
                });
                tv2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {

                        if (v.getId() == R.id.textView2 && hasFocus) {

                            mRecyclerViewBridge.setFocusView(v, 1.2f);
                            oldView = v;
                        } else {

                            mRecyclerViewBridge.setUnFocusView(oldView);

                            if(tv3.isFocused()||tv1.isFocused()){
                            }else {
                                FrameLayout framelayout_setting = itemView.findViewById(R.id.framelayout_setting);
                                framelayout_setting.setVisibility(View.GONE);
                            }
                        }
                    }
                });
                tv3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {

                        if (v.getId() == R.id.textView3 && hasFocus) {

                            mRecyclerViewBridge.setFocusView(v, 1.2f);
                            oldView = v;

                        } else {

                            mRecyclerViewBridge.setUnFocusView(oldView);

                            if(!tv2.isFocused()){
                                FrameLayout framelayout_setting = itemView.findViewById(R.id.framelayout_setting);
                                framelayout_setting.setVisibility(View.GONE);
                            }
                        }
                    }
                });
            }

            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {

            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {

        switch(view.getId()){
            case R.id.textView1:
                break;
            case R.id.textView2:
                break;
            case R.id.textView3:
                break;
        }
    }

    public float getDimension(int id) {
        return getResources().getDimension(id);
    }

}
