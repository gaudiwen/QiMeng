package com.cndatacom.qmhz.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.cndatacom.qmhz.R;
import com.cndatacom.qmhz.view.DisplayUtil;
import com.cndatacom.qmhz.view.MarqueeTextView;
import com.cndatacom.qmhz.view.MyVideoView;
import com.open.androidtvwidget.leanback.recycle.RecyclerViewTV;
import com.open.androidtvwidget.view.FrameMainLayout;
import com.open.androidtvwidget.view.MainUpView;
import com.open.androidtvwidget.view.RelativeMainLayout;
import com.open.androidtvwidget.view.ViewPagerTV;

import static android.text.TextUtils.TruncateAt.MARQUEE;

/**
 * 描述: 使用JAVA代码生成xml
 * 邮箱：275634247@qq.com
 * Created by GaudiWen on 2019/5/15 9:14.
 */
public class ViewUtils {

    /**
     * xml根布局
     * @param context
     * @param resid
     * @return
     */
    public static RelativeLayout creatRootView(Context context, int resid){
        //根布局参数
        LinearLayout.LayoutParams rootLinearLayoutParams
                =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout rootMain = new RelativeLayout(context);
        rootMain.setBackgroundResource(resid);
        rootMain.setClipChildren(false);
        rootMain.setClipToPadding(false);
        rootMain.setLayoutParams(rootLinearLayoutParams);
        return rootMain;
    }

    /**
     * Android Tv焦点指示移动边框
     */
    public static MainUpView creatMainUpView(Context context){
        //LinearLayout.LayoutParams layoutParamsMainUp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        MainUpView mainUpView = new MainUpView(context);
        mainUpView.setUpRectResource(R.drawable.test_rectangle); // 设置移动边框的图片.
       // rootMain.addView(mainUpView1, layoutParamsMainUp);
        return mainUpView;
    }

    /**
     * 放置各模块的父Relativelayout
     * @param context
     * @return
     */
    public static RelativeLayout creatRelativeMainView(Context context){
        //RelativeMainLayout
        //LinearLayout.LayoutParams layoutParamsFrameRoot = new LinearLayout.LayoutParams(width, height);
        RelativeLayout relativeMainLayout = new RelativeLayout(context);
        relativeMainLayout.setClipChildren(false);
        relativeMainLayout.setClipToPadding(false);
        //rootMain.addView(RelativeMainLayout, layoutParamsFrameRoot);
        return relativeMainLayout;
    }

    /**
     * 普通RecyclerView模块布局
     * @param context
     * @param width
     * @param height
     * @return
     */
    public static RecyclerViewTV creatNormalRecyclerView(Context context){
        //mRelativeMainLayout儿子
//        RelativeLayout.LayoutParams layoutParamsRecyclerMain = new RelativeLayout.LayoutParams(width, height);
//        layoutParamsRecyclerMain.leftMargin = DisplayUtil.dip2px(context,leftMargin);
//        layoutParamsRecyclerMain.topMargin = DisplayUtil.dip2px(context,topMargin);
        RecyclerViewTV recyclerView = new RecyclerViewTV(context);
        recyclerView.setFocusable(false);
       // RelativeMainLayout.addView(recyclerView, layoutParamsRecyclerMain);
        return recyclerView;
    }

    /**
     * 普通ImageView控件模块
     * @param context
     * @return
     */
    public static ImageView creatImageView(Context context,int resId){

        //imageMain布局参数
//        RelativeLayout.LayoutParams layoutParamsImageMain = new RelativeLayout.LayoutParams(width, height);
//        layoutParamsImageMain.leftMargin = DisplayUtil.dip2px(context,leftMargin);
//        layoutParamsImageMain.topMargin = DisplayUtil.dip2px(context,topMargin);
        //初始化ImageView
        ImageView imageMain = new ImageView(context);
        imageMain.setScaleType(ImageView.ScaleType.FIT_XY);
        imageMain.setFocusable(true);
        imageMain.setFocusableInTouchMode(true);
        imageMain.setAdjustViewBounds(true);
        imageMain.setImageResource(resId);
       // RelativeMainLayout.addView(imageMain, layoutParamsImageMain);
        return imageMain;
    }

