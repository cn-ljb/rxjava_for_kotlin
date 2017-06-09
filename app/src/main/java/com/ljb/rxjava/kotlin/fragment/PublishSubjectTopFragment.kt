package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.MainActivity
import com.ljb.rxjava.kotlin.R
import kotlinx.android.synthetic.main.fragment_publish_top.*

/**
 * Created by L on 2017/6/9.
 */
class PublishSubjectTopFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_publish_top, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        btn_send.setOnClickListener {
            val result = et_input.text.toString().trim()
            if (activity is MainActivity) {
                //发送消息
                (activity as MainActivity).mPublishSubject.onNext(result)
            }
        }
    }
}