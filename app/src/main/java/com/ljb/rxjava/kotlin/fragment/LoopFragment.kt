package com.ljb.rxjava.kotlin.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.utils.RxUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_loop.*
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by L on 2017/6/9.
 */
class LoopFragment : Fragment() {

    private val mData = intArrayOf(R.drawable.pic_1, R.drawable.pic_2, R.drawable.pic_3)

    private var mLoopDisposable: Disposable? = null
    private var mLoopAdapter: PicLoopAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_loop, null)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initViewPager()
        autoLoop()
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLoop()
    }

    private fun initListener() {
        btn_start_loop.setOnClickListener {
            autoLoop()
        }

        btn_stop_loop.setOnClickListener {
            stopLoop()
        }
    }

    private fun stopLoop() {
        RxUtils.dispose(mLoopDisposable)
    }


    private fun autoLoop() {
        if (mLoopDisposable == null || mLoopDisposable!!.isDisposed) {

            mLoopDisposable = Observable.interval(3000, 3000, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        viewpager.currentItem++
                    }
        }
    }


    private fun initViewPager() {
        mLoopAdapter = PicLoopAdapter(mData, context)
        viewpager.adapter = mLoopAdapter
    }


    private class PicLoopAdapter(private val mData: IntArray, private val context: Context) : PagerAdapter() {

        private val mCacheViews = ArrayList<ImageView>()

        override fun getCount(): Int {
            return Integer.MAX_VALUE
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): View {
            val index = position % mData.size
            val iv: ImageView
            if (mCacheViews.size > 0) {
                iv = mCacheViews.removeAt(0)
            } else {
                iv = ImageView(context)
                iv.layoutParams = ViewPager.LayoutParams()
                iv.scaleType = ImageView.ScaleType.CENTER_CROP
            }
            iv.setImageResource(mData[index])
            container.addView(iv)
            return iv
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
            mCacheViews.add(`object` as ImageView)
        }
    }

}