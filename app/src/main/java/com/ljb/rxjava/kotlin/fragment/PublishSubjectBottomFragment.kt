package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.MainActivity
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_publish_bottom.*

/**
 * Created by L on 2017/6/9.
 */
class PublishSubjectBottomFragment : Fragment() {

    private var mSubjectDisposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_publish_bottom, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mSubjectDisposable)
    }

    private fun initData() {
        if (activity is MainActivity) {
            //订阅消息
             mSubjectDisposable = (activity as MainActivity).mPublishSubject.subscribe { tv_result.text = it }
        }
    }
}