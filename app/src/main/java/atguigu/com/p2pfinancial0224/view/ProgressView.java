package atguigu.com.p2pfinancial0224.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.common.MyApplication;
import atguigu.com.p2pfinancial0224.utils.DensityUtil;

/**
 * Created by sun on 2017/6/23.
 */

public class ProgressView extends View{
    private int textSizes;
    private Paint paint;
    private int height;
    private int width;
    private float strokewidth= DensityUtil.dip2px(MyApplication.context,10);
    private int paintColors;
    private float sweeAngle=0;
    private int textColors;

    public ProgressView(Context context) {
        super(context);

        init();
    }


    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progressView);
        int color = typedArray.getColor(R.styleable.progressView_paintColors, Color.BLUE);
        paintColors=color;
        int color1 = typedArray.getColor(R.styleable.progressView_textColors, Color.RED);
        textColors=color1;
        int anInt = typedArray.getInt(R.styleable.progressView_textSizes, DensityUtil.dip2px(context,35));
        textSizes=anInt;
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height=getMeasuredHeight();
        width=getMeasuredWidth();
    }

    private void init() {
        paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画笔宽度
        paint.setStrokeWidth(strokewidth);
        paint.setColor(paintColors);
        float cx=width/2;
        float cy=height/2;
        float radiuss=cx-strokewidth/2;
        canvas.drawCircle(cx,cy,radiuss,paint);
        //画弧
        paint.setColor(Color.RED);
        RectF rectF=new RectF();
        rectF.set(strokewidth/2,strokewidth/2,width-strokewidth/2,height-strokewidth/2);
        canvas.drawArc(rectF,0,sweeAngle,false,paint);
        //画文字
        paint.setStrokeWidth(0);
        paint.setTextSize(textSizes);
        paint.setColor(textColors);
        String str=sweeAngle+"%";
        Rect rect=new Rect();
        paint.getTextBounds(str,0,str.length(),rect);
        int textwidth = rect.width();
        int textheight = rect.height();
        float Tx=width/2-textwidth/2;
        float Ty=height/2+textheight/2;
        canvas.drawText(str,Tx,Ty,paint);


    }

    public void setSweeAngle(int sweeAngle) {
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(0, sweeAngle);
        valueAnimator.setDuration(5000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int am = (int) animation.getAnimatedValue();
                ProgressView.this.sweeAngle=am;
                invalidate();
            }
        });
        valueAnimator.start();
    }


}
