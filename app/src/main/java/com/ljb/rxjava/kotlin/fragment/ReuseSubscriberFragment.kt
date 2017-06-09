package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ljb.rxjava.kotlin.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_reuse_subscriber.*

/**
 * Created by L on 2017/6/9.
 */
class ReuseSubscriberFragment : Fragment() {


    val mReuseSubscriber = object : Observer<Any> {

        override fun onSubscribe(d: Disposable?) {
        }

        override fun onComplete() {
        }

        override fun onNext(t: Any?) {
            when (t) {
                is Int -> {
                    Toast.makeText(context, "The data from Btn1 !", Toast.LENGTH_SHORT).show()
                }
                is String -> {
                    Toast.makeText(context, "The data from Btn2 !", Toast.LENGTH_SHORT).show()
                }
            }
        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_reuse_subscriber, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        btn1.setOnClickListener {
            Observable.just(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mReuseSubscriber);
        }

        btn2.setOnClickListener {
            Observable.just("string")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mReuseSubscriber)
        }
    }


}