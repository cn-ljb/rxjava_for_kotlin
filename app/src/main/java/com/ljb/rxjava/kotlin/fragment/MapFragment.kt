package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.log.XgoLog
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by L on 2017/7/5.
 */
class MapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?, @Nullable savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    private fun initData() {
        val list = ArrayList<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        demoMap(list)
    }

    private fun demoMap(list: ArrayList<String>) {
        Observable.just(list)
                .map {
                    updateList(it)
                }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    XgoLog.i(it.toString())
                } , {
                    XgoLog.e(it.message)
                })
    }

    private fun updateList(list: ArrayList<String>): List<String> {
        list.add("A")
        list.add("B")
        list.add("C")
        return list
    }
}