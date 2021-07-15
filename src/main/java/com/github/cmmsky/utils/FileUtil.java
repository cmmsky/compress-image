package com.github.cmmsky.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @Author: cmmsky
 * @Date: Created in 14:27 2021/7/15
 * @Description:
 * @Modified by:
 */
public class FileUtil {


    public static final String IMAGE_DIR = "images/";

    public static String getUserDir() {
        String property = System.getProperty("user.dir");
        return property;
    }

    public static String getImageDirPathPhy() {
        return getUserDir() + File.separator + IMAGE_DIR;
    }


    public static boolean isImage(File file) {

        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch (IOException e) {

            return false;
        }


    }

}
