package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.MainActivity
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.event.MsgEvent
import kotlinx.android.synthetic.main.fragment_rxbus_top.*

/**
 * Created by L on 2017/6/9.
 */
class RxBusTopFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rxbus_top, null);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        btn_demo_rxbus_top.setOnClickListener {
            if (activity is MainActivity) {
                (activity as MainActivity).mRxBus.send(MsgEvent("这是一条来自TopFragment的消息"))
            }
        }
    }

}