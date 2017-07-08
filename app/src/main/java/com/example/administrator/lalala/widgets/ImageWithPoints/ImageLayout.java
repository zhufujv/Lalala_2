package com.example.administrator.lalala.widgets.ImageWithPoints;

/**
 * Created by Administrator on 2017/4/23.
 * 这是一个可以给图片添加标记点的组件
 */

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.example.administrator.lalala.R;

import java.util.ArrayList;

public class ImageLayout extends FrameLayout implements View.OnClickListener {

    ArrayList<PointSimple> points;

    FrameLayout layouPoints;

    ImageView imgBg;

    Context mContext;

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    public void setImageWithPoints(ImgSimple imgSimple) {

    }
    public void removeAll() {
        layouPoints.removeAllViews();
    }
    private void initView(Context context, AttributeSet attrs) {

        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }

    public void setImgBg(int width, int height, String imgUrl) {

        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;

        imgBg.setLayoutParams(lp);

        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = width;
        lp1.height = height;

        layouPoints.setLayoutParams(lp1);

        Glide.with(mContext).load(imgUrl).asBitmap().into(imgBg);

        addPoints(width, height);

    }
    public void setImgBg(int width, int height, int resource) {

        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;

        imgBg.setLayoutParams(lp);

        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = width;
        lp1.height = height;

        layouPoints.setLayoutParams(lp1);

        Glide.with(mContext).load(resource).asBitmap().into(imgBg);

        addPoints(width, height);

    }
    public void setPoints(ArrayList<PointSimple> points) {

        this.points = points;
    }

    private void addPoints(int width, int height) {

        layouPoints.removeAllViews();

        for (int i = 0; i < points.size(); i++) {
            double width_scale = points.get(i).width_scale;
            double height_scale = points.get(i).height_scale;
            String label = points.get(i).label;

            View view = LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgPoint);
            TextView textview_label = (TextView)view.findViewById(R.id.label);
            textview_label.setText(label);
            imageView.setTag(i);

            AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();

            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

            layoutParams.leftMargin = (int) (width * width_scale);
            layoutParams.topMargin = (int) (height * height_scale);

            imageView.setOnClickListener(this);

            layouPoints.addView(view, layoutParams);
        }
    }


    @Override
    public void onClick(View view) {
        int pos = (int) view.getTag();
        Toast.makeText(getContext(), points.get(pos).label, Toast.LENGTH_SHORT).show();
    }
}