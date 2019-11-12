package cn.zdh.uiadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.zdh.uiadapter.density.DensityActivity;
import cn.zdh.uiadapter.notch.NotchActivity;
import cn.zdh.uiadapter.percent.PercentActivity;
import cn.zdh.uiadapter.pixel.PixelActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //动态运行时适配
    public void pixel(View view) {
        Intent intent = new Intent(MainActivity.this, PixelActivity.class);
        startActivity(intent);
    }

    //百分比适配
    public void percent(View view) {
        Intent intent = new Intent(MainActivity.this, PercentActivity.class);
        startActivity(intent);
    }

    //像素屏幕适配--》今日头条
    public void density(View view) {
        Intent intent = new Intent(MainActivity.this, DensityActivity.class);
        startActivity(intent);
    }


    //刘海屏适配
    public void notch(View view) {
        Intent intent = new Intent(MainActivity.this, NotchActivity.class);
        startActivity(intent);
    }
}
