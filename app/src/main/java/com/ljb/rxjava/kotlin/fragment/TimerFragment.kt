package com.ljb.rxjava.kotlin.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_timer.*
import java.util.concurrent.TimeUnit

/**
 * Created by L on 2017/6/9.
 */
class TimerFragment : Fragment() {

    private var mTimeDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_timer, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        starTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mTimeDisposable)
    }

    private fun starTimer() {
        mTimeDisposable = Observable.timer(3000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    iv_welcome.visibility = View.VISIBLE
                    ObjectAnimator.ofFloat(iv_welcome, "alpha", 0F, 1F).setDuration(500).start()
                }
    }


}