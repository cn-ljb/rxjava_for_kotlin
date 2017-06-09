package com.ljb.rxjava.kotlin.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.ljb.rxjava.kotlin.R
import kotlinx.android.synthetic.main.fragment_checkbox_update.*

/**
 * Created by L on 2017/6/8.
 */

class CheckBoxUpdateFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_checkbox_update, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_login.setOnClickListener {
            Toast.makeText(context, R.string.login, Toast.LENGTH_SHORT).show()
        }
        check_update1()
        check_update2()
    }


    private fun check_update2() {
        RxCompoundButton.checkedChanges(cb_2)
                .subscribe {
                    btn_login.isClickable = it
                    btn_login.setBackgroundResource(if (it) R.color.can_login else R.color.not_login)
                }
    }

    private fun check_update1() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val xxFunction = RxSharedPreferences.create(preferences).getBoolean("xxFunction", false)
        cb_1.isChecked = xxFunction.get()

        RxCompoundButton
                .checkedChanges(cb_1)
                .subscribe {
                    xxFunction.set(it)
                }
    }

}

