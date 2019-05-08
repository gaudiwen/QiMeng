package com.cndatacom.qmhz.activity;
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

}
