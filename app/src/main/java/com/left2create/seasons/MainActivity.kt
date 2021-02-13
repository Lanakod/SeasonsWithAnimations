package com.left2create.seasons

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import kotlin.system.exitProcess

lateinit var spring: ImageView
lateinit var autumn: ImageView
lateinit var summer: ImageView
lateinit var winter: ImageView
lateinit var result: TextView
private var status = true
private var isShown = false

private val textAnimLength: Long = 500


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spring = findViewById(R.id.imageView_spring)
        autumn = findViewById(R.id.imageView_autumn)
        summer = findViewById(R.id.imageView_summer)
        winter = findViewById(R.id.imageView_winter)
        result = findViewById(R.id.textView)

        playAnim(spring)
        playAnim(autumn)
        playAnim(summer)
        playAnim(winter)
    }

    fun seasonClick(view: View)
    {
        if(status) {
            when (view) {
                spring -> {
                    playAnim(result, "Весна")
                }
                autumn -> {
                    playAnim(result, "Осень")
                }
                summer -> {
                    playAnim(result, "Лето")
                }
                winter -> {
                    playAnim(result, "Зима")
                }
            }
        }
    }

    private fun playAnim(obj: View, text: String = "")
    {
        status = false
        when(obj)
        {
            summer -> {
                val animation = AnimationUtils.loadAnimation(this, R.anim.summer)
                obj.startAnimation(animation)
                status = true
            }
            winter -> {
                val animation = AnimationUtils.loadAnimation(this, R.anim.winter)
                obj.startAnimation(animation)
                status = true
            }
            autumn -> {
                val animation = AnimationUtils.loadAnimation(this, R.anim.autumn)
                obj.startAnimation(animation)
                status = true
            }
            spring -> {
                val animation = AnimationUtils.loadAnimation(this, R.anim.spring)
                obj.startAnimation(animation)
                status = true
            }
            result -> {
                lateinit var animation: Animation
                if(!isShown)
                {
                    animation = AnimationUtils.loadAnimation(this, R.anim.result_first)
                    result.text = text
                    obj.startAnimation(animation)
                    Handler().postDelayed({status = true
                        isShown = true}, textAnimLength)
                }
                else
                {
                    animation = AnimationUtils.loadAnimation(this, R.anim.result_second)
                    obj.startAnimation(animation)
                    Handler().postDelayed({
                        result.text = text
                        animation = AnimationUtils.loadAnimation(this, R.anim.result_first)
                        obj.startAnimation(animation)
                        Handler().postDelayed({status = true
                            isShown = true}, textAnimLength)
                    }, textAnimLength)
                }
            }
        }
    }

    fun exitClick(view: View)
    {
        exitProcess(0)
    }
}