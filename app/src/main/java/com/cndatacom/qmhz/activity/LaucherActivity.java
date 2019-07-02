package com.cndatacom.qmhz.activity;

import android.content.Context;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.adapter.AnnounceAdapter;
import com.cndatacom.qmhz.adapter.TestAdapter;
import com.cndatacom.qmhz.adapter.ViewPagerAdapter;
import com.cndatacom.qmhz.bean.AnnounceBean;
import com.cndatacom.qmhz.bean.TestDataBean;
import com.cndatacom.qmhz.utils.LogUtils;
import com.cndatacom.qmhz.utils.TimeThread;
import com.cndatacom.qmhz.utils.ViewUtils;
import com.cndatacom.qmhz.view.DisplayUtil;
import com.cndatacom.qmhz.view.MyVideoView;
import com.cndatacom.qmhz.view.WindowController;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.utils.Utils;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.ViewPagerTV;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class LaucherActivity extends AppCompatActivity {

    TextView tvShowtime;

    private List<TestDataBean> list=null;

    View mOldFocus; // 4.3以下版本需要自己保存.
    private EditText editInfo;
    private EditText passWord;
    private RecyclerViewTV mRecyclerview;
    private MainUpView mainUpView1;
    RelativeLayout mRelativeMainLayout;
    private int bgResId;  //根布局背景
    private ViewGroup mRootMain;//根布局
    private MyVideoView mVideoView;
    private TextView tvTime; //时间显示
    private FrameMainLayout frameMainLayout;
    private ViewPagerAdapter adapter =null;
    private List<ImageView> images;
    private List<View> dots;
    //记录上一次点的位置
    private int oldPosition = 0;
    private TextView title;
    private View bannerLayout = null;
    private View bannerLayout2 = null;
    private LayoutInflater inflater =null;
    private ViewPager mViewPaper=null;
    private ViewPager mViewPaper2=null;
    private int currentItem;
    private int currentItem1;
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
            if(mViewPaper2!=null){
                mViewPaper2.setCurrentItem(currentItem1);
            }
        }
    };

    private ScheduledExecutorService scheduledExecutorService;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.mipmap.grid_view_item_test,
            R.mipmap.grid_view_item_test,
            R.mipmap.grid_view_item_test,
            R.mipmap.grid_view_item_test,
            R.mipmap.grid_view_item_test
    };
    //存放图片的标题
    private String[] titles = new String[]{
            "挑战者联盟，薛之谦又来辣",
            "老九门，又是李易峰这个傻叉",
            "红色通道，再看刘烨英俊潇洒",
            "神犬小七，小七居然是只猪",
            "灭罪师，鬼知道这是什么剧"
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delegate_laucher);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        initData();
    }

    protected void initData() {

        initUI();

        mRootMain.post(new Runnable() {
            @Override
            public void run() {
//              initVideoPlayer("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4",300,400,600,300);
                initVideoPlayer("http://14.29.1.245:8082/si-manager-image/temp/manager/zhengqiVideos/20190619144200_BOM_.mp4",388,441,500,141);
            }
        });

        initMainUp();
        getDensity();
    }
    

    public void initUI() {

        tvShowtime=findViewById(R.id.tv_showtime);
        ViewGroup rootMain = (ViewGroup) this.findViewById(Window.ID_ANDROID_CONTENT);
        //根布局参数
        mRootMain = rootMain;


        //RelativeMainLayout
        mRelativeMainLayout = ViewUtils.creatRelativeMainView(this);
        rootMain.addView(mRelativeMainLayout, ViewUtils.RelativeLayoutParamsWithMatchParent());

       /* initViewPager(imageIds);
        initViewPager2(imageIds);*/

        //mRelativeMainLayout儿子
       // mRecyclerview = ViewUtils.creatNormalRecyclerView(this);
       // mRelativeMainLayout.addView(mRecyclerview,ViewUtils.RelativeLayoutParams(this,300,300,900, 100));

        //imageMain布局参数
    //    ImageView imageView = ViewUtils.creatImageView(this, R.mipmap.grid_view_item_test);
        //指定焦点获取，需延时回去，否则有可能会失效
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                imageView.requestFocus();
//            }

//        }, 200);
      //  mRelativeMainLayout.addView(imageView, ViewUtils.RelativeLayoutParams(this, 300, 300, 80, 100));


        mRelativeMainLayout.addView(ViewUtils.creatImageViewGlide(this, "http://192.168.137.225/group1/M00/00/01/wKiJ4V0J20yAGGjxAAA2OJzwKbc199.png"), ViewUtils.RelativeLayoutParams(this,190, 190, 88, 142));
        mRelativeMainLayout.addView(ViewUtils.creatImageViewGlide(this, "http://192.168.137.225/group1/M00/00/01/wKiJ4V0J24uAJF-VAAA3oonTQRM247.png"), ViewUtils.RelativeLayoutParams(this,190, 190, 88, 360));
        mRelativeMainLayout.addView(ViewUtils.creatImageViewGlide(this, "http://192.168.137.225/group1/M00/00/01/wKiJ4V0J25-ABsfGAAA6OhRn_4s473.png"), ViewUtils.RelativeLayoutParams(this,190, 190, 305, 142));
        mRelativeMainLayout.addView(ViewUtils.creatImageViewGlide(this, "http://192.168.137.225/group1/M00/00/01/wKiJ4V0J2_mAIC1-AAA1s0Mm5GQ133.png"), ViewUtils.RelativeLayoutParams(this,190, 190, 305, 360));

        //跑马灯
        mRelativeMainLayout.addView(ViewUtils.creatTextView(this, "这是一个跑马灯，需放在跟布局。 这是一个跑马灯，需放在跟布局。 这是一个跑马灯，需放在跟布局。",40),
                ViewUtils.RelativeLayoutParams(this, 800, ViewGroup.LayoutParams.WRAP_CONTENT, 200, 50));

        //时间显示
        TimeThread timeThread = new TimeThread(tvShowtime);
        timeThread.start();//启动线程

        //initRecyclerView(200,400,400,500);
        initAnnounceRecyclerView(300,385,953,138);

        //   return rootMain;

    }

    private void initImageView(int resId,int width, int height,int leftMargin,int topMargin){

        mRelativeMainLayout.addView(ViewUtils.creatImageView(this, resId), ViewUtils.RelativeLayoutParams(this,width,height,leftMargin,topMargin));

    }
    private void initVideoPlayer(String url,int width, int height,int leftMargin,int topMargin) {

        frameMainLayout =ViewUtils.creatFrameMainLayout(this);
        mRelativeMainLayout.addView(frameMainLayout,ViewUtils.RelativeLayoutParams(this,388,441,500,141));
        mVideoView=ViewUtils.creatVideoView(this);
        RelativeLayout.LayoutParams relativeLayoutParams1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        relativeLayoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT);
        frameMainLayout.addView(mVideoView,relativeLayoutParams1);

       // mVideoView=ViewUtils.creatVideoView(this);
        //mRelativeMainLayout.addView(mVideoView,ViewUtils.RelativeLayoutParams(this,width,height,leftMargin,topMargin));
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

    private void initViewPager(int[] imageIds){

        // 产生一个ExecutorService对象，这个对象只有一个线程可用来执行任务，若任务多于一个，任务将按先后顺序执行。
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 2, 2, TimeUnit.SECONDS);

        //显示的图片
        List<ImageView> images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[i]);
            images.add(imageView);
        }
        inflater = LayoutInflater.from(this);
        bannerLayout = inflater.inflate(R.layout.dialog_viewpager, null);

        mViewPaper=(ViewPager) bannerLayout.findViewById(R.id.vp);
        mViewPaper.setFocusable(false);
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(bannerLayout.findViewById(R.id.dot_0));
        dots.add(bannerLayout.findViewById(R.id.dot_1));
        dots.add(bannerLayout.findViewById(R.id.dot_2));
        dots.add(bannerLayout.findViewById(R.id.dot_3));
        dots.add(bannerLayout.findViewById(R.id.dot_4));


        title = (TextView)bannerLayout.findViewById(R.id.title);
        title.setText(titles[0]);
        mViewPaper.setAdapter(adapter = new ViewPagerAdapter(this, images));

        //禁止手指控制滑动
        mViewPaper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        frameMainLayout =ViewUtils.creatFrameMainLayout(this);
        frameMainLayout.setFocusable(true);
        mRelativeMainLayout.addView(frameMainLayout,ViewUtils.RelativeLayoutParams(this,400,400,200,300));
        frameMainLayout.addView(bannerLayout,ViewUtils.RelativeLayoutParams(this,400,400,200,300));

        frameMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView viewPagerImgView=adapter.getCurrentImgView();
                viewPagerImgView.performClick();
            }
        });

    }
    private void initViewPager2(int[] imageIds){

        // 产生一个ExecutorService对象，这个对象只有一个线程可用来执行任务，若任务多于一个，任务将按先后顺序执行。
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 2, 2, TimeUnit.SECONDS);

        //显示的图片
        List<ImageView> images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[i]);
            images.add(imageView);
        }
        inflater = LayoutInflater.from(this);
        bannerLayout2 = inflater.inflate(R.layout.dialog_viewpager, null);

        mViewPaper2=(ViewPager) bannerLayout2.findViewById(R.id.vp);
        mViewPaper2.setFocusable(false);
        //显示的小点
