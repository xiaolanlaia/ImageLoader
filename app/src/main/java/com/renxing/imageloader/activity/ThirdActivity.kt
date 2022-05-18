package com.renxing.imageloader.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.imageloader.R
import com.renxing.imageloader.*
import com.renxing.moduleImageLoader.RXImageLoader

class ThirdActivity : AppCompatActivity() , View.OnClickListener {
    lateinit var btn_url_gif_corner: Button
    lateinit var btn_id_gif_corner: Button
    lateinit var btn_url_gif_corner_playTimes: Button
    lateinit var btn_id_gif_corner_playTimes: Button
    lateinit var btn_url_gif_corner_border: Button
    lateinit var btn_id_gif_corner_border: Button
    lateinit var btn_url_gif_corner_border_playTimes: Button
    lateinit var btn_id_gif_corner_border_playTimes: Button
    lateinit var btn_url_CustomTarget: Button
    lateinit var btn_id_CustomTarget: Button
    lateinit var btn_url_png9: Button
    lateinit var btn_id_png9: Button

    lateinit var btn_next_page: Button
    lateinit var test_tv_1: TextView
    lateinit var test_tv_2: TextView
    lateinit var test_tv_3: TextView
    lateinit var test_iv_1: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
        btn_url_gif_corner = findViewById(R.id.btn_url_gif_corner)
        btn_id_gif_corner = findViewById(R.id.btn_id_gif_corner)
        btn_url_gif_corner_playTimes = findViewById(R.id.btn_url_gif_corner_playTimes)
        btn_id_gif_corner_playTimes = findViewById(R.id.btn_id_gif_corner_playTimes)
        btn_url_gif_corner_border = findViewById(R.id.btn_url_gif_corner_border)
        btn_id_gif_corner_border = findViewById(R.id.btn_id_gif_corner_border)
        btn_url_gif_corner_border_playTimes = findViewById(R.id.btn_url_gif_corner_border_playTimes)
        btn_id_gif_corner_border_playTimes = findViewById(R.id.btn_id_gif_corner_border_playTimes)
        btn_url_CustomTarget = findViewById(R.id.btn_url_CustomTarget)
        btn_id_CustomTarget = findViewById(R.id.btn_id_CustomTarget)
        btn_url_png9 = findViewById(R.id.btn_url_png9)
        btn_id_png9 = findViewById(R.id.btn_id_png9)

        btn_next_page = findViewById(R.id.btn_next_page)
        btn_next_page.setOnClickListener(this)
        test_tv_1 = findViewById(R.id.test_tv_1)
        test_tv_2 = findViewById(R.id.test_tv_2)
        test_tv_3 = findViewById(R.id.test_tv_3)
        test_iv_1 = findViewById(R.id.test_iv_1)

        btn_url_gif_corner.setOnClickListener(this)
        btn_id_gif_corner.setOnClickListener(this)
        btn_url_gif_corner_playTimes.setOnClickListener(this)
        btn_id_gif_corner_playTimes.setOnClickListener(this)
        btn_url_gif_corner_border.setOnClickListener(this)
        btn_id_gif_corner_border.setOnClickListener(this)
        btn_url_gif_corner_border_playTimes.setOnClickListener(this)
        btn_id_gif_corner_border_playTimes.setOnClickListener(this)
        btn_url_CustomTarget.setOnClickListener(this)
        btn_id_CustomTarget.setOnClickListener(this)
        btn_url_png9.setOnClickListener(this)
        btn_id_png9.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_url_gif_corner -> {
                RXImageLoader.loadCornerGif(gifUrl, test_iv_1, cornerWidth)
            }
            R.id.btn_id_gif_corner -> {
                RXImageLoader.loadCornerGif(gifId, test_iv_1, cornerWidth)
            }
            R.id.btn_url_gif_corner_playTimes -> {
                RXImageLoader.loadCornerGif(playTimes, gifUrl, test_iv_1, cornerWidth)
            }
            R.id.btn_id_gif_corner_playTimes -> {
                RXImageLoader.loadCornerGif(playTimes, gifId, test_iv_1, cornerWidth)
            }
            R.id.btn_url_gif_corner_border -> {
                RXImageLoader.loadBorderCornerGif(
                    gifUrl,
                    test_iv_1,
                    borderColor,
                    borderWidth,
                    cornerWidth
                )
            }
            R.id.btn_id_gif_corner_border -> {
                RXImageLoader.loadBorderCornerGif(
                    gifId,
                    test_iv_1,
                    borderColor,
                    borderWidth,
                    cornerWidth
                )
            }
            R.id.btn_url_gif_corner_border_playTimes -> {
                RXImageLoader.loadBorderCornerGif(
                    playTimes,
                    gifUrl,
                    test_iv_1,
                    borderColor,
                    borderWidth,
                    cornerWidth,
                )
            }
            R.id.btn_id_gif_corner_border_playTimes -> {
                RXImageLoader.loadBorderCornerGif(
                    playTimes,
                    gifId,
                    test_iv_1,
                    borderColor,
                    borderWidth,
                    cornerWidth,
                )
            }
            R.id.btn_url_CustomTarget -> {

                //todo
            }
            R.id.btn_id_CustomTarget -> {
                //todo
            }
            R.id.btn_url_png9 -> {
                RXImageLoader.load9Png(pngUrl, test_tv_1)
            }
            R.id.btn_id_png9 -> {
                RXImageLoader.load9Png(pngId, test_tv_1)
            }
            R.id.btn_next_page -> {
                startActivity(Intent(this@ThirdActivity, FourthActivity::class.java))
            }
        }
    }
}