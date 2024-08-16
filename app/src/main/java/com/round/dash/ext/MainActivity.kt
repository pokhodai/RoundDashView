package com.round.dash.ext

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnLayout
import com.round.dash.libs.setRoundedDashBackground

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val view = findViewById<View>(R.id.hello_world)
        view.doOnLayout {
            it.setRoundedDashBackground(
                width = it.width,
                height = it.height,
                dashThickness = resources.getDimension(R.dimen.thickness).toInt(),
                dashGap = resources.getDimension(R.dimen.gap).toInt(),
                dashLength = resources.getDimension(R.dimen.length).toInt(),
                cornerRadius = resources.getDimension(R.dimen.corner).toInt(),
                dashColorInt = Color.RED,
                backgroundColorInt = Color.GREEN
            )
        }
    }
}