package com.cndatacom.qmhz.delegates;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.TestAdapter;
import com.cndatacom.qmhz.bean.LaucherConfigBean;
import com.cndatacom.qmhz.bean.TestDataBean;
import com.cndatacom.qmhz.cache.LocalDataManager;
import com.cndatacom.qmhz.utils.LogUtils;
import com.cndatacom.qmhz.utils.TimeThread;
import com.cndatacom.qmhz.utils.ViewUtils;
import com.cndatacom.qmhz.view.WindowController;
import com.open.androidtvwidget.bridge.EffectNoDrawBridge;
import com.open.androidtvwidget.utils.Utils;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 描述: 描述一下类的作用
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/5/6 10:25.
 */
public class LaucherDelegate2 extends PlaneDelegate {

    @BindView(R.id.tv_showtime)
    TextView tvShowtime;

    private List<TestDataBean> list=null;

    View mOldFocus; // 4.3以下版本需要自己保存.
    private EditText editInfo;
    private EditText passWord;
    private RecyclerView mRecyclerview;
    private MainUpView mainUpView1;
    RelativeLayout mRelativeMainLayout;
    private int bgResId;  //根布局背景
    private ViewGroup mRootMain;//根布局
    private VideoView mVideoView;
    private TextView tvTime; //时间显示
    private FrameMainLayout frameMainLayout;

