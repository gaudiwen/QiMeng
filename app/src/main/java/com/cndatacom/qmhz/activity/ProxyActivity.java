package com.cndatacom.qmhz.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.delegates.PlaneDelegate;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {

    public abstract PlaneDelegate setRootDelegate();

    public abstract void  init();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initContainer(savedInstanceState);
    }

    private void initContainer(Bundle savedInstanceState) {
       ContentFrameLayout container =  new ContentFrameLayout(this);
       container.setId(R.id.delegate_container);
       setContentView(container);
       if(savedInstanceState == null){
           loadRootFragment(R.id.delegate_container,setRootDelegate());
       }
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();

    }
}
