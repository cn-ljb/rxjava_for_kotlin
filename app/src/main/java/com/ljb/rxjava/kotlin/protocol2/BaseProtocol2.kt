package com.ljb.rxjava.kotlin.protocol

import com.google.gson.Gson
import com.ljb.rxjava.kotlin.net.XgoHttpClient
import io.reactivex.Observable
import io.reactivex.Observable.create

/**
 * Created by L on 2017/6/8.
 */
abstract class BaseProtocol2 {

    private val mGson by lazy { Gson() }

    /**
     *  创建一个被观察者(被订阅者)对象
     *  @param url
     *  @param method
     *  @param params
     */
    protected fun <T> createObservable(url: String, method: String, params: Map<String, String>?, clazz: Class<T>): Observable<T> {
        return create {
            val request = XgoHttpClient.getRequest(url, method, params)
            val json = XgoHttpClient.execute2String(request)
            if (!json.isNullOrBlank()) {
                it.onNext(mGson.fromJson(json, clazz))
                it.onComplete()
            } else {
                it.onError(Throwable("not data"))
            }
        }
    }

    protected fun <T> createObservable(url: String, method: String, clazz: Class<T>): Observable<T> {
        return createObservable(url, method, null, clazz)
    }


}
