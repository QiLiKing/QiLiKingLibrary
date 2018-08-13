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

        assertEquals(false, new File(VALID_ROOT).exists());
        assertEquals(false, new File(VALID_PATH).exists());

        //4. delete file
        FileUtil.createFile(VALID_PATH, true);
        assertEquals(true, FileUtil.deleteAllFiles(VALID_PATH));

        assertEquals(true, new File(VALID_ROOT).exists());
        assertEquals(false, new File(VALID_PATH).exists());
    }

    @Test
    public void copyFile() {
        String srcPath = VALID_PATH;
    }

    @Test
    public void needRename() {

        FileUtil.deleteAllFiles(VALID_ROOT);

        String conflictPath = VALID_ROOT + "/test.txt";   //create
        String expectedPath = VALID_ROOT + "/test.txt";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/test.txt";
        expectedPath = VALID_ROOT + "/test(1).txt";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/test.txt";
        expectedPath = VALID_ROOT + "/test(2).txt";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hide";   //create
        expectedPath = VALID_ROOT + "/.hide";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hide";
        expectedPath = VALID_ROOT + "/.hide(1)";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hide";
        expectedPath = VALID_ROOT + "/.hide(2)";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hide.txt";   //create
        expectedPath = VALID_ROOT + "/.hide.txt";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hide.txt";
        expectedPath = VALID_ROOT + "/.hide(1).txt";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hide.txt";
        expectedPath = VALID_ROOT + "/.hide(2).txt";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hidesuffix.txt.jpg";   //create
        expectedPath = VALID_ROOT + "/.hidesuffix.txt.jpg";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hidesuffix.txt.jpg";
        expectedPath = VALID_ROOT + "/.hidesuffix(1).txt.jpg";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));

        conflictPath = VALID_ROOT + "/.hidesuffix.txt.jpg";
        expectedPath = VALID_ROOT + "/.hidesuffix(2).txt.jpg";
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));


        //rename directory
        conflictPath = VALID_ROOT + "/folder";
        expectedPath = VALID_ROOT + "/folder";
        new File(conflictPath).mkdirs();
        assertEquals(expectedPath, FileUtil.needRename(conflictPath, true));
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

        //5. a directory, force to file
        file.delete();
        rootFile.mkdirs();
        assertEquals(true, rootFile.isDirectory());
        assertEquals(true, FileUtil.createFile(VALID_ROOT, true));
        assertEquals(false, file.exists());
        assertEquals(true, rootFile.isFile());

    }

    @Test
    public void write() {
        byte[] data = new byte[100];
        for (int i = 0; i < 100; i++) {
            data[i] = (byte) i;
        }

        new File(VALID_ROOT).mkdirs();

        //not exists
        FileUtil.deleteAllFiles(VALID_PATH);
        FileUtil.write(VALID_PATH, data);   //100 B
        FileUtil.write(VALID_PATH, data, true); //200 B

        //exists
        FileUtil.write(VALID_PATH, data, true); //300 B
        FileUtil.write(VALID_PATH, data);    //100 B
    }

}