package com.cndatacom.qmhz.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class MyVideoView extends VideoView {

    int width;
    int height;

    public MyVideoView(Context context) {
        super(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         width = getDefaultSize(getWidth(), widthMeasureSpec);
         height = getDefaultSize(getHeight(), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    public void setVideoView(){
        setMeasuredDimension((int)(width*1.1f), (int)(height*1.1f));
    }
    public void resetVideoView(){
        setMeasuredDimension(width,height);
    }

}