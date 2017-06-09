package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jakewharton.rxbinding2.view.RxView
import com.ljb.rxjava.kotlin.R
import kotlinx.android.synthetic.main.fragment_not_more_click.*
import java.util.concurrent.TimeUnit

/**
 * Created by L on 2017/6/8.
 */

class NotMoreClickFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_not_more_click, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notMoreClick()
    }

    private fun notMoreClick() {
        RxView.clicks(btn_click)
                .throttleFirst(3000, TimeUnit.MILLISECONDS)
                .subscribe { Toast.makeText(context, R.string.des_demo_not_more_click, Toast.LENGTH_SHORT).show() }
    }
}