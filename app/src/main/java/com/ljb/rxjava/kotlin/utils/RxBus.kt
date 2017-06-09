package com.ljb.rxjava.kotlin.utils

import io.reactivex.subjects.PublishSubject

/**
 * RxBus
 */
class RxBus {

    private val _bus by lazy {
        PublishSubject.create<Any>()
    }//线程不安全

    //    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());  //线程安全


    fun send(o: Any) {
        _bus.onNext(o)
    }

    fun toObserverable(): PublishSubject<Any> {
        return _bus
    }

    /**
     * 是否含有观察者
     */
    fun hasObservers(): Boolean {
        return _bus.hasObservers()
    }
}