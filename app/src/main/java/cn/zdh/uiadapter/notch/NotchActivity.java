package cn.zdh.uiadapter.notch;

import android.os.Bundle;
import android.util.Log;

import cn.zdh.uiadapter.R;
import cn.zdh.uiadapter.notch.notch.StatusBarUtil;
import cn.zdh.uiadapter.pixel.ScreenAdapterLayout;


public class NotchActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notch);


        Log.e("zdh","-----------onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("zdh","-----------onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zdh","-----------onResume");
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        Log.e("zdh","-----------onAttachedToWindow");
        //适配刘海屏
        if (getIsHasCutout()) {
            ScreenAdapterLayout layout = findViewById(R.id.layout);
            layout.setPadding(0, StatusBarUtil.getStatusBarHeight(this), 0, 0);
        }
    }

}
