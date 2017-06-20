package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.MainActivity
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.event.MsgEvent
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_rxbus_bottom.*
import java.util.concurrent.TimeUnit

/**
 * Created by L on 2017/6/9.
 */
class RxBusBottomFragment : Fragment() {

    private var mDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rxbus_bottom, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }


    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mDisposable)
    }


    private fun initData() {
        if (activity is MainActivity) {
            val publish = (activity as MainActivity).mRxBus.toObservable().publish()

            //订阅1
            publish.filter {
                it is MsgEvent
            }.subscribe {
                val msgEvent = it as MsgEvent
                showText(msgEvent)
            }

            //订阅2
            publish.publish { it.buffer(it.debounce(1, TimeUnit.SECONDS)) }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { showCount(it.size) }

            mDisposable = publish.connect()  //需手动调用connect()方法
        }
    }

    private fun showCount(size: Int) {
        demo_rxbus_tap_count.text = size.toString()
        demo_rxbus_tap_count.visibility = View.VISIBLE
        demo_rxbus_tap_count.scaleX = 1f
        demo_rxbus_tap_count.scaleY = 1f
        ViewCompat.animate(demo_rxbus_tap_count)
                .scaleXBy(-1f)
                .scaleYBy(-1f)
                .setDuration(800).startDelay = 100
    }

    private fun showText(msgEvent: MsgEvent) {
        demo_rxbus_tap_txt.visibility = View.VISIBLE
        demo_rxbus_tap_txt.alpha = 1F
        ViewCompat.animate(demo_rxbus_tap_txt).alpha(-1F).setDuration(400).start()
    }
}