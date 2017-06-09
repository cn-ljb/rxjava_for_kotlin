package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.R

/**
 * Created by L on 2017/6/9.
 */
class PublishSubjectFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_publish, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        val topFragment = PublishSubjectTopFragment()
        val bottomFragment = PublishSubjectBottomFragment()
        childFragmentManager.beginTransaction()
                .replace(R.id.fl_top, topFragment, topFragment::class.java.simpleName)
                .replace(R.id.fl_bottom, bottomFragment, bottomFragment::class.java.simpleName)
                .commit()
    }

}