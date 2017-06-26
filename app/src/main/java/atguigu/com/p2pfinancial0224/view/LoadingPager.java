package atguigu.com.p2pfinancial0224.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import atguigu.com.p2pfinancial0224.R;
import atguigu.com.p2pfinancial0224.utils.HttpClientUtil;
import atguigu.com.p2pfinancial0224.utils.UIUtils;

import static atguigu.com.p2pfinancial0224.common.MyApplication.context;

/**
 * Created by sun on 2017/6/23.
 */

public abstract class LoadingPager extends FrameLayout {
    private View loadingview;
    private View errorview;

    private static final int STATE_LOADING=0;
    private static final int STATE_ERROR=1;
    private static final int STATE_SUCCESS=2;
    private int currentState;
    private View successView;

    public LoadingPager(Context context) {
        super(context);

        init();
    }

    public LoadingPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        loadingview=View.inflate(context, R.layout.page_loading,null);
        this.addView(loadingview);
        errorview=View.inflate(context,R.layout.page_error,null);
        this.addView(errorview);
        showSafePager();
    }

    private void showSafePager() {
        //在主线程运行
        UIUtils.runOnUIThread(new Runnable(){
            @Override
            public void run() {
              showPager();
            }
        });
    }

    private void showPager() {
        errorview.setVisibility(currentState==STATE_ERROR ? View.VISIBLE : View.GONE);
        loadingview.setVisibility(currentState==STATE_LOADING ? View.VISIBLE : View.GONE);
        if(successView==null){
            successView=getView();
            this.addView(successView);
        }

        successView.setVisibility(currentState==STATE_SUCCESS ? View.VISIBLE : View.GONE);

    }

    protected abstract View getView();

    public void loadNet(){
        String url=getUrl();
        if(TextUtils.isEmpty(url)){
            currentState=STATE_SUCCESS;
            showSafePager();
            setResult(successView,"");
        }else {
            HttpClientUtil.getInstance().get(url, new HttpClientUtil.OnHttpClientListener() {
                @Override
                public void onSuccess(String json) {
                    if (json.indexOf("title") > 0){
                        loadState = LoadState.ERROR;
                        showState();
                    }else{
                        //改变当前状态
                        loadState = LoadState.SUCCESS;
                        loadState.setJson(json);
                        showState();
                    }
                }

                @Override
                public void onFailure(String message) {

                    loadState = LoadState.ERROR;
                    showState();

                }
            });
        }

    }

    private void showState() {

        switch (loadState){
            case SUCCESS:
                currentState = STATE_SUCCESS;
                break;
            case ERROR:
                currentState = STATE_ERROR;
                break;
            case LOADING:
                currentState = STATE_LOADING;
                break;
        }

        showSafePager();

        if (currentState == STATE_SUCCESS){
            setResult(successView, loadState.SUCCESS.getJson());
        }

    }

    private LoadState loadState;
    public enum LoadState{

        SUCCESS(0),ERROR(1),LOADING(2);

        private int state;
        private String json;
        LoadState(int state){
            this.state = state;
        }

        public void setJson(String json){
            this.json = json;
        }

        public String getJson(){
            return json;
        }
    }

    protected abstract void setResult(View successView, String json);

    protected abstract String getUrl();

}
