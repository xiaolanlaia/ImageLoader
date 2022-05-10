package com.renxing.moduleImageLoader.imageUtils;

import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;


import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;

/**
 */
public class FileUtils {

    /**
     * 获取缓存跟目录
     *
     * @return
     */
    public static String getCacheDirPath() {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            if (Objects.requireNonNull(ModuleImageConstant.INSTANCE.getApplication()).getExternalCacheDir() != null) {
                cachePath = ModuleImageConstant.INSTANCE.getApplication().getExternalCacheDir().getAbsolutePath();
            } else {
                cachePath = ModuleImageConstant.INSTANCE.getApplication().getCacheDir().getAbsolutePath();
            }
        } else {
            cachePath = Objects.requireNonNull(ModuleImageConstant.INSTANCE.getApplication()).getCacheDir().getAbsolutePath();
        }
        return cachePath;
    }

    /**
     * 获取video的文件夹
     *
     * @return
     */
    public static File getJsonPathName() {
        if (!ExternalStorage.isExistExternalStore()) {
//            ToastUtil.showMessage("内存卡不存在");
            return null;
        } else {
            File file = new File(ExternalStorage.getExternalStorePath() + "/guaizhu/giftAnim/");
            if (!file.exists() && !file.mkdirs()) {
//                ToastUtil.showMessage("Path to file could not be created");
                return null;
            } else {
                return file;
            }
        }
    }



    /**
     * 获取全路径中的文件拓展名
     *
     * @param filePath 文件路径
     * @return 文件拓展名
     */
    public static String getFileExtension(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastPoi = filePath.lastIndexOf('.');
        int lastSep = filePath.lastIndexOf(File.separator);
        if (lastPoi == -1 || lastSep >= lastPoi) return "";
        return filePath.substring(lastPoi + 1);
    }

    private static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取全路径中的文件名
     *
     * @param filePath 文件路径
     * @return 文件名
     */
    public static String getFileName(String filePath) {
        if (isSpace(filePath)) return filePath;
        int lastSep = filePath.lastIndexOf(File.separator);
        return lastSep == -1 ? filePath : filePath.substring(lastSep + 1);
    }



    /**
     * 获取文件夹大小
     *
     * @param file File实例
     * @return long
     */
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) size = size + getFolderSize(fileList[i]);
                else size = size + fileList[i].length();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }




    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 格式化单位
     *
     * @param size size
     * @return size
     */
    public static String getFormatSize(long size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);

        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    public static long getFsAvailableSize(String anyPathInFs) {
        if (TextUtils.isEmpty(anyPathInFs)) {
            return 0L;
        } else {
            StatFs statFs = new StatFs(anyPathInFs);
            long blockSize;
            long availableSize;
            if (Build.VERSION.SDK_INT >= 18) {
                blockSize = statFs.getBlockSizeLong();
                availableSize = statFs.getAvailableBlocksLong();
            } else {
                blockSize = (long)statFs.getBlockSize();
                availableSize = (long)statFs.getAvailableBlocks();
            }

            return blockSize * availableSize;
        }
    }




}
