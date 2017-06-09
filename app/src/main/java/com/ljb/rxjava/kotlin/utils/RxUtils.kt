package com.ljb.rxjava.kotlin.utils

import io.reactivex.disposables.Disposable

/**
 * Created by L on 2017/6/8.
 */

object RxUtils {

    fun dispose(disposable: Disposable?) {
        if(disposable!=null && !disposable.isDisposed){
            disposable.dispose()
        }
    }
}