package cn.zdh.uiadapter.density;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


/**
 * 像素密度
 * DensityUtils初始化可以设置在baseactivity 或者application或者activity
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //设置density
        DensityUtils.setDensity(getApplication(), this);
    }
}
