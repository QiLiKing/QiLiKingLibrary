package com.qlk.lib.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/9 17:23
 */
public class FileUtil {
    public static final String DOT = ".";
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";
    public static final String FTP = "ftp://";

    public static boolean isFileInvalid(String filePath) {
        return !isFileValid(filePath);
    }

    public static boolean isFileValid(String filePath) {
        if (filePath == null) {
            return false;
        }
        if (isRemoteFile(filePath)) {
            return true;
        }
        File file = new File(filePath);
        return file.exists() && file.isFile() && file.length() > 0;
    }

    public static boolean isRemoteFile(String path) {
        if (path == null) {
            return false;
        }
        path = path.toLowerCase();
        return path.startsWith(HTTP) || path.startsWith(HTTPS) || path.startsWith(FTP);
    }

    public static boolean exists(String path) {
        return path != null && new File(path).exists();
    }

    public static String getFileName(String filePath) {
        return new File(filePath).getName();
    }

    @Nullable
    public static String getParentDir(String filePath) {
        return new File(filePath).getParent();
    }

    @Nullable
    public static String getFolder(String filePath) {
        File parent = new File(filePath).getParentFile();
        if (parent != null) {
            return parent.getName();
        }
        return null;
    }

    /**
     * 删除目录下所有文件及子文件
     */
    public static boolean deleteAllFiles(String dir) {
        File file = new File(dir);
        if (file.isDirectory()) {
            File[] ff = file.listFiles();
            if (ff != null) {
                for (File f : ff) {
                    if (f != null) {
                        deleteAllFiles(f.getAbsolutePath());    //May cause StackOverflowException?
                    }
                }
            }
        } else if (file.isFile()) {
            return file.delete();
        }
        return false;
    }

    public static void deleteAllFiles(Collection<String> dirs) {
        Iterator<String> iterator = dirs.iterator();
        while (iterator.hasNext()) {
            deleteAllFiles(iterator.next());
        }
    }

    /**
     * @return Copy length
     */
    public static long copyFile(String from, String to) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(to);
            return IOUtil.copy(fis, fos);
        } finally {
            IOUtil.close(fis, fos);
        }
    }

    public static String needRename(String desPath) {
        File file = new File(desPath);
        final String dir = file.getParent();
        if (file.exists() && file.isFile()) {
            String name = file.getName();
            final int split = name.indexOf(".");    //the first dot
            String left = name.substring(0, split);
            String type = name.substring(split);
            if (left.matches(".*\\(\\d\\)")) {
                left = left.substring(0, left.length() - 3);
            }
            int i = 1;
            while (true) {
                desPath = dir + File.separator + left + "(" + i + ")" + type;
                file = new File(desPath);
                if (!file.exists()) {
                    break;
                }
                i++;
            }
        }
        return desPath;
    }
}
