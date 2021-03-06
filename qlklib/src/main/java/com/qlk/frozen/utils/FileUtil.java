package com.qlk.frozen.utils;

import android.support.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
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
        if (isRemoteFile(filePath)) {
            return true;
        }
        File file = new File(filePath);
        return file.exists() && file.isFile() && file.length() > 0;
    }

    public static boolean isRemoteFile(String path) {
        path = path.toLowerCase();
        return path.startsWith(HTTP) || path.startsWith(HTTPS) || path.startsWith(FTP);
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

    public static boolean createFile(String path) {
        return createFile(path, false);
    }

    /**
     * @param forceCreate if path is a directory, delete and create with file type
     * @return true if create success or already exists
     */
    public static boolean createFile(String path, boolean forceCreate) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {
                return true;
            } else {
                if (!forceCreate) {
                    return false;
                }
                deleteAllFiles(path);
            }
        } else {
            String dir = getParentDir(path);
            if (dir != null) {
                createDirectory(dir, forceCreate);
            }
        }

        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @return true the directory exists or created successfully
     */
    public static boolean createDirectory(String dir, boolean forceCreate) {
        File file = new File(dir);
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            } else {
                if (!forceCreate) {
                    return false;
                }
                file.delete();
                return file.mkdirs();
            }
        } else {
            String parent = file.getParent();
            //check the parent directory is whether a file or not.
            if (parent != null) {
                createDirectory(parent, forceCreate);
            }
            return file.mkdirs();
        }
    }

    /**
     * delete all files under the folder
     *
     * @return true if delete success or not exists
     */
    public static boolean deleteAllFiles(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            File[] ff = file.listFiles();
            if (ff != null) {
                for (File f : ff) {
                    if (f != null) {
                        deleteAllFiles(f.getAbsolutePath());    //Maybe cause StackOverflowException?
                    }
                }
            }
        }

        return file.delete();
    }

    public static void deleteAllFiles(Collection<String> dirs) {
        Iterator<String> iterator = dirs.iterator();
        while (iterator.hasNext()) {
            deleteAllFiles(iterator.next());
        }
    }

    /**
     * @return Copy length. -1 copy failed
     * @see FileUtil#copyFile(String, String)
     */
    public static long copyFileOld(String from, String to) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(to);
            return IOUtil.copy(fis, fos);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            IOUtil.close(fis, fos);
        }
    }

    public static boolean copyFile(String from, String to) {
        return copyFile(from, to, false);
    }

    public static boolean copyFile(String from, String to, boolean append) {
        if (!append) {
            to = needRename(to);
        }
        try (RandomAccessFile in = new RandomAccessFile(from, "r");
             RandomAccessFile out = new RandomAccessFile(to, append ? "rw+" : "rw")) {
            FileChannel fIn = in.getChannel();
            FileChannel fOut = out.getChannel();
            fOut.transferFrom(fIn, 0, fIn.size());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String needRename(String desPath) {
        return needRename(desPath, false);
    }

    /**
     * @param autoCreate not force create
     */
    public static String needRename(String expectPath, boolean autoCreate) {
        File file = new File(expectPath);
        final String dir = file.getParent();
        String desPath = expectPath;
        if (file.exists() && file.isFile()) {
            String name = file.getName();
            int first = name.indexOf(DOT);    //the first dot
            int last = name.lastIndexOf(DOT);
            int index = first;
            if (name.startsWith(DOT)) {    //hide file
                if (first == last) {
                    index = name.length();
                } else {
                    String tmp = name.substring(1, name.length());
                    index = tmp.indexOf(DOT) + 1;
                }
            }
            String left = name.substring(0, index);
            String type = name.substring(index);
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

        if (autoCreate) {
            createFile(desPath);    //not force to create
        }
        return desPath;
    }

    public static void write(String desFilePath, byte[] data) {
        write(desFilePath, data, false);
    }

    public static void write(String desFilePath, byte[] data, boolean append) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(desFilePath, append);
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.close(fos);
        }
    }
}
