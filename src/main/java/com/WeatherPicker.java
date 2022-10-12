package com.youkeda.test.j5c4s5p1;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;

public class WeatherPicker {

  // okHttpClient 实例
  private OkHttpClient okHttpClient;

  public WeatherPicker() {
    //1. 构建 okHttpClient 实例
    okHttpClient = new OkHttpClient();
  }

  /**
   * 根据输入的url，读取页面内容并返回
   */
  private String getPageContentSync(String url) {
    // 参数判断，未输入参数则直接返回
    if (StringUtils.isBlank(url)) {
      return null;
    }

    //2.定义一个request
    Request request = new Request.Builder().url(url).build();
    //3.使用client去请求
    Call call = okHttpClient.newCall(request);
    String result = null;
    try {
      //4.获得返回结果
      result = call.execute().body().string();
      System.out.println("call " + url + " , content's size=" + result.length());
    } catch (IOException e) {
      System.out.println("request " + url + " error . ");
      e.printStackTrace();
    }

    return result;
  }

  public String pick() {
    String content = getPageContentSync("http://www.weather.com.cn/data/sk/101190101.html");
    return content;
  }

  public String pick(String url) {
    String content = getPageContentSync(url);
    return content;
  }
}
