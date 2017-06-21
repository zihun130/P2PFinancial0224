package atguigu.com.p2pfinancial0224.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by sun on 2017/6/21.
 */

public class HttpClientUtil {

    private final AsyncHttpClient asyncHttpClient;

    private HttpClientUtil (){
        asyncHttpClient=new AsyncHttpClient();
    }

    private static HttpClientUtil httpClientUtil=new HttpClientUtil();

    public static HttpClientUtil getInstance(){
        return httpClientUtil;
    }


    public void get(String url, final OnHttpClientListener onHttpClientListener ){
        asyncHttpClient.get(url,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, String content) {
                super.onSuccess(statusCode, content);
                if(onHttpClientListener!=null){
                    onHttpClientListener.onSuccess(content);
                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                if(onHttpClientListener!=null){
                    onHttpClientListener.onFailure(content);
                }
            }
        });
    }

    public interface OnHttpClientListener{
        void onSuccess(String json);
        void onFailure(String message);
    }

}
