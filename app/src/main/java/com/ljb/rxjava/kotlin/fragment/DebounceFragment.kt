package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.os.Looper
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.log.XgoLog
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search_text_change.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by L on 2017/6/9.
 */

class DebounceFragment : Fragment() {

    private var mSearchDisposable: Disposable? = null
    private var mAdapter: ArrayAdapter<String>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_text_change, null);
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchKeyWordDemo()
    }


    override fun onDestroy() {
        super.onDestroy()
        RxUtils.dispose(mSearchDisposable)
    }


    private fun searchKeyWordDemo() {
        mSearchDisposable = RxTextView.textChangeEvents(et_search)
                .debounce(150, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { clearPage(it) }
                .filter { it.text().isNotEmpty() }
                .switchMap { getKeyWordFormNet(it.text().toString().trim()).subscribeOn(Schedulers.io()) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    initPage(it)
                }, {
                    it.printStackTrace()
                })
    }

    private fun clearPage(event: TextViewTextChangeEvent) {
        val b = Thread.currentThread() === Looper.getMainLooper().thread
        XgoLog.d("clearPage主线程::" + b)
        if (mAdapter != null && event.text().isEmpty()) {
            mAdapter!!.clear()
            mAdapter!!.notifyDataSetChanged()
        }
    }

    private fun initPage(keyWords: List<String>) {
        val b = Thread.currentThread() === Looper.getMainLooper().thread
        XgoLog.d("initPage主线程::" + b)
        if (mAdapter == null) {
            mAdapter = ArrayAdapter(context, R.layout.item_list, R.id.tv_text, keyWords)
            lv_list.adapter = mAdapter
            lv_list.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
                Toast.makeText(context, "搜索:" + mAdapter?.getItem(position), Toast.LENGTH_SHORT).show()
            }
        } else {
            mAdapter?.clear()
            mAdapter?.addAll(keyWords)
            mAdapter?.notifyDataSetChanged()
        }
    }

    /**
     * 模拟网路接口获取匹配到的关键字列表
     */
    private fun getKeyWordFormNet(key: String): Observable<List<String>> {
        return Observable.create {
            val b = Thread.currentThread() === Looper.getMainLooper().thread
            XgoLog.d("网络IO线程::" + !b)

            SystemClock.sleep(500)

            //这里是网络请求操作...
            val datas = (0..9).mapTo(ArrayList<String>()) { "KeyWord:" + key + it }

            it.onNext(datas)
            it.onComplete()
        }
    }
}