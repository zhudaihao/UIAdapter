package cn.zdh.uiadapter.notch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import cn.zdh.uiadapter.R;
import cn.zdh.uiadapter.notch.notch.NotchUtil;
import cn.zdh.uiadapter.notch.notch.StatusBarUtil;
import cn.zdh.uiadapter.notch.notch.i.OnCutoutListener;


public abstract class BaseActivity extends AppCompatActivity {
    private boolean isHasCutout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全屏模式
        setFullScreen();

        //设置状态栏背景颜色
//        setStatusBarColor();


    }

    private void setFullScreen() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        NotchUtil.setImmersiveWithNotch(this, true, WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER);
    }


    private void setStatusBarColor() {
        if (getLocalClassName().contains("activity.SplashActivity")) {
            StatusBarUtil.setStatusBarColor(this, R.color.white);
        } else {
            StatusBarUtil.setStatusBarColor(this, R.color.colorAccent);
        }
    }



    protected boolean getIsHasCutout(){
        return isHasCutout;
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        //判断有没有刘海屏(获取需要在View绑定到Window之后，否在拿不到)
        NotchUtil.isHasCutout(this, new OnCutoutListener() {
            @Override
            public void isHasCutout(boolean isHas) {
                isHasCutout = isHas;
                if (isHas) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    /**
                     * * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT 全屏模式，内容下移 非全屏模式下不受影响
                     * * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES 允许内容进入刘海区域
                     * * @see #LAYOUT_IN_DISPLAY_CUTOUT_MODE_NEVER 不允许内容进入刘海区域
                     */
                    NotchUtil.setImmersiveWithNotch(BaseActivity.this, false,
                            WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES);
                }
            }
        });
    }
}