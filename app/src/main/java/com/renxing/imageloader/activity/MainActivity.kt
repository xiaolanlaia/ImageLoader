package com.renxing.imageloader.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.imageloader.R
import com.renxing.imageloader.*
import com.renxing.moduleImageLoader.RXImageLoader
import com.renxing.moduleImageLoader.RXImageLoader.loadCircleImage
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils.appendUrl
import com.renxing.moduleImageLoader.imageUtils.ImgLoadParams
import com.renxing.moduleImageLoader.imageUtils.enumUtils.DiskCacheStrategyEnum
import com.renxing.moduleImageLoader.imageUtils.enumUtils.TransitionEnum

class MainActivity : AppCompatActivity(),View.OnClickListener {


    lateinit var btn_url                            :Button
    lateinit var btn_id                             :Button
    lateinit var btn_url_placeholder                :Button
    lateinit var btn_id_placeholder                 :Button
    lateinit var btn_special                        :Button
    lateinit var btn_special_placeholder            :Button
    lateinit var btn_url_fitCenter                  :Button
    lateinit var btn_id_fitCenter                   :Button
    lateinit var btn_url_centerCrop                 :Button
    lateinit var btn_id_centerCrop                  :Button
    lateinit var btn_url_centerInside               :Button
    lateinit var btn_id_centerInside                :Button
    lateinit var btn_url_skipCache                  :Button
    lateinit var btn_id_skipCache                   :Button
    lateinit var btn_url_skipCache_special          : Button
    lateinit var btn_url_circle                     : Button
    lateinit var btn_id_circle                      : Button
    lateinit var btn_url_corner                     : Button
    lateinit var btn_id_corner                      : Button


    lateinit var btn_next_page                    :Button
    lateinit var test_tv_1                        :TextView
    lateinit var test_tv_2                        :TextView
    lateinit var test_tv_3                        :TextView
    lateinit var test_iv_1                        :ImageView
    lateinit var test_iv_2                        :ImageView
    lateinit var test_iv_3                        :ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_url                            = findViewById(R.id.btn_url                            )
        btn_id                             = findViewById(R.id.btn_id                             )
        btn_url_placeholder                = findViewById(R.id.btn_url_placeholder                )
        btn_id_placeholder                 = findViewById(R.id.btn_id_placeholder                 )
        btn_special                        = findViewById(R.id.btn_special                        )
        btn_special_placeholder            = findViewById(R.id.btn_special_placeholder            )
        btn_url_fitCenter                  = findViewById(R.id.btn_url_fitCenter                  )
        btn_id_fitCenter                   = findViewById(R.id.btn_id_fitCenter                   )
        btn_url_centerCrop                 = findViewById(R.id.btn_url_centerCrop                 )
        btn_id_centerCrop                  = findViewById(R.id.btn_id_centerCrop                  )
        btn_url_centerInside               = findViewById(R.id.btn_url_centerInside               )
        btn_id_centerInside                = findViewById(R.id.btn_id_centerInside                )
        btn_url_skipCache                  = findViewById(R.id.btn_url_skipCache                  )
        btn_id_skipCache                   = findViewById(R.id.btn_id_skipCache                   )
        btn_url_skipCache_special          = findViewById(R.id.btn_url_skipCache_special          )
        btn_url_circle                     = findViewById(R.id.btn_url_circle                     )
        btn_id_circle                      = findViewById(R.id.btn_id_circle                      )
        btn_url_corner                     = findViewById(R.id.btn_url_corner                     )
        btn_id_corner                      = findViewById(R.id.btn_id_corner                      )

        btn_next_page                    = findViewById(R.id.btn_next_page                    )
        test_tv_1                        = findViewById(R.id.test_tv_1                        )
        test_tv_2                        = findViewById(R.id.test_tv_2                        )
        test_tv_3                        = findViewById(R.id.test_tv_3                        )
        test_iv_1                        = findViewById(R.id.test_iv_1                        )
        test_iv_2                        = findViewById(R.id.test_iv_2                        )
        test_iv_3                        = findViewById(R.id.test_iv_3                        )
        btn_next_page                       .setOnClickListener(this)


