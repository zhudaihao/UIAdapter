package cn.zdh.uiadapter.density;


import android.os.Bundle;

import cn.zdh.uiadapter.R;

/**
 * 像素密度 适配
 */
public class DensityActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置density
//        DensityUtils.setDensity(getApplication(), this);
        setContentView(R.layout.activity_density);
    }

}
