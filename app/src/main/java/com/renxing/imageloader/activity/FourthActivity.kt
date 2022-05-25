package com.renxing.imageloader.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.imageloader.R
import com.renxing.imageloader.bigPic
import com.renxing.imageloader.placeHoldId
import com.renxing.imageloader.placeHoldId2
import com.renxing.moduleImageLoader.RXImageLoader
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.config.ImgConfigImpl
import com.renxing.moduleImageLoader.loaderStrategy.glide.easyglide.transformation.RoundedCornersTransformation

class FourthActivity : AppCompatActivity() ,View.OnClickListener{

    lateinit var url_placeholder    : Button
    lateinit var id_special         : Button
    lateinit var test_iv_1          : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        test_iv_1     = findViewById(R.id.test_iv_1    )
        url_placeholder     = findViewById(R.id.url_placeholder    )
        id_special          = findViewById(R.id.id_special         )
        url_placeholder    .setOnClickListener(this)
        id_special         .setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.url_placeholder    -> {

                RXImageLoader.loadImage(this,
                    ImgConfigImpl
                        .builder()
                        .url(bigPic)
                        .transformation(RoundedCornersTransformation(100, 0))
                        .isCrossFade(false)
                        .errorPic(placeHoldId)
                        .placeholder(placeHoldId)
                        .imageView(test_iv_1)
                        .build())
            }
            R.id.id_special         -> {
                RXImageLoader.loadImage(this,
                    ImgConfigImpl
                        .builder()
                        .url(bigPic)
                        .transformation(RoundedCornersTransformation(100, 0))
                        .isCrossFade(false)
                        .errorPic(placeHoldId2)
                        .placeholder(placeHoldId2)
                        .imageView(test_iv_1)
                        .build())


            }
        }
    }
}