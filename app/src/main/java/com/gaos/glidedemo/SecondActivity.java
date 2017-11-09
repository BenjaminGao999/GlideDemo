package com.gaos.glidedemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    private final static String picURL = "http://www.onegreen.net/maps/m/a/world5.jpg";
    private SimpleTarget<Bitmap> bitmapSimpleTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final ImageView ivDemo = (ImageView) findViewById(R.id.iv_demo);

        Glide.with(SecondActivity.this)
                .load(picURL)
                .centerCrop()
                .into(ivDemo);

        bitmapSimpleTarget = Glide.with(SecondActivity.this).load(picURL).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {


                ivDemo.setScaleType(ImageView.ScaleType.CENTER);

                if (resource != null) {

                    ivDemo.setImageBitmap(resource);
                }
            }
        });

        Log.d(TAG, "onCreate: picURL = " + picURL);

        Button btnFinishSelf = (Button) findViewById(R.id.btn_finish_self);

        btnFinishSelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    /*
    * call finish -> onStop -> onDestroy
    *
    * call onBackPressed -> finish -> onStop -> onDestroy
    * */

    @Override
    public void finish() {
        super.finish();
        Log.d(TAG, "finish: " + TAG);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: " + TAG);
        Glide.with(this).onDestroy();
        if (bitmapSimpleTarget != null) {

            bitmapSimpleTarget.getRequest().clear();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + TAG);
    }
}
