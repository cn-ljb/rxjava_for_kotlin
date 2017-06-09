package com.ljb.rxjava.kotlin.protocol

import com.ljb.rxjava.kotlin.net.XgoHttpClient
import io.reactivex.Observable

/**
 * Created by L on 2017/6/8.
 */
abstract class BaseProtocol {
    /**
     *  创建一个被观察者(被订阅者)对象
     *  @param url
     *  @param method
     *  @param params
     */
    protected fun createObservable(url: String, method: String, params: Map<String, String>?): Observable<String> {
        return Observable.create {
            val request = XgoHttpClient.getRequest(url, method, params)
            val json = XgoHttpClient.execute2String(request)
            if (json.isNullOrEmpty()) {
                it.onError(Throwable("not data"))
            } else {
                it.onNext(json)
                it.onComplete()
            }
        }
    }

    protected fun createObservable(url: String, method: String): Observable<String> {
        return createObservable(url, method, null)
    }


}
