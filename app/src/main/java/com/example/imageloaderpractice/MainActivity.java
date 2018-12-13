package com.example.imageloaderpractice;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageLoader imageLoader = new ImageLoader();
        //使用内存缓存
        imageLoader.setImageCache(new MemoryCache());
        //使用SD卡缓存
        imageLoader.setImageCache(new DiskCache());
        //使用双缓存
        imageLoader.setImageCache(new DoubleCache());
        //使用自定义的图片缓存实现
        imageLoader.setImageCache(new ImageCache() {
            @Override
            public Bitmap get(String url) {
                return null;
            }

            @Override
            public void put(String url, Bitmap bitmap) {

                //缓存图片；
            }
        });
    }
}
