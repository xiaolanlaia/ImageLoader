package com.example.imageloaderpractice;

import android.graphics.Bitmap;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by W on 2018/12/13.
 */

public class DiskCache implements ImageCache{
    static String cacheDir = "sdcard/cache/";
    @Override
    public Bitmap get(String url){
        return  null;
    }
    @Override
    public void put(String url,Bitmap bitmap){
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null){
                try{
                    fileOutputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
