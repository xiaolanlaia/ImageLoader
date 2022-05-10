package com.example.moduleimage.old;

import android.os.Environment;

/**
 * 作者：肖程成 on  2020/7/12 5:57 PM
 * 邮箱：253682355@qq.com
 * 功能：
 */
public class ExternalStorage {
    private static ExternalStorage instance;

    private ExternalStorage() {

    }

    public static ExternalStorage getInstance() {
        if (null == instance) {
            instance = new ExternalStorage();
        }

        return instance;
    }


    public static boolean isExistExternalStore() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getExternalStorePath() {
        return isExistExternalStore() ? Environment.getExternalStorageDirectory().getAbsolutePath() : null;
    }
}
