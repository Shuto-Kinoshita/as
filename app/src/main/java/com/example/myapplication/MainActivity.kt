
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
//import com.example.myapplication.R

class MainActivity : AppCompatActivity(),ButtonFragment.OnButtonClickListener {

    //ボタンがクリックされたらラベルフラグメントを呼び出す
    override fun onButtonClicked() {
        val fragment = supportFragmentManager.findFragmentByTag("labelFragment") as LabelFragment
        fragment.update()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //フラグメントの有無を確認し動的にフラグメントを追加する
        if (supportFragmentManager.findFragmentByTag("labelFragment") == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.container, newLabelFragment(0),"labelFragment")
                .commit()
        }

        // ボタン要素（オブジェクト）を取得
        val buttonToSubActivity = findViewById<Button>(R.id.button_to_sub)

        // ボタンタップ時のイベントリスナー
        buttonToSubActivity.setOnClickListener {
            val intent = Intent(this, Newslist::class.java)
            startActivity(intent)
        }
    }
}
