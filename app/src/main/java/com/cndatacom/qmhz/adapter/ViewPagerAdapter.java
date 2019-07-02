package com.cndatacom.qmhz.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import static android.widget.ImageView.ScaleType.CENTER_CROP;
 /**
  * @date:  2019/06/10
  * @author: GaudiWen(cmt+回车)
  * 轮播图ViewPager
  */
public class ViewPagerAdapter extends PagerAdapter {

    private List<ImageView> images;
    private Context mContext;
     private ImageView mImageView=null;

     public ViewPagerAdapter(Context context, List<ImageView> img) {
        this.mContext = context;
        this.images = img;
    }

    //返回要滑动的VIew的个数
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //从当前container中删除指定位置（position）的View;
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(images.get(position));
    }

    //做了两件事，第一：将当前视图添加到container中，第二：返回当前View
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        container.addView(images.get(position));
        ImageView imageView = images.get(position);
         mImageView=imageView;
        imageView.setScaleType(CENTER_CROP);
        //imageView.setBackgroundResource(R.drawable.button_style);

        imageView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                    if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT && event.getAction() == KeyEvent.ACTION_DOWN) {
//                        rightImgView2.requestFocusFromTouch();
//                        rightImgView2.requestFocus();
//                        return true;
//                    }
//                    if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT && event.getAction() == KeyEvent.ACTION_DOWN) {
//                        rightImgView.requestFocusFromTouch();
//                        rightImgView.requestFocus();
//                        return true;
//                    }
                return false;
            }
        });
        imageView.setFocusable(false);
        imageView.setFocusableInTouchMode(false);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了第" + "" + position + "张图片", Toast.LENGTH_LONG).show();
            }
        });
        return imageView;
    }

    public ImageView getCurrentImgView(){

         if (mImageView!=null){
             return mImageView;
         }
         return mImageView;
    }
}