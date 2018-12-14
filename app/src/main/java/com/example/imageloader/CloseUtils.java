package com.example.imageloader;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by W on 2018/12/13.
 */

public final class CloseUtils {

    private CloseUtils(){}
    /**
     * 关闭Closeable对象
     */
    public static void closeQuietly(Closeable closeable){
        if (null != closeable){
            try {
                closeable.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