    public static LaucherDelegate2 newInstance() {

        Bundle args = new Bundle();

        LaucherDelegate2 fragment = new LaucherDelegate2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected Object setLayout() {


        //如果有缓存配置，拿缓存
        /*if (LocalDataManager.getInstance().getLaucherConfigBean() != null) {
            bgResId = LocalDataManager.getInstance().getLaucherConfigBean().getBgResId();
        } else {
            //如果没有，请求网络
            bgResId = R.color.color_aqua;
            LocalDataManager.getInstance().saveLaucherConfigBean(new LaucherConfigBean(bgResId));
        }
        return initUI();*/
        return R.layout.delegate_laucher;
    }

    @Override
    protected void initData(Bundle arguments) {

        initUI();

        initGridData();
        getDensity();
        /*mRootMain.post(new Runnable() {
            @Override
            public void run() {
            }
        });*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                initVideoPlayer("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
            }
        });
    }

    @Override
    protected void init() {

    }

    private float getDensity() {
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        LogUtils.e("dpi==" + dm.density);
        return dm.density;
    }

    private void initGridData() {

        list=new ArrayList<>();
        //模拟10条假数据
        for (int i = 0; i < 10; i++) {
            list.add(new TestDataBean("android", "http://inews.gtimg.com/newsapp_bt/0/876781763/1000"));
        }
        initRcy();

        initMainUp();
    }

    private void initMainUp() {

        mainUpView1 =new MainUpView(_mActivity);
        mainUpView1.attach2Window(_mActivity);

        if (Utils.getSDKVersion() == 17) { // 测试 android 4.2版本.
            switchNoDrawBridgeVersion();
        } else { // 其它版本（android 4.3以上）.
            mainUpView1.setUpRectResource(R.drawable.test_rectangle); // 设置移动边框的图片.
            //mainUpView1.setShadowResource(R.drawable.item_shadow); // 设置移动边框的阴影.
        }

        mRelativeMainLayout.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {

                if (newFocus != null)
                    newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
                float scale = 1.2f;
                mainUpView1.setFocusView(newFocus, oldFocus, scale);

                mOldFocus = newFocus; // 4.3以下需要自己保存.
                // 测试是否让边框绘制在下面，还是上面. (建议不要使用此函数)
                if (newFocus != null) {
                    //testTopDemo(newFocus, scale);
                }
            }
        });
    }


    private void initRcy() {

        TestAdapter adapter = new TestAdapter(_mActivity, list);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(_mActivity));
        mRecyclerview.setAdapter(adapter);
    }

    public float getDimension(int id) {
        return getResources().getDimension(id);
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


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 8:

                return true;
            case 9:

                bgResId = R.color.color_darkviolet;
                mRootMain.setBackgroundResource(bgResId);
                LocalDataManager.getInstance().saveLaucherConfigBean(new LaucherConfigBean(bgResId));

                return true;
            case 10:
                return true;
        }
        return false;
    }

    @SuppressLint("ResourceType")
    public final View initUI() {

        ViewGroup rootMain = (ViewGroup) _mActivity.findViewById(Window.ID_ANDROID_CONTENT);
        //根布局参数
        // RelativeLayout rootMain = ViewUtils.creatRootView(_mActivity, bgResId);
        mRootMain = rootMain;

        //RelativeMainLayout
        mRelativeMainLayout = ViewUtils.creatRelativeMainView(_mActivity);
        rootMain.addView(mRelativeMainLayout, ViewUtils.RelativeLayoutParamsWithMatchParent());

        //mRelativeMainLayout儿子
        mRecyclerview = ViewUtils.creatNormalRecyclerView(_mActivity);
         mRelativeMainLayout.addView(mRecyclerview,ViewUtils.RelativeLayoutParams(_mActivity,300,300,900, 100));

        //窗口视频
//        mVideoView=ViewUtils.creatVideoView(_mActivity);
//        mRelativeMainLayout.addView(mVideoView,ViewUtils.RelativeLayoutParams(_mActivity,600,500,80,450));

         frameMainLayout =ViewUtils.creatFrameMainLayout(_mActivity);
         frameMainLayout.setId(R.id.framelayout_videoview_id);

        mRelativeMainLayout.addView(frameMainLayout,ViewUtils.RelativeLayoutParams(_mActivity,600,500,80,400));

        //imageMain布局参数
        ImageView imageView = ViewUtils.creatImageView(_mActivity, R.mipmap.grid_view_item_test);
        //指定焦点获取，需延时回去，否则有可能会失效
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.requestFocus();
            }
        }, 200);
        mRelativeMainLayout.addView(imageView, ViewUtils.RelativeLayoutParams(_mActivity, 300, 300, 80, 100));

        mRelativeMainLayout.addView(ViewUtils.creatImageView(_mActivity, R.mipmap.grid_view_item_test), ViewUtils.RelativeLayoutParams(_mActivity, 300, 300, 250, 100));
        mRelativeMainLayout.addView(ViewUtils.creatImageView(_mActivity, R.mipmap.grid_view_item_test), ViewUtils.RelativeLayoutParams(_mActivity, 300, 300, 420, 100));
        mRelativeMainLayout.addView(ViewUtils.creatImageView(_mActivity, R.mipmap.grid_view_item_test), ViewUtils.RelativeLayoutParams(_mActivity, 300, 300, 590, 100));

        //时间显示
        TimeThread timeThread = new TimeThread(tvShowtime);
        timeThread.start();//启动线程

        //MainUp布局参数
//        mainUpView1 = ViewUtils.creatMainUpView(_mActivity);
//        mRootMain.addView(mainUpView1, ViewUtils.RelativeLayoutParamsWithWrapContent());

        return rootMain;

    }


    private void initVideoPlayer(String url) {

//        mVideoView=ViewUtils.creatVideoView(_mActivity);
//        mRelativeMainLayout.addView(mVideoView,ViewUtils.RelativeLayoutParamsWithMatchParent());

//        loadRootFragment(R.id.framelayout_videoview_id,MainDelegate.newInstance());

//        WindowController.showVideoWindow(_mActivity,"http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",
//                600,500,80,450);

       /* mVideoView= getActivity().findViewById(R.id.videoview);
        mVideoView.setZOrderOnTop(true);
        if (mVideoView != null) {
            mVideoView.setVideoPath(url);
            mVideoView.start();
        }
        // 播放出错回调
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return true;
            }
        });

        // 播放完成回调
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
            }
        });*/
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
