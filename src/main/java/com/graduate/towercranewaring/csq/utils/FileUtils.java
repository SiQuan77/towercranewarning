package com.graduate.towercranewaring.csq.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @ClassName: FileUtils
 * @Description:
 * @Author:csq
 * @Date 2021/4/9
 * @Version 1.0
 **/
public class FileUtils {
    /**
     * 编码
     * @param byteArray
     * @return
     */
    public static String encode(byte[] byteArray) {
        return new String(new Base64().encode(byteArray));
    }

    /**
     * 解码
     * @param base64EncodedString
     * @return ss
     */
    public static byte[] decode(String base64EncodedString) {
        return new Base64().decode(base64EncodedString);
    }


    /**
     * 本地图片转换成base64字符串
     * @param imgFile	图片对象
     * @return
     * @author huangshikai
     */
    public static String ImageToBase64ByLocal(File imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;

        if(!imgFile.exists()){
            return null;
        }

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        String base64Str = encoder.encode(data);
        base64Str.replaceAll("(\r\n|\r|\n|\n\r)", "");//替换base64后的字符串中的回车换行
        return base64Str;// 返回Base64编码过的字节数组字符串
    }

    /**
     * 本地图片转换成base64字符串
     * @param imgFilePath 本地图片存放路径
     * @return
     * @author huangshikai
     */
    public static String ImageToBase64ByLocal(String imgFilePath){
        if(imgFilePath==null){
            return null;
        }
        File file=new File(imgFilePath);
        if(!file.exists()){
            return null;
        }else {
            return ImageToBase64ByLocal(file);
        }

    }


}
