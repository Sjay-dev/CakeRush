package com.example.cakerush.ui.theme.View

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            , WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}