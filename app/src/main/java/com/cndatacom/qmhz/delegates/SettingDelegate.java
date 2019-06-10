package com.cndatacom.qmhz.delegates;

import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.LeftMenuPresenter;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/5/23 10:46.
 */
public class SettingDelegate extends  PlaneDelegate{


    private Context mContext;
    private RecyclerViewTV left_menu_rv; // 左侧菜单.
    private MainUpView mainUpView1;
    private RecyclerViewBridge mRecyclerViewBridge;
    private View oldView;

    public static SettingDelegate newInstance() {

        Bundle args = new Bundle();
        SettingDelegate fragment = new SettingDelegate();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected Object setLayout() {
        return R.layout.delegate_setting;
    }

    @Override
    protected void initData(Bundle arguments) {

    }


    @Override
    protected void init() {

        initviews();
    }

    private void initviews() {

        left_menu_rv = (RecyclerViewTV)(getRootView()).findViewById(R.id.left_menu_rv);
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
        // 初始化左侧菜单.
        initLeftMenu();

       loadRootFragment(R.id.framelayout, BackgroundDelegate.newInstance(),false,false);
    }

    private void initLeftMenu() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        left_menu_rv.setLayoutManager(layoutManager);
        left_menu_rv.setFocusable(false);
        GeneralAdapter generalAdapter = new GeneralAdapter(new LeftMenuPresenter());
        left_menu_rv.setAdapter(generalAdapter);
        left_menu_rv.setOnItemListener(new RecyclerViewTV.OnItemListener() {
            @Override
            public void onItemPreSelected(RecyclerViewTV parent, View itemView, int position) {
                // 传入 itemView也可以, 自己保存的 oldView也可以.
                mRecyclerViewBridge.setUnFocusView(itemView);
            }
            @Override
            public void onItemSelected(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.2f);
                oldView = itemView;
            }

            @Override
            public void onReviseFocusFollow(RecyclerViewTV parent, View itemView, int position) {
                mRecyclerViewBridge.setFocusView(itemView, 1.0f);
                oldView = itemView;
            }
        });
        left_menu_rv.setOnItemClickListener(new RecyclerViewTV.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerViewTV parent, View itemView, int position) {
                // 测试.
                mRecyclerViewBridge.setFocusView(itemView, oldView, 1.0f);
                oldView = itemView;
                //
                onViewItemClick(itemView, position);
            }
        });
    }


    public float getDimension(int id) {
        return getResources().getDimension(id);
    }

    // 左边侧边栏的单击事件.
    private void onViewItemClick(View v, int pos) {
        switch (pos) {
            case 0:
                switchContentFragment(BackgroundDelegate.newInstance());
                break;
            case 1:
                switchContentFragment(MainDelegate.newInstance());
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    /**
     * 替换加载 内容Fragment
     *
     * @param
     */
    public void switchContentFragment(SupportFragment fragment) {

        SupportFragment topFragment = (SupportFragment) getTopChildFragment();
   //     SupportFragment contentFragment = findChildFragment(topFragment);
//        if (contentFragment != null) {
//        }
        topFragment.replaceFragment(fragment, false);
    }

}
