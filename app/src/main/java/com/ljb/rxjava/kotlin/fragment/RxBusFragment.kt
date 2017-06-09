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
class RxBusFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_rxbus_demo, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initFragment()
    }

    private fun initFragment() {
        val topFragment = RxBusTopFragment()
        val bottomFragment = RxBusBottomFragment()

        childFragmentManager.beginTransaction()
                .replace(R.id.demo_rxbus_frag_1, topFragment, topFragment::class.simpleName)
                .replace(R.id.demo_rxbus_frag_2, bottomFragment, bottomFragment::class.simpleName)
                .commit()
    }
}