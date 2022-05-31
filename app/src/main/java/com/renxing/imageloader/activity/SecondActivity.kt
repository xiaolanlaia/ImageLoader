package com.renxing.imageloader.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.imageloader.R
import com.renxing.imageloader.*
import com.renxing.moduleImageLoader.RXImageLoader
import com.renxing.moduleImageLoader.imageUtils.enumUtils.CornerTypeEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum

class SecondActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var btn_url_corner_special             : Button
    lateinit var btn_id_corner_special              : Button
    lateinit var btn_bitmap_corner                  : Button
    lateinit var btn_url_border_circle              : Button
    lateinit var btn_id_border_circle               : Button
    lateinit var btn_url_border_corner              : Button
    lateinit var btn_id_border_corner               : Button
    lateinit var btn_url_gif                        : Button
    lateinit var btn_id_gif                         : Button
    lateinit var btn_url_gif_playTimes              : Button
    lateinit var btn_id_gif_playTimes               : Button
    lateinit var btn_url_gif_circle                 : Button
    lateinit var btn_id_gif_circle                  : Button
    lateinit var btn_url_gif_circle_playTimes       : Button
    lateinit var btn_id_gif_circle_playTimes        : Button

    lateinit var btn_next_page                    :Button
    lateinit var test_tv_1                        : TextView
    lateinit var test_tv_2                        : TextView
    lateinit var test_tv_3                        : TextView
    lateinit var test_iv_1                        : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        btn_url_corner_special             = findViewById(R.id.btn_url_corner_special             )
        btn_id_corner_special              = findViewById(R.id.btn_id_corner_special              )
        btn_bitmap_corner                  = findViewById(R.id.btn_bitmap_corner                  )
        btn_url_border_circle              = findViewById(R.id.btn_url_border_circle              )
        btn_id_border_circle               = findViewById(R.id.btn_id_border_circle               )
        btn_url_border_corner              = findViewById(R.id.btn_url_border_corner              )
        btn_id_border_corner               = findViewById(R.id.btn_id_border_corner               )
        btn_url_gif                        = findViewById(R.id.btn_url_gif                        )
        btn_id_gif                         = findViewById(R.id.btn_id_gif                         )
        btn_url_gif_playTimes              = findViewById(R.id.btn_url_gif_playTimes              )
        btn_id_gif_playTimes               = findViewById(R.id.btn_id_gif_playTimes               )
        btn_url_gif_circle                 = findViewById(R.id.btn_url_gif_circle                 )
        btn_id_gif_circle                  = findViewById(R.id.btn_id_gif_circle                  )
        btn_url_gif_circle_playTimes       = findViewById(R.id.btn_url_gif_circle_playTimes       )
        btn_id_gif_circle_playTimes        = findViewById(R.id.btn_id_gif_circle_playTimes        )


        btn_next_page                    = findViewById(R.id.btn_next_page                        )
        test_tv_1                        = findViewById(R.id.test_tv_1                        )
        test_tv_2                        = findViewById(R.id.test_tv_2                        )
        test_tv_3                        = findViewById(R.id.test_tv_3                        )
        test_iv_1                        = findViewById(R.id.test_iv_1                        )
        btn_next_page                       .setOnClickListener(this)


        btn_url_corner_special             .setOnClickListener(this)
        btn_id_corner_special              .setOnClickListener(this)
        btn_bitmap_corner                  .setOnClickListener(this)
        btn_url_border_circle              .setOnClickListener(this)
        btn_id_border_circle               .setOnClickListener(this)
        btn_url_border_corner              .setOnClickListener(this)
        btn_id_border_corner               .setOnClickListener(this)
        btn_url_gif                        .setOnClickListener(this)
        btn_id_gif                         .setOnClickListener(this)
        btn_url_gif_playTimes              .setOnClickListener(this)
        btn_id_gif_playTimes               .setOnClickListener(this)
        btn_url_gif_circle                 .setOnClickListener(this)
        btn_id_gif_circle                  .setOnClickListener(this)
        btn_url_gif_circle_playTimes       .setOnClickListener(this)
        btn_id_gif_circle_playTimes        .setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.btn_url_corner_special             ->{
                RXImageLoader.loadCornersImage(
                    url,test_iv_1, cornerWidth,
                    CornerTypeEnum.RIGHT)

            }
            R.id.btn_id_corner_special              ->{
                RXImageLoader.loadCornersImage(
                    imgId,test_iv_1, cornerWidth,
                    CornerTypeEnum.LEFT)
            }
            R.id.btn_bitmap_corner                  ->{
                //todo bitmap是怎么做的
//                RXImageLoader.loadRoundedCornersImage(imgId,test_iv_1,radius,CornerTypeEnum.LEFT)

            }
            R.id.btn_url_border_circle              ->{
                RXImageLoader.loadBorderCircleImage(url,test_iv_1, borderColor, borderWidth)
            }
            R.id.btn_id_border_circle               ->{
                RXImageLoader.loadBorderCircleImage(imgId,test_iv_1, borderColor, borderWidth)
            }
            R.id.btn_url_border_corner              ->{
                RXImageLoader.loadBorderCornerImage(
                    url,test_iv_1,
                    borderColor,
                    borderWidth,
                    cornerWidth
                )
            }
            R.id.btn_id_border_corner               ->{
                RXImageLoader.loadBorderCornerImage(
                    imgId,test_iv_1,
                    borderColor,
                    borderWidth,
                    cornerWidth
                )
            }
            R.id.btn_url_gif                        ->{
//                RXImageLoader.loadGif(gifUrl,test_iv_1)

            }
            R.id.btn_id_gif                         ->{
//                RXImageLoader.loadGif(gifId,test_iv_1)
            }
            R.id.btn_url_gif_playTimes              ->{
                RXImageLoader.loadGif(playTimes, gifUrl,test_iv_1)
            }
            R.id.btn_id_gif_playTimes               ->{
                RXImageLoader.loadGif(playTimes, gifId,test_iv_1)
            }
            R.id.btn_url_gif_circle                 ->{
                RXImageLoader.loadCircleGif(gifUrl,test_iv_1)
            }
            R.id.btn_id_gif_circle                  ->{
                RXImageLoader.loadCircleGif(gifId,test_iv_1)
            }
            R.id.btn_url_gif_circle_playTimes       ->{
                RXImageLoader.loadCircleGif(playTimes, gifUrl,test_iv_1)
            }
            R.id.btn_id_gif_circle_playTimes        ->{
                RXImageLoader.loadCircleGif(playTimes, gifId,test_iv_1)
            }

            R.id.btn_next_page                      ->{
                startActivity(Intent(this@SecondActivity, ThirdActivity::class.java))
            }
        }
    }
}