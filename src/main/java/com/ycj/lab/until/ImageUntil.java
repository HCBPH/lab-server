package com.ycj.lab.until;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.io.*;
import java.util.Iterator;

/**
 * @author 53059
 * @date 2021/5/30 10:37
 */
public class ImageUntil {
    public static String getImageType(byte[] in) {
        String type = "unknown";
//        InputStream inputStream = in;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(in);
        MemoryCacheImageInputStream memoryCacheImageInputStream = new MemoryCacheImageInputStream(byteArrayInputStream);
        Iterator iterator = ImageIO.getImageReaders(memoryCacheImageInputStream);
        while (iterator.hasNext()) {
            ImageReader reader = (ImageReader) iterator.next();
            String name = reader.getClass().getSimpleName();
            if ("JPEGImageReader".equals(name)) {
                type = "jpg";
                break;
            } else if ("PNGImageReader".equals(name)) {
                type = "png";
                break;
            } else if ("BMPImageReader".equals(name)) {
                type = "bmp";
                break;
            } else if ("GIFImageReader".equals(name)) {
                type = "gif";
                break;
            }
        }
        return type;
    }

    public static boolean saveImage(InputStream in, String path) throws IOException {
//        System.out.println(in.available());
        byte[] buff = new byte[in.available()];
        in.read(buff);
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(buff);
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
