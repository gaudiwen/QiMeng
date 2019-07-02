package com.cndatacom.qmhz.delegates;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.activity.LaucherActivity;
import com.cndatacom.qmhz.activity.SettingActivity;
import com.cndatacom.qmhz.activity.ViewPagerShowActivity;
import com.cndatacom.qmhz.bean.GoodsTypeBean;
import com.cndatacom.qmhz.bean.LoginBean;
import com.cndatacom.qmhz.network.retrofit.HttpManager;
import com.cndatacom.qmhz.network.rxjava.BaseListResponse;
import com.cndatacom.qmhz.network.rxjava.observable.ResultTransformer;
import com.cndatacom.qmhz.network.rxjava.observer.BaseObserver;
import com.cndatacom.qmhz.view.MarqueeTextView;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.bridge.OpenEffectBridge;
import com.open.androidtvwidget.utils.Utils;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ReflectItemView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/5/6 10:25.
 */
public class MainDelegate extends PlaneDelegate {


    MainUpView mainUpView1;
    View mOldFocus; // 4.3以下版本需要自己保存.

    OpenEffectBridge mOpenEffectBridge;

    @BindView(R.id.tvMarqueeOne)
    MarqueeTextView tvMarqueeOne;
    @BindView(R.id.gridview_lay)
    FrameLayout gridviewLay;
    @BindView(R.id.viewpager_lay)
    ReflectItemView viewpagerLay;

    private HashMap<String, Object> loginMap = new HashMap<>();


    public static MainDelegate newInstance() {

        Bundle args = new Bundle();
        MainDelegate fragment = new MainDelegate();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle arguments) {

    }

    @Override
    protected void init() {

        mainUpView1 = (MainUpView) getRootView().findViewById(R.id.mainUpView1);

        //  mOpenEffectBridge = (OpenEffectBridge) mainUpView1.getEffectBridge();

        if (Utils.getSDKVersion() == 17) { // 测试 android 4.2版本.
            switchNoDrawBridgeVersion();
        } else { // 其它版本（android 4.3以上）.
            mainUpView1.setUpRectResource(R.drawable.test_rectangle); // 设置移动边框的图片.
            //mainUpView1.setShadowResource(R.drawable.item_shadow); // 设置移动边框的阴影.
        }

        FrameMainLayout main_lay11 = (FrameMainLayout) getRootView().findViewById(R.id.main_lay);
        main_lay11.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {

                if (newFocus != null)
                    newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
                float scale = 1.2f;
                mainUpView1.setFocusView(newFocus, mOldFocus, scale);

                mOldFocus = newFocus; // 4.3以下需要自己保存.
                // 测试是否让边框绘制在下面，还是上面. (建议不要使用此函数)
                if (newFocus != null) {
                    //testTopDemo(newFocus, scale);
                }
            }
        });
        // networkTest();
    }

    private void networkTest() {

        loginMap.put("style", "0");
        loginMap.put("userName", "dfty");
        loginMap.put("password", "df0987");

        HttpManager.getService().getLoginToken(loginMap)
                .compose(ResultTransformer.<LoginBean>transformerYI())
                .subscribe(new BaseObserver<LoginBean>(_mActivity, mDisposable) {
                    @Override
                    protected void onSuccess(LoginBean loginBean) {

                    }
                });

        HttpManager.getService().Yd2getTypes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseListResponse<GoodsTypeBean>>(_mActivity, mDisposable) {
                    @Override
                    protected void onSuccess(BaseListResponse<GoodsTypeBean> bean) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    private void switchNoDrawBridgeVersion() {
        float density = getResources().getDisplayMetrics().density;
        RectF rectf = new RectF(getDimension(R.dimen.w_10) * density, getDimension(R.dimen.h_10) * density,
                getDimension(R.dimen.w_9) * density, getDimension(R.dimen.h_9) * density);
        EffectNoDrawBridge effectNoDrawBridge = new EffectNoDrawBridge();
        effectNoDrawBridge.setTranDurAnimTime(200);
//        effectNoDrawBridge.setDrawUpRectPadding(rectf);
        mainUpView1.setEffectBridge(effectNoDrawBridge); // 4.3以下版本边框移动.
        mainUpView1.setUpRectResource(R.drawable.white_light_10); // 设置移动边框的图片.
        mainUpView1.setDrawUpRectPadding(rectf); // 边框图片设置间距.
    }

    public float getDimension(int id) {
        return getResources().getDimension(id);
    }


    @OnClick({R.id.gridview_lay,R.id.viewpager_lay})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.gridview_lay:
                //start(LaucherDelegate2.newInstance(), SupportFragment.SINGLETOP);
                Intent intent = new Intent();
                intent.setClass(getActivity(), LaucherActivity.class);
                startActivity(intent);
                break;
            case R.id.viewpager_lay:
//                Intent intent2 = new Intent();
//                intent2.setClass(getActivity(), SettingActivity.class);
//                startActivity(intent2);

                Intent intent3 = new Intent();
                intent3.setClass(getActivity(), ViewPagerShowActivity.class);
                startActivity(intent3);

                break;
            case R.id.effect_rlay:
//                Intent intent3 = new Intent();
//                intent3.setClass(getActivity(), ViewPagerShowActivity.class);
//                startActivity(intent3);
                break;
        }
    }


}
