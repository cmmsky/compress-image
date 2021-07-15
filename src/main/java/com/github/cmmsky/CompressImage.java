package com.github.cmmsky;

import com.github.cmmsky.utils.FileUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.File;
import java.io.IOException;

/**
 * @Author: cmmsky
 * @Date: Created in 14:26 2021/7/15
 * @Description:
 * @Modified by:
 */
public class CompressImage {

    // 1mb
    private static final Integer ONE_MB = 1024 * 1024;

    private static final Integer HALF_ONE_MB = ONE_MB / 2;
    // 压缩后图片的最大的大小
    private static final Integer MAX_LENGTH = ONE_MB / 10;


    public static void main(String[] args) {
        String imageDirPathPhy = FileUtil.getImageDirPathPhy();
        File file = new File(imageDirPathPhy);
        try {
            compress(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void compress(File file) throws IOException {
        File[] files = file.listFiles();
        if (files.length > 0) {
            for (File childFile : files) {
                if (childFile.isFile()) {
                    doCompress(childFile);
                } else {
                    compress(childFile);
                }
            }
        }
    }

    /**
     * 执行压缩动作，根据图片的大小来祸得不同的压缩factor
     * @param childFile
     * @throws IOException
     */
    public static void doCompress(File childFile) throws IOException {
        long length = childFile.length();
        if (!FileUtil.isImage(childFile)) {
            return;
        }

        double scalingFactor = 0.5f;
        if (length > ONE_MB && length < ONE_MB * 2) {
            scalingFactor = 0.1f;
        } else if (length > HALF_ONE_MB && length < ONE_MB) {
            scalingFactor = 0.2;
        } else if (length > ONE_MB / 10 && length < HALF_ONE_MB){
            scalingFactor = 0.5;
        } else {
            return;
        }
        System.out.println(String.format("文件大小超过500kb，进行压缩文件名 %s 文件原大小 %s kb", childFile.getName(), childFile.length() / 1024));
        Thumbnails.of(childFile).scale(scalingFactor).toFile(childFile);
        System.out.println(String.format("压缩后文件大小 %s kb", childFile.length() / 1024));

        if (childFile.length() > MAX_LENGTH) {
            doCompress(childFile);
        }
    }
}
