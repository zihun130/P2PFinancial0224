package atguigu.com.p2pfinancial0224.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by sun on 2017/6/23.
 */

public class MyScrollView extends ScrollView {

    private int lastY;
    private Rect rect=new Rect();
    private View childView;
    private int lastX;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    private boolean isfinish=false;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isfinish && getChildCount()>0){
            return super.onTouchEvent(ev);
        }

      int eventY= (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN :

                lastY= eventY;

                break;
            case MotionEvent.ACTION_MOVE:
                int dY=eventY-lastY;
                //只能在move中,不能放在down中,否则按住banner移动时,返回效果会消失.
                if(rect.isEmpty()){
                    rect.set(childView.getLeft(),childView.getTop(),childView.getRight(),childView.getBottom());

                }
                childView.layout(childView.getLeft(),childView.getTop()+dY,childView.getRight(),childView.getBottom()+dY);

                lastY=eventY;
                break;
            case MotionEvent.ACTION_UP :
                if(!rect.isEmpty()){
                    int translateY = childView.getTop() - rect.top;
                    TranslateAnimation tra=new TranslateAnimation(0,0,0,-translateY);
                    tra.setDuration(200);
                    tra.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            isfinish=true;
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            isfinish=false;
                            childView.layout(rect.left,rect.top,rect.right,rect.bottom);
                            rect.setEmpty();
                            childView.clearAnimation();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    childView.startAnimation(tra);
                }

                break;
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isOnTouch=false;

        int evenX= (int) ev.getX();
        int evenY= (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN :
                lastY=evenY;
                lastX=evenX;

                break;
            case MotionEvent.ACTION_MOVE :
                int dx=Math.abs(evenX-lastX);
                int dy=Math.abs(evenY-lastY);

                if(dy>dx && dy>=20){
                    isOnTouch=true;
                }

                lastX=evenX;
                lastY=evenY;

                break;
        }
        return isOnTouch;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>0){
            childView = getChildAt(0);
        }

    }
}
