package com.cndatacom.qmhz.activity;
import android.view.KeyEvent;

import com.cndatacom.qmhz.delegates.MainDelegate;
import com.cndatacom.qmhz.delegates.PlaneDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public PlaneDelegate setRootDelegate() {
        return MainDelegate.newInstance();
    }

    @Override
    public void init() {

    }

   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        getTopFragment().onKeyDown(keyCode,event);
        return super.onKeyDown(keyCode, event);
    }*/
}
