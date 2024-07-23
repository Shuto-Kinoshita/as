package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.ListView


class Newslist : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.newslist)

        // ボタン要素（オブジェクト）を取得
        val buttonToMainActivity = findViewById<Button>(R.id.button2)

        // ボタンタップ時のイベントリスナー
        buttonToMainActivity.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // リストデータの作成
        val dataList = arrayListOf<Data>()
        for (i in 0..15){
            dataList.add(Data().apply {
                icon = "${i}/${i}"
                title = "${i}番目のタイトル"
                text =  "${i}番目のテキスト"
            })
        }
        // アダプターをセット
        val listView = findViewById<ListView>(R.id.custom_list_view)
        val adapter = CustomAdapter(this, dataList)
        listView.adapter = adapter
    }
}

