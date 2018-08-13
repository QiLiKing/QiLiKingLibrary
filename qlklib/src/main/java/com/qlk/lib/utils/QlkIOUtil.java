package com.qlk.lib.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * <br/>
 * QQ：1055329812<br/>
 * Created by QiLiKing on 2018/8/9 17:23
 */
public class QlkIOUtil {
    private static final int BUFFER_SIZE = 1024 * 4;

    public static void close(Closeable... streams) {
        for (Closeable stream : streams) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    //do nothing
                }
            }
        }
    }

    /*-**********************************************************
     * Split: Following copied from com.amazonaws.util.IOUtils
     *-*********************************************************/

    /**
     * Reads and returns the rest of the given input stream as a byte array,
     * closing the input stream afterwards.
     *
     * @param is the input stream.
     * @return the rest of the given input stream.
     */
    public static byte[] toByteArray(InputStream is) throws IOException {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            final byte[] b = new byte[BUFFER_SIZE];
            int n = 0;
            while ((n = is.read(b)) != -1) {
                output.write(b, 0, n);
            }
            return output.toByteArray();
        }
    }

    /**
     * Reads and returns the rest of the given input stream as a string, closing
     * the input stream afterwards.
     *
     * @param is the input stream.
     * @return the rest of the given input stream.
     */
    public static String toString(InputStream is) throws IOException {
        return new String(toByteArray(is), "UTF-8");
    }

    /**
     * Copies all bytes from the given input stream to the given output stream.
     * Caller is responsible for closing the streams.
     *
     * @param in  the input stream.
     * @param out the output stream.
     * @return the count of bytes copied.
     * @throws IOException if there is any IO exception during read or write.
     */
    public static long copy(InputStream in, OutputStream out) throws IOException {
        final byte[] buf = new byte[BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while ((n = in.read(buf)) > -1) {
            out.write(buf, 0, n);
            count += n;
        }
        return count;
    }
}
