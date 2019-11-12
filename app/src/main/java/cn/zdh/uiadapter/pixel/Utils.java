package cn.zdh.uiadapter.pixel;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 获取缩放值工具类
 */
public class Utils {
    private static Utils utils;
    private Context mContext;
    //设置稿参考宽高
    private static final float STANDARD_WIDTH = 1080;
    private static final float STANDARD_HEIGHT = 1920;
    //屏幕的宽高
    private int mDisplayWidth;
    private int mDisplayHeight;

    //单利
    public static synchronized Utils getInstance(Context context) {
        if (utils == null) {
            synchronized (Utils.class) {
                if (utils == null) {
                    utils = new Utils(context);
                }
            }
        }

        return utils;
    }

    private Utils(Context context) {
        mContext = context;
        //获取屏幕的宽高
        if (mDisplayWidth == 0 || mDisplayHeight == 0) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                //获取手机的宽高
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
                //获取真实屏幕宽高（即我们绘制UI的宽高，状态栏不是我们可绘制区域所有需要减掉）
                //处理横竖屏问题
                //刘海屏适配需要减掉刘海屏高度，沉浸式适配状态了就不需要减状态栏了（目标获取可绘制宽高）
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    //当系统测量宽大于高说明横屏了、
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeight = displayMetrics.widthPixels - getStatusBarHeight(context);
                } else {
                    //竖屏
                    mDisplayWidth = displayMetrics.widthPixels;
                    mDisplayHeight = displayMetrics.heightPixels - getStatusBarHeight(context);

                }

            }
        }
    }


    //获取字体栏高度
    private int getStatusBarHeight(Context context) {
        //获取状态栏id 标识符
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            //获取具体像素值
            return context.getResources().getDimensionPixelOffset(resId);
        }
        return 0;
    }

    //获取宽高的缩放比例
    public float getHorizontalScale() {
        return mDisplayWidth / STANDARD_WIDTH;
    }

    public float getVerticalScale() {
        return mDisplayHeight / (STANDARD_HEIGHT - getStatusBarHeight(mContext));
    }


}
