package com.project.jingdong.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Author:AND
 * Time:2018/2/12.
 * Email:2911743255@qq.com
 * Description:
 * Detail:
 */

public class NetUtils {
    public static String getmsg(String urlstr) {
        StringBuilder sb = new StringBuilder();
        try {
            //获取URL对象
            URL url = new URL(urlstr);
            //打开链接
            HttpURLConnection connect = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connect.getInputStream();
            //设置连接超时时间
            connect.setConnectTimeout(5000);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回字符串
        return sb.toString();
    }
}
