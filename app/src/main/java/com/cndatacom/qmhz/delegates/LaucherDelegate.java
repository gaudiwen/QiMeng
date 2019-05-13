package com.cndatacom.qmhz.delegates;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.GridHelperAdapter;
import com.cndatacom.qmhz.utils.LogUtils;
import com.cndatacom.qmhz.utils.ToastUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/5/6 10:25.
 */
public class LaucherDelegate extends PlaneDelegate {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private ArrayList<Integer> imgSrc = new ArrayList<>();
    DelegateAdapter adapters;
    GridHelperAdapter gridHelperAdapter;
    GridLayoutHelper gridHelper;

    public static LaucherDelegate newInstance() {

        Bundle args = new Bundle();

        LaucherDelegate fragment = new LaucherDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.delegate_laucher;
    }

    @Override
    protected void initData(Bundle arguments) {

        getDensity();
    }
    private float getDensity() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        LogUtils.e("dpi=="+dm.density);
        return dm.density;
    }


    @Override
    protected void init() {

        initGridData();
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(_mActivity);

        recyclerview.setLayoutManager(layoutManager);

         adapters = new DelegateAdapter(layoutManager, true);
        // 进行Grid布局
        gridHelper = new GridLayoutHelper(3,5);
        gridHelper.setMarginTop(30);
//        gridHelper.setWeights(new float[]{20.0f,20.0f,20.0f,20.0f,20.0f});
        //设置垂直方向条目的间隔
        gridHelper.setVGap(4);
        //设置水平方向条目的间隔
        gridHelper.setHGap(5);
        gridHelper.setMarginLeft(30);
        gridHelper.setMarginBottom(30);
        //自动填充满布局
        gridHelper.setAutoExpand(true);

         gridHelperAdapter = new GridHelperAdapter(imgSrc, gridHelper,this._mActivity,5);
        adapters.addAdapter(gridHelperAdapter);

        recyclerview.setAdapter(adapters);

    }

    private void initGridData() {

        for (int i=0;i<5;i++){
            imgSrc.add(R.mipmap.grid_view_item_test);
        }
    }

    int i =5;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 8:

                adapters.removeAdapter(gridHelperAdapter);
                gridHelperAdapter = new GridHelperAdapter(imgSrc, gridHelper,this._mActivity,i);
                adapters.addAdapter(gridHelperAdapter);
                recyclerview.setAdapter(adapters);
                i--;
                if(i== 0){i=5;}
                return true;
            case 9:
                return true;
            case 10:
                return true;
        }
        return false;
    }

}
