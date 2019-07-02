package com.cndatacom.qmhz.activity;

import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.LeftMenuPresenter;
import com.cndatacom.qmhz.delegates.BackgroundDelegate;
import com.cndatacom.qmhz.delegates.MainDelegate;
import com.cndatacom.qmhz.delegates.PlaneDelegate;
import com.cndatacom.qmhz.delegates.SettingDelegate;
import com.open.androidtvwidget.bridge.RecyclerViewBridge;
import com.open.androidtvwidget.leanback.adapter.GeneralAdapter;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.MainUpView;

/**
  * @date:  2019/05/22
  * @author: GaudiWen
  */
public class SettingActivity extends ProxyActivity {

     @Override
     public PlaneDelegate setRootDelegate() {
        return SettingDelegate.newInstance();
     }

     @Override
     public void init() {

     }

 }
