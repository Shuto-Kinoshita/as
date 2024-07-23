package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.lang.RuntimeException

import android.app.AlertDialog
import android.app.Activity

//①Fragmentを継承したclassを作る　これがなきゃ始まらない
class ButtonFragment:Fragment() {

    //③onAttach:フラグメントがアクティビティに配置されたときに呼ばれる
    //ここではコールバックインターフェース（②）がちゃんと実装されていることを確認している
    override fun onAttach(context: Context){
        super.onAttach(context)
        if(context !is OnButtonClickListener)
            throw RuntimeException("リスナーを実装してください")
    }
    //③

    //④フラグメントのレイアウトを決定している（onCreateView）
    override fun onCreateView(inflater:LayoutInflater,
                              container: ViewGroup?, savedInstanceState:Bundle?): View {
        //⑤ボタンが押された時のリスナーをセット
        val view = inflater.inflate(R.layout.button_fragment, container, false)
        view.findViewById<Button>(R.id.button)
            .setOnClickListener {

                showCustomDialog()

                //⑥コールバックインターフェースを実装しているアクティビティにイベントを知らせる
                val listener = context as? OnButtonClickListener
                listener?.onButtonClicked()
                //⑥
            }
        return view
    }

   private fun showCustomDialog() {
       val customView = layoutInflater.inflate(R.layout.custom_dialog, null)
       val dialog = AlertDialog.Builder(requireContext(), R.style.RoundedCornersDialog)
           .setView(customView)
           .create()

        customView.findViewById<Button>(R.id.positiveButton).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    //②コールバックインターフェースを定義する
    interface OnButtonClickListener{
        fun onButtonClicked()
    }
    //②
}
//①

