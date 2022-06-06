package com.renxing.imageloader.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.imageloader.R
import com.renxing.imageloader.bigPic
import com.renxing.imageloader.nullUrl
import com.renxing.imageloader.placeHoldId
import com.renxing.imageloader.placeHoldId2
import com.renxing.moduleImageLoader.RXImageLoader

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
                RXImageLoader.loadCircleImage(nullUrl,test_iv_1,placeHoldId)
//                RXImageLoader.loadCornersImage(bigPic,test_iv_1,100f, placeHoldId)
//                RXImageLoader.loadImageWithSkipCache(nullUrl,test_iv_1,360,360,placeHoldId)
            }
            R.id.id_special         -> {
                RXImageLoader.loadCircleImage(bigPic,test_iv_1, placeHoldId2)
//                RXImageLoader.loadCornersImage(bigPic,test_iv_1,100f, placeHoldId2)


            }
        }
    }
}