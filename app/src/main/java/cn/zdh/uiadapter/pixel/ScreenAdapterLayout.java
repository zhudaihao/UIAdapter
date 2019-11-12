package cn.zdh.uiadapter.pixel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 动态运行时屏幕适配
 */
public class ScreenAdapterLayout extends RelativeLayout {
    //测量标记，防止二次测量
    private boolean flag;

    public ScreenAdapterLayout(Context context) {
        super(context);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScreenAdapterLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    /**
     * 在测量方法中对自定义view 以及子view的宽高等比缩放 达到适配
     * <p>
     * 注意在super前执行
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setScale();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    //对子view等比缩放
    private void setScale() {
        if (!flag) {
            //防止二次测量
            flag = true;
            //获取宽高缩放值
            float horizontalScale = Utils.getInstance(getContext()).getHorizontalScale();
            float verticalScale = Utils.getInstance(getContext()).getVerticalScale();

            //获取子view的个数
            int childCount = getChildCount();
            //遍历子view
            for (int i = 0; i < childCount; i++) {
                //获取子view
                View childAt = getChildAt(i);
                //对view宽高等比缩放 后赋值到view里面（通过LayoutParams）
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                //宽高等比缩放
                layoutParams.width = (int) (layoutParams.width * horizontalScale);
                layoutParams.height = (int) (layoutParams.height * verticalScale);
                //对margin偏移量的等比缩放
                layoutParams.leftMargin = (int) (layoutParams.leftMargin * horizontalScale);
                layoutParams.rightMargin = (int) (layoutParams.rightMargin * horizontalScale);
                layoutParams.topMargin = (int) (layoutParams.topMargin * verticalScale);
                layoutParams.bottomMargin = (int) (layoutParams.bottomMargin * verticalScale);
                //队padding的偏移量等比缩放
                childAt.setPadding((int) (childAt.getPaddingLeft() * horizontalScale),
                        (int) (childAt.getPaddingTop() * verticalScale),
                        (int) (childAt.getPaddingRight() * horizontalScale),
                        (int) (childAt.getPaddingBottom() * horizontalScale));

                //把修改后的参数赋值到view里面
                childAt.setLayoutParams(layoutParams);
            }
        }
    }
}
