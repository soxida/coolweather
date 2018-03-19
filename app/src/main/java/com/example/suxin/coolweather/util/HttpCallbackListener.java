package com.example.suxin.coolweather.util;

/**
 * Created by suxin on 2018/3/18.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
