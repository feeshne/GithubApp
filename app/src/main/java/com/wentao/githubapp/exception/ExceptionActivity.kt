package com.wentao.githubapp.exception

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wentao.githubapp.R
import kotlin.system.exitProcess

class ExceptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_exception)
        findViewById<Button>(R.id.button_upload).setOnClickListener {
            // 上传崩溃日志到后台
            Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show()
        }
        findViewById<Button>(R.id.button_exit).setOnClickListener {
            exitProcess(0)
        }
    }
}