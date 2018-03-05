package com.vit.vitwanandroid.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.vit.vitwanandroid.net.interceptor.LogInterceptor;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author kewz
 * @date 2018/3/5
 */

public class ApiBase {

    private static ApiService SERVICE;

    /**
     * 请求超时时间
     */
    private static final int DEFAULT_TIMEOUT = 8000;

    protected ApiService getDefault() {
        if (SERVICE == null) {
            //手动创建一个OkHttpClient
            OkHttpClient.Builder client = new OkHttpClient.Builder();
            client.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);

            client.addInterceptor(new LogInterceptor());
            Gson gson = new GsonBuilder()
                    //配置你的Gson
                    .setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create();
            SERVICE = new Retrofit.Builder()
                    .baseUrl(ApiUrl.BASE_URL)
                    .client(client.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(ApiService.class);
        }
        return SERVICE;
    }

    /**
     * 申请线程调度
     *
     * @return
     */
    protected <T> ObservableTransformer<HttpResult<T>, T> applySchedulers() {
        return new ObservableTransformer<HttpResult<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<HttpResult<T>> upstream) {
                return upstream
                        .flatMap(new Function<HttpResult<T>, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(HttpResult<T> tHttpResult) throws Exception {
                                return flatResponse(tHttpResult);
                            }
                        }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 预处理
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<T> flatResponse(final HttpResult<T> response) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                if (response.getErrorCode() == 0) {
                    emitter.onNext(response.getData());
                } else {
                    //网络请求错误
                }

            }
        });
    }

}
