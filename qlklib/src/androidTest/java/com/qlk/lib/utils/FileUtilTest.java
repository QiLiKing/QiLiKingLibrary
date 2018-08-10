package com.qlk.lib.utils;

import android.os.Environment;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/10 16:04
 */
public class FileUtilTest {
    private static final String VALID_ROOT = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/test";
    private static final String INVALID_ROOT = "./ADJ//DFHG/ED";
    private static final String VALID_PATH = VALID_ROOT + File.separator + "test.txt";
    private static final String INVALID_PATH = INVALID_ROOT + File.separator + "test.txt";

    private static final String[] PATHS = {
            "/storage/emulated/0/DCIM/Camera/356.png",
//            null,
            "",
            ".",
            "./.txt",
            "./a.txt",
            "./a.txt.jpg",
            "a/b.txt",
            "a/b/c/d.txt",
            "httpatbat/.dtxt",
            ".txt",
            "http://www.baidu.com/a.txt",
            "ftp://ddd.txt",
            "https://dfsf./tadg.txt",
            "file://cone0/dfdg/abt/0",
            "content://dsdfd/contact/e-/dg.txt"
    };

    @Test
    public void isFileInvalid() {
        for (String path : PATHS) {
            System.out.println(path + "---" + FileUtil.isFileInvalid(path));
        }
    }

    @Test
    public void isFileValid() {
    }

    @Test
    public void isRemoteFile() {
    }

    @Test
    public void exists() {
        for (String path : PATHS) {
            System.out.println(path + "---" + FileUtil.exists(path));
        }
    }

    @Test
    public void getFileName() {
        for (String path : PATHS) {
            System.out.println(path + "---" + FileUtil.getFileName(path));
        }
    }

    @Test
    public void getParentDir() {
        for (String path : PATHS) {
            System.out.println(path + "---" + FileUtil.getParentDir(path));
        }
    }

    @Test
    public void getFolder() {
        for (String path : PATHS) {
            System.out.println(path + "---" + FileUtil.getFolder(path));
        }
    }

    @Test
    public void deleteAllFiles() {

        //valid
        File rootFile = new File(VALID_ROOT);
        File file = new File(VALID_PATH);

        //1. root not exists
        file.delete();
        rootFile.delete();
        assertEquals(true, FileUtil.deleteAllFiles(VALID_ROOT));

        //2. empty folder
        file.delete();
        rootFile.mkdirs();
        assertEquals(true, FileUtil.deleteAllFiles(VALID_ROOT));

        //3. not empty folder
        FileUtil.createFile(VALID_PATH, true);
        assertEquals(true, FileUtil.deleteAllFiles(VALID_ROOT));

        //4. delete file
        FileUtil.createFile(VALID_PATH, true);
        assertEquals(true, FileUtil.deleteAllFiles(VALID_PATH));
    }

    @Test
    public void copyFile() {
    }

    @Test
    public void needRename() {
        File file = new File(VALID_PATH);

    }

    @Test
    public void createFile() {

        //valid
        File rootFile = new File(VALID_ROOT);
        File file = new File(VALID_PATH);

        //invalid
//        File rootFile = new File(INVALID_ROOT);
//        File file = new File(INVALID_PATH);

        //1. PATH exists and is a directory
        file.delete();
        rootFile.delete();
        file.mkdirs();
        assertEquals(false, FileUtil.createFile(VALID_PATH));
        assertEquals(true, file.exists());
        assertEquals(false, file.isFile());

        //2. PATH exists and is a directory, force create
        file.delete();
        rootFile.delete();
        file.mkdirs();
        assertEquals(true, FileUtil.createFile(VALID_PATH, true));
        assertEquals(true, file.exists());
        assertEquals(true, file.isFile());

        //3. PATH not exists
        file.delete();
        rootFile.delete();
        assertEquals(true, FileUtil.createFile(VALID_PATH));
        assertEquals(true, file.exists());
        assertEquals(true, file.isFile());

        //4. PATH not exists, force create
        file.delete();
        rootFile.delete();
        assertEquals(true, FileUtil.createFile(VALID_PATH, true));
        assertEquals(true, file.exists());
        assertEquals(true, file.isFile());

    }
}