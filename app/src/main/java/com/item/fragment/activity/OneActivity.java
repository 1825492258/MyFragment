package com.item.fragment.activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.item.fragment.R;
import com.item.fragment.ment.LoginFragment;
import com.item.fragment.ment.RegisterFragment;

/**
 * 在一个Activity中嵌套2个Fragment
 */
public class OneActivity extends AppCompatActivity implements IOneTrigger {

    private ImageView imgView; // 给图片设置着色及蒙板效果

    private Fragment mCurFragment;
    private Fragment mLoginFragment;
    private Fragment mRegisterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        initFragment();
        initWidget();
    }

    private void initFragment() {
        // 初始化Fragment
        mCurFragment = mLoginFragment = new LoginFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_container, mCurFragment)
                .commit();
    }

    private void initWidget() {
        imgView = (ImageView) findViewById(R.id.img_view);
        Glide.with(this)
                .load(R.drawable.bg_src_tianjin)
                .into(new ViewTarget<ImageView,Drawable>(imgView) {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        // 拿到Glide的Drawable
                        Drawable drawable = resource.getCurrent();
                        // 使用适配类进行包装
                        drawable = DrawableCompat.wrap(drawable);
                        // 设置着色的效果和颜色 蒙板模式
                        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary),
                                PorterDuff.Mode.SCREEN);
                        // 设置给ImageView
                        this.view.setImageDrawable(drawable);
                    }
                });

        // 初始化背景
//        Glide.with(this)
//                .load(R.drawable.bg_src_tianjin)
//                .into(new SimpleTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                        // 拿到Glide的Drawable
//                        Drawable drawable = resource.getCurrent();
//                        // 使用适配类进行包装
//                        drawable = DrawableCompat.wrap(drawable);
//                        // 设置着色的效果和颜色 蒙板模式
//                        drawable.setColorFilter(getResources().getColor(R.color.colorPrimary),
//                                PorterDuff.Mode.SCREEN);
//                        // 设置给ImageView
//                        imgView.setImageDrawable(drawable);
//                    }
//                });
    }

    @Override
    public void triggerView() {
        Fragment fragment;
        if (mCurFragment == mLoginFragment) {
            if (mRegisterFragment == null) {
                // 默认情况下为null
                // 第一次之后就不为null了
                mRegisterFragment = new RegisterFragment();
            }
            fragment = mRegisterFragment;
        } else {
            fragment = mLoginFragment;
        }
        // 重新赋值当前正在显示的Fragment
        mCurFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container, fragment)
                .commit();
    }
}