        btn_url                            .setOnClickListener(this)
        btn_id                             .setOnClickListener(this)
        btn_url_placeholder                .setOnClickListener(this)
        btn_id_placeholder                 .setOnClickListener(this)
        btn_special                        .setOnClickListener(this)
        btn_special_placeholder            .setOnClickListener(this)
        btn_url_fitCenter                  .setOnClickListener(this)
        btn_id_fitCenter                   .setOnClickListener(this)
        btn_url_centerCrop                 .setOnClickListener(this)
        btn_id_centerCrop                  .setOnClickListener(this)
        btn_url_centerInside               .setOnClickListener(this)
        btn_id_centerInside                .setOnClickListener(this)
        btn_url_skipCache                  .setOnClickListener(this)
        btn_id_skipCache                   .setOnClickListener(this)
        btn_url_skipCache_special          .setOnClickListener(this)
        btn_url_circle                     .setOnClickListener(this)
        btn_id_circle                      .setOnClickListener(this)
        btn_url_corner                     .setOnClickListener(this)
        btn_id_corner                      .setOnClickListener(this)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_url                            ->{


                RXImageLoader.loadCircleImage(
                    ImgLoadParams(this)
                        .load("https:wwww")
                        .into(test_iv_1)
                        .transitionEnum(TransitionEnum.CenterCrop)
                        .placeholder(R.mipmap.img_1)
                        .diskcacheStrategy(DiskCacheStrategyEnum.ALL)
                )


            }
            R.id.btn_id                             ->{

            }
            R.id.btn_url_placeholder                ->{

            }
            R.id.btn_id_placeholder                 ->{
                RXImageLoader.loadImage(
                    ImgLoadParams(this)
                        .load(imgId)
                        .placeholder(placeHoldId)
                        .into(test_iv_1)
                )
            }
            R.id.btn_special                        ->{
                RXImageLoader.loadImage(
                    ImgLoadParams(this)
                        .load(url)
                        .override(width,height)
                        .into(test_iv_1)
                )

            }
            R.id.btn_special_placeholder            ->{
                RXImageLoader.loadImage(
                    ImgLoadParams(this)
                        .load(url2)
                        .override(width,height)
                        .placeholder(placeHoldId)
                        .into(test_iv_1)
                )
            }
            R.id.btn_url_fitCenter                  ->{
                RXImageLoader.loadImage(
                    ImgLoadParams(this)
                        .load(url2)
                        .fitCenter(true)
                        .into(test_iv_1)
                )
            }
            R.id.btn_id_fitCenter                   ->{
                RXImageLoader.loadImage(
                    ImgLoadParams(this)
                        .load(imgId)
                        .fitCenter(true)
                        .into(test_iv_1)
                )
            }
            R.id.btn_url_centerCrop                 ->{

//                RXImageLoader.loadImageWithCenterCrop(url,test_iv_1)
            }
            R.id.btn_id_centerCrop                  ->{
//                RXImageLoader.loadImageWithCenterCrop(imgId,test_iv_1)
            }
            R.id.btn_url_centerInside               ->{
//                RXImageLoader.loadImageWithCenterInside(url,test_iv_1)
            }
            R.id.btn_id_centerInside                ->{
//                RXImageLoader.loadImageWithCenterInside(imgId,test_iv_1)
            }
            R.id.btn_url_skipCache                  ->{
//                RXImageLoader.loadImageWithSkipCache(url,test_iv_1)
            }
            R.id.btn_id_skipCache                   ->{
//                RXImageLoader.loadImageWithSkipCache(imgId,test_iv_1)
            }
            R.id.btn_url_skipCache_special          ->{
//                RXImageLoader.loadImageWithSkipCache(url,test_iv_1, width, height)
            }
            R.id.btn_url_circle                     ->{
                RXImageLoader.loadCircleImage(
                    ImgLoadParams(this)
                        .load(url)
                        .placeholder(placeHoldId)
                        .into(test_iv_1)
                )
            }
            R.id.btn_id_circle                      ->{
//                RXImageLoader.loadCircleImage(imgId,test_iv_1)
            }
            R.id.btn_url_corner                     ->{

                RXImageLoader.loadCornersImage(
                    ImgLoadParams(this)
                        .load(url)
                        .cornerRadius(20f)
                        .placeholder(placeHoldId)
                        .into(test_iv_1)
                )
                //圆角矩形测一下
//                RXImageLoader.loadCornersImage(url,test_iv_1, cornerWidth)
            }
            R.id.btn_id_corner                      ->{
            }

            R.id.btn_next_page                      ->{
                startActivity(Intent(this@MainActivity, SecondActivity::class.java))

            }

        }

    }
}