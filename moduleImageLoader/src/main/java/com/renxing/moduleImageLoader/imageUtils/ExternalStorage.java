package com.renxing.moduleImageLoader.imageUtils;

import android.os.Environment;


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
