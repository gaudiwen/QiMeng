package com.cndatacom.qmhz.delegates;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyFragment1 extends Fragment {

    private Unbinder unbinder;

    public MyFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_page1, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.page1_item1,R.id.page1_item2})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.page1_item1:
                ToastUtil.getInstance().showNewShort("page1_item1");
                break;
            case R.id.page1_item2:
                ToastUtil.getInstance().showNewShort("page1_item2");
                break;
//            case R.id.listview_lay:
//                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}