package com.lishi.baijiaxing.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ouyangbin on 2015/9/10.
 */
public class StreamUtils {

    final static int BUFFER_SIZE = 1024;

    private StreamUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * String -> InputStream
     *
     * @param text
     * @return
     */
    public static InputStream stringToInputStream(String text) throws Exception {
        return new ByteArrayInputStream(text.getBytes("UTF-8"));
    }

    /**
     * InputStream -> String
     *
     * @param is
     * @return
     */
    public static String inputStreamToString(InputStream is) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = is.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        return new String(outStream.toByteArray(), "UTF-8");

    }

    /**
     * 将InputStream转换成byte数组
     *
     * @param in InputStream
     * @return byte[]
     * @throws IOException
     */
    public static byte[] InputStreamTOByte(InputStream in) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        return outStream.toByteArray();
    }

    /**
     * 将byte数组转换成InputStream
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static InputStream byteTOInputStream(byte[] in) throws Exception {

        ByteArrayInputStream is = new ByteArrayInputStream(in);
        return is;
    }

    /**
     * 将byte数组转换成String
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static String byteTOString(byte[] in) throws Exception {

        InputStream is = byteTOInputStream(in);
        return inputStreamToString(is);
    }
}