//        dots = new ArrayList<View>();
//        dots.add(bannerLayout.findViewById(R.id.dot_0));
//        dots.add(bannerLayout.findViewById(R.id.dot_1));
//        dots.add(bannerLayout.findViewById(R.id.dot_2));
//        dots.add(bannerLayout.findViewById(R.id.dot_3));
//        dots.add(bannerLayout.findViewById(R.id.dot_4));


//        title = (TextView)bannerLayout.findViewById(R.id.title);
//        title.setText(titles[0]);
        mViewPaper2.setAdapter(adapter = new ViewPagerAdapter(this, images));

        mViewPaper2.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
//                title.setText(titles[position]);
//                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
//                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                oldPosition = position;
                currentItem1 = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

        frameMainLayout =ViewUtils.creatFrameMainLayout(this);
        frameMainLayout.setFocusable(true);
        mRelativeMainLayout.addView(frameMainLayout,ViewUtils.RelativeLayoutParams(this,400,400,400,300));
        frameMainLayout.addView(bannerLayout2,ViewUtils.RelativeLayoutParams(this,400,400,400,300));

        frameMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ImageView viewPagerImgView=adapter.getCurrentImgView();
                viewPagerImgView.performClick();
            }
        });

    }

    private void initRecyclerView(int width, int height,int leftMargin,int topMargin){

        list=new ArrayList<>();
        //模拟10条假数据
        for (int i = 0; i < 10; i++) {
            list.add(new TestDataBean("android", "http://inews.gtimg.com/newsapp_bt/0/876781763/1000"));
        }

//        inflater = LayoutInflater.from(this);
//        recyclerviewLayout = inflater.inflate(R.layout.dialog_recyclerview, null);
//        mRecyclerview = (RecyclerViewTV) (recyclerviewLayout).findViewById(R.id.recyclerView_tv);

        RecyclerViewTV mRecyclerview = ViewUtils.creatNormalRecyclerView(this);

        TestAdapter adapter = new TestAdapter(this, list);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);
        mRecyclerview.setSelectedItemAtCentered(true);

        mRelativeMainLayout.addView(mRecyclerview,ViewUtils.RelativeLayoutParams(this,width,height,leftMargin,topMargin));

    }
    private void initAnnounceRecyclerView(int width, int height,int leftMargin,int topMargin){

        List<AnnounceBean> list=new ArrayList<>();
        //模拟10条假数据
        for (int i = 0; i < 10; i++) {
            list.add(new AnnounceBean("http://inews.gtimg.com/newsapp_bt/0/876781763/1000"));
        }

//        inflater = LayoutInflater.from(this);
//        recyclerviewLayout = inflater.inflate(R.layout.dialog_recyclerview, null);
//        mRecyclerview = (RecyclerViewTV) (recyclerviewLayout).findViewById(R.id.recyclerView_tv);

        RecyclerViewTV mRecyclerview = ViewUtils.creatNormalRecyclerView(this);

        AnnounceAdapter adapter = new AnnounceAdapter(this,list,20);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(adapter);
        mRecyclerview.setSelectedItemAtCentered(true);
        mRecyclerview.setBackgroundColor(R.color.color_aqua);

        mRelativeMainLayout.addView(mRecyclerview,ViewUtils.RelativeLayoutParams(this,width,height,leftMargin,topMargin));
    }

    private float getDensity() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        LogUtils.e("dpi==" + dm.density);
        return dm.density;
    }


    private void initMainUp() {

        mainUpView1 =new MainUpView(this);
        mainUpView1.attach2Window(this);

        if (Utils.getSDKVersion() == 17) { // 测试 android 4.2版本.
           // switchNoDrawBridgeVersion();
        } else { // 其它版本（android 4.3以上）.
            mainUpView1.setUpRectResource(R.drawable.ic_sp_block_focus); // 设置移动边框的图片.
            //mainUpView1.setShadowResource(R.drawable.item_shadow); // 设置移动边框的阴影.
        }

        mRelativeMainLayout.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(final View oldFocus, final View newFocus) {

                if (newFocus != null)
                    newFocus.bringToFront(); // 防止放大的view被压在下面. (建议使用MainLayout)
                float scale = 1.2f;

                mainUpView1.setFocusView(newFocus, oldFocus, scale);

                if(newFocus==frameMainLayout){
                    if(mVideoView!=null){
                       // frameMainLayout.setPadding(2,2,2,2);
                        mainUpView1.setFocusView(newFocus, oldFocus, 1.1f);
                        mVideoView.setVideoView();
                    }
                }
                if(oldFocus==frameMainLayout){
                    if(mVideoView!=null){
                       // frameMainLayout.setPadding(0,0,0,0);
                        mainUpView1.setFocusView(newFocus, oldFocus, 1.1f);
                        mVideoView.resetVideoView();
                    }
                    return;
                }

                mOldFocus = newFocus; // 4.3以下需要自己保存.
                // 测试是否让边框绘制在下面，还是上面. (建议不要使用此函数)
                if (newFocus != null) {
                    //testTopDemo(newFocus, scale);
                }
            }
        });
    }

    private class ViewPageTask implements Runnable {
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            currentItem1 = (currentItem1 + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }
}
