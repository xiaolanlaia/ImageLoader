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
import com.renxing.moduleImageLoader.imageUtils.ImageLoaderUtils
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.config.CACHE_STRATEGY_NONE
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.config.ImgConfigImpl
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.transformation.RoundedCornersTransformation

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





//        RXImageLoader.loadBorderCircleImage(errUrl,test_iv_1,29,29,resources.getColor(R.color.color_FFCFF3),1f,R.mipmap.default_photo)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_url                            ->{
//                RXImageLoader.loadBorderCircleImage(url,test_iv_1,resources.getColor(R.color.color_FFCFF3,null),1f,R.mipmap.default_photo)
//                RXImageLoader.pauseRequests(test_iv_1.context)

                //                    ImageLoadUtil.loadIv(mActivity, charmList.get(0), ivCharmOne);


                val errUrl = "https://voimigo.chongqiwawa6.com/album/202205/16529906286987503.jpg"
                val errGifUrl = "https://voimigo.chongqiwawa6.com/svga20210526kne9q64x1618212139664.gif"
//        RXImageLoader.loadBorderCircleImage(
//            errUrl,
//            test_iv_1,
//            resources.getColor(R.color.color_FFCFF3),
//            1f,
//            R.mipmap.default_photo
//        )

                RXImageLoader.loadGif(errGifUrl,test_iv_1)

            }
            R.id.btn_id                             ->{
            }
            R.id.btn_url_placeholder                ->{
            }
            R.id.btn_id_placeholder                 ->{
            }
            R.id.btn_special                        ->{
                RXImageLoader.loadImage(this,
                    ImgConfigImpl
                        .builder()
                        .url(ImageLoaderUtils.appendUrl(url, width, height, false))
                        .imageView(test_iv_1)
                        .build())
            }
            R.id.btn_special_placeholder            ->{
                RXImageLoader.loadImage(this,
                    ImgConfigImpl
                        .builder()
                        .url(ImageLoaderUtils.appendUrl(url, width, height, false))
                        .placeholder(placeHoldId)
                        .errorPic(placeHoldId)
                        .imageView(test_iv_1)
                        .build())
            }
            R.id.btn_url_fitCenter                  ->{
            }
            R.id.btn_id_fitCenter                   ->{
            }
            R.id.btn_url_centerCrop                 ->{
            }
            R.id.btn_id_centerCrop                  ->{
            }
            R.id.btn_url_centerInside               ->{
            }
            R.id.btn_id_centerInside                ->{
            }
            R.id.btn_url_skipCache                  ->{
            }
            R.id.btn_id_skipCache                   ->{
            }
            R.id.btn_url_skipCache_special          ->{
                RXImageLoader.loadImage(this,
                    ImgConfigImpl
                        .builder()
                        .url(ImageLoaderUtils.appendUrl(url, width, height, false))
                        .cacheStrategy(CACHE_STRATEGY_NONE)
                        .imageView(test_iv_1)
                        .build())
            }
            R.id.btn_url_circle                     ->{
            }
            R.id.btn_id_circle                      ->{
            }
            R.id.btn_url_corner                     ->{

            }
            R.id.btn_id_corner                      ->{
            }

            R.id.btn_next_page                      ->{
                startActivity(Intent(this@MainActivity, SecondActivity::class.java))

            }

        }

    }
}