    public static ImageView creatImageViewGlide(Context context,String url){

        //初始化ImageView
        ImageView imageMain = new ImageView(context);
        imageMain.setScaleType(ImageView.ScaleType.FIT_XY);
        imageMain.setFocusable(true);
        imageMain.setFocusableInTouchMode(true);
        imageMain.setAdjustViewBounds(true);
        Glide.with(context).load(url).into(imageMain);
        return imageMain;
    }

    /**
     * 跑马灯TextView
     * @param context
     * @param resId
     * @return
     */
    public static TextView creatTextView(Context context,String tvContent,int textSize){

        //初始化ImageView
        MarqueeTextView marqueeTv = new MarqueeTextView(context);
        marqueeTv.setTextSize(textSize);
        marqueeTv.setFocusable(true);
        marqueeTv.setFocusableInTouchMode(true);
        marqueeTv.setEllipsize(MARQUEE);
        marqueeTv.setMarqueeRepeatLimit(-1);
        marqueeTv.setHorizontallyScrolling(true);
        marqueeTv.setSingleLine(true);
        marqueeTv.setText(tvContent);
        marqueeTv.setGravity(Gravity.CENTER_HORIZONTAL);
        marqueeTv.setNextFocusDownId(marqueeTv.getId());
        return marqueeTv;
    }

    /**
     * 普通TextView
     * @param context
     * @param tvContent
     * @return
     */
    public static TextView creatNormalTextView(Context context,float textSize){

        TextView normalTv = new TextView(context);
        normalTv.setFocusable(true);
        normalTv.setFocusableInTouchMode(true);
        normalTv.setHorizontallyScrolling(true);
        normalTv.setSingleLine(true);
  //      normalTv.setText(tvContent);
        normalTv.setTextSize(textSize);
        normalTv.setGravity(Gravity.CENTER_HORIZONTAL);
        return normalTv;
    }

    /**
     * VideoView
     * @param context
     * @param tvContent
     * @return
     */
    public static MyVideoView creatVideoView(Context context){

        MyVideoView videoView = new MyVideoView(context);
        videoView.setZOrderOnTop(true);
        videoView.setFocusable(false);
        videoView.setFocusableInTouchMode(false);
        return videoView;
    }

    public static ViewPager creatViewPager(Context context){

        ViewPager viewPager = new ViewPager(context);
//        viewPager.setFocusable(true);
//        viewPager.setFocusableInTouchMode(true);
        return viewPager;
    }

    public static FrameMainLayout creatFrameMainLayout(Context context){

        FrameMainLayout framemainlayout = new FrameMainLayout(context);
        framemainlayout.setId(R.id.framelayout_videoview_id);
        framemainlayout.setBackgroundColor(R.color.color_black);
        framemainlayout.setFocusable(true);
        framemainlayout.setFocusableInTouchMode(true);
        framemainlayout.setClipChildren(false);
        framemainlayout.setClipToPadding(false);
        return framemainlayout;
    }

    /**
     * layout参数设置
     * @return
     */
    public static  LinearLayout.LayoutParams LinearLayoutParamsWithWrapContent(){

        return new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static  RelativeLayout.LayoutParams  RelativeLayoutParamsWithWrapContent(){

        return new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    public static  RelativeLayout.LayoutParams  RelativeLayoutParamsWithMatchParent(){

        return new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    public static  RelativeLayout.LayoutParams  RelativeLayoutParams(Context context,int width, int height,int leftMargin,int topMargin){

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(width, height);
//        relativeLayoutParams.leftMargin = leftMargin;
//        relativeLayoutParams.topMargin =topMargin;
        relativeLayoutParams.leftMargin = DisplayUtil.px2dip(context,leftMargin);
        relativeLayoutParams.topMargin = DisplayUtil.px2dip(context,topMargin);
        return relativeLayoutParams;
    }
    public static  RelativeLayout.LayoutParams  RelativeLayoutParams(Context context,int width, int height){

        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(width, height);
        return relativeLayoutParams;
    }

    public static  RelativeLayout.LayoutParams  RelativeLayoutParamsWithWrapContentMargin(Context context,int leftMargin,int topMargin){

        RelativeLayout.LayoutParams layoutParamsImageMain = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsImageMain.leftMargin = DisplayUtil.px2dip(context,leftMargin);
        layoutParamsImageMain.topMargin = DisplayUtil.px2dip(context,topMargin);
        return layoutParamsImageMain;
    }


}
