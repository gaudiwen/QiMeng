package com.cndatacom.qmhz.activity;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.TestAdapter;
import com.cndatacom.qmhz.bean.TestDataBean;
import com.cndatacom.qmhz.utils.LogUtils;
import com.cndatacom.qmhz.utils.TimeThread;
import com.cndatacom.qmhz.utils.ViewUtils;
import com.cndatacom.qmhz.view.DisplayUtil;
import com.cndatacom.qmhz.view.WindowController;
import com.open.androidtvwidget.utils.Utils;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class LaucherActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delegate_laucher);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        initData();
    }

    protected void initData() {

        initUI();

        initGridData();
        getDensity();
       // initVideoPlayer("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");

        mRootMain.post(new Runnable() {
            @Override
            public void run() {
                initVideoPlayer("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4");
            }
        });
    }
    

    public void initUI() {

        tvShowtime=findViewById(R.id.tv_showtime);
        ViewGroup rootMain = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
        //根布局参数
        // RelativeLayout rootMain = ViewUtils.creatRootView(this, bgResId);
        mRootMain = rootMain;

        //RelativeMainLayout
        mRelativeMainLayout = ViewUtils.creatRelativeMainView(this);
        rootMain.addView(mRelativeMainLayout, ViewUtils.RelativeLayoutParamsWithMatchParent());

        //跑马灯
        mRelativeMainLayout.addView(ViewUtils.creatTextView(this, "这是一个跑马灯，需放在跟布局。 这是一个跑马灯，需放在跟布局。 这是一个跑马灯，需放在跟布局。"),
                ViewUtils.RelativeLayoutParams(this, 800, ViewGroup.LayoutParams.WRAP_CONTENT, 100, 0));

        //mRelativeMainLayout儿子
        mRecyclerview = ViewUtils.creatNormalRecyclerView(this);
        mRelativeMainLayout.addView(mRecyclerview,ViewUtils.RelativeLayoutParams(this,300,300,900, 100));


        //imageMain布局参数
        ImageView imageView = ViewUtils.creatImageView(this, R.mipmap.grid_view_item_test);
        //指定焦点获取，需延时回去，否则有可能会失效
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                imageView.requestFocus();
//            }
//        }, 200);
        mRelativeMainLayout.addView(imageView, ViewUtils.RelativeLayoutParams(this, 300, 300, 80, 100));

        mRelativeMainLayout.addView(ViewUtils.creatImageView(this, R.mipmap.grid_view_item_test), ViewUtils.RelativeLayoutParams(this, 300, 300, 250, 100));
        mRelativeMainLayout.addView(ViewUtils.creatImageView(this, R.mipmap.grid_view_item_test), ViewUtils.RelativeLayoutParams(this, 300, 300, 420, 100));
        mRelativeMainLayout.addView(ViewUtils.creatImageView(this, R.mipmap.grid_view_item_test), ViewUtils.RelativeLayoutParams(this, 300, 300, 590, 100));

        //时间显示
        TimeThread timeThread = new TimeThread(tvShowtime);
        timeThread.start();//启动线程

        frameMainLayout =ViewUtils.creatFrameMainLayout(this);
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(400, 400);
        relativeLayoutParams.leftMargin = DisplayUtil.dip2px(this,400);
        relativeLayoutParams.topMargin = DisplayUtil.dip2px(this,400);
        relativeLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mRelativeMainLayout.addView(frameMainLayout,ViewUtils.RelativeLayoutParams(this,400,400,400,400));

        //MainUp布局参数
//        mainUpView1 = ViewUtils.creatMainUpView(this);
//        mRootMain.addView(mainUpView1, ViewUtils.RelativeLayoutParamsWithWrapContent());

     //   return rootMain;

    }

    private void initVideoPlayer(String url) {

        mVideoView=ViewUtils.creatVideoView(this);
        mVideoView.setZOrderOnTop(true);
        RelativeLayout.LayoutParams relativeLayoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT);
        frameMainLayout.addView(mVideoView,relativeLayoutParams1);

        //窗口视频
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
        });
    }

    private float getDensity() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
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

    private void initRcy() {

        TestAdapter adapter = new TestAdapter(this, list);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);
    }

    private void initMainUp() {

        mainUpView1 =new MainUpView(this);
        mainUpView1.attach2Window(this);

        if (Utils.getSDKVersion() == 17) { // 测试 android 4.2版本.
           // switchNoDrawBridgeVersion();
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

}
