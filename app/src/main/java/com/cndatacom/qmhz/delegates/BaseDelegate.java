package com.cndatacom.qmhz.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cndatacom.qmhz.utils.LogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseDelegate extends SupportFragment {

    private Unbinder mBind;
    private View mRootView;
    protected CompositeDisposable mDisposable;


    protected abstract Object setLayout();

    protected abstract void initData(Bundle arguments);

    protected abstract void init();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(setLayout() instanceof  Integer){
            mRootView = inflater.inflate((Integer)setLayout(),container,false);
        }else if(setLayout() instanceof  View){
            mRootView = (View)setLayout();
        }else {
            throw  new RuntimeException("setLayout must be View or int");
        }
        if(mRootView != null){
            mBind = ButterKnife.bind(this, mRootView);
            setHasOptionsMenu(true);
//            onBindView(savedInstanceState,rootView);
        }else {
            throw new IllegalArgumentException("can’t find layout");
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDisposable = new CompositeDisposable();
        initData(getArguments());
        LogUtils.d("baseDelegate",this.getClass().getName());
        init();
    }

    protected View getRootView(){
        return mRootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mBind != null){
            mBind.unbind();
        }
        if(mDisposable!=null){
            mDisposable.dispose();
            mDisposable.clear();
        }
    }


    @Override
    public void onSupportInvisible() {
        super.onSupportInvisible();
    }

}
