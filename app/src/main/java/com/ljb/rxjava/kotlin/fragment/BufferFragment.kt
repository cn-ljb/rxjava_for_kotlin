package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_buffer.*

/**
 * Created by L on 2017/6/9.
 */

class BufferFragment : Fragment() {

    private var mBufferSkipDisposable: Disposable? = null
    private var mBufferDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_buffer, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        demoBufferCount()
        demoBufferCountSkip()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mBufferDisposable)
        RxUtils.dispose(mBufferSkipDisposable)
    }


    private fun demoBufferCountSkip() {
        RxUtils.dispose(mBufferSkipDisposable)
        mBufferSkipDisposable = RxView.clicks(btn_buffer_count_skip)
                .doOnNext { tv_output.text = "result: " }
                .flatMap { Observable.fromIterable(et_input.text.toList()) }
                .buffer(2, 3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { tv_output.text = "${tv_output.text} $it" }
    }


    private fun demoBufferCount() {
        RxUtils.dispose(mBufferDisposable)
        mBufferDisposable = RxView.clicks(btn_buffer_count)
                .buffer(3)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Toast.makeText(context, R.string.des_demo_buffer_count, Toast.LENGTH_SHORT).show()
                }
    }
}