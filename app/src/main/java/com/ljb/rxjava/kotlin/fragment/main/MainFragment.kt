package com.ljb.rxjava.kotlin.fragment.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ljb.rxjava.kotlin.R
import com.ljb.rxjava.kotlin.fragment.*
import com.ljb.rxjava.kotlin.log.XgoLog
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by L on 2017/6/8.
 */

class MainFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_main, null)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        btn_net.setOnClickListener(this)
        btn_net2.setOnClickListener(this)
        btn_not_more_click.setOnClickListener(this)
        btn_checkbox_state_update.setOnClickListener(this)
        btn_text_change.setOnClickListener(this)
        btn_buffer.setOnClickListener(this)
        btn_zip.setOnClickListener(this)
        btn_concat.setOnClickListener(this)
        btn_loop.setOnClickListener(this)
        btn_timer.setOnClickListener(this)
        btn_publish.setOnClickListener(this)
        btn_rxbus.setOnClickListener(this)
        btn_reuse_subscriber.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_net -> open(NetFragment())
            R.id.btn_net2 -> open(NetFragment2())
            R.id.btn_not_more_click -> open(NotMoreClickFragment())
            R.id.btn_checkbox_state_update -> open(CheckBoxUpdateFragment())
            R.id.btn_text_change -> open(DebounceFragment())
            R.id.btn_buffer -> open(BufferFragment())
            R.id.btn_zip -> open(ZipFragment())
            R.id.btn_concat -> open(ConcatFragment())
            R.id.btn_loop -> open(LoopFragment())
            R.id.btn_timer -> open(TimerFragment())
            R.id.btn_publish -> open(PublishSubjectFragment())
            R.id.btn_rxbus -> open(RxBusFragment())
            R.id.btn_reuse_subscriber -> open(ReuseSubscriberFragment())
        }
    }

    /**
     * 开启新的Fragment
     */
    private fun open(fragment: Fragment) {
        val tag = fragment.javaClass.simpleName
        XgoLog.i("open :: $tag")
        activity.supportFragmentManager
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.main_content, fragment, tag)
                .commit()
    }


}
