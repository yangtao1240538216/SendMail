package com.youkeda.test.j5c4s5p1;

import com.alibaba.fastjson.JSON;
import com.youkeda.test.j5c4s5p1.model.WeatherData;
import com.youkeda.test.j5c4s5p1.model.Weather;

public class WeatherNotice {

    public static void main(String[] args) {
        WeatherPicker wp = new WeatherPicker();
        String content = wp.pick();
        Weather weather = JSON.parseObject(content, Weather.class);
        String cityName = weather.getWeatherinfo().getCity();
        String temp = weather.getWeatherinfo().getTemp();
        String time = weather.getWeatherinfo().getTime();

        // 在天气变化后发邮件的观察者
        WeatherObserver w1 = new WeatherObserver();
        w1.setName("天气邮件观察者");

        // 在天气变化后发短信的观察者
        WeatherObserver w2 = new WeatherObserver();
        w2.setName("天气短信观察者");
        // 城市天气数据
        WeatherData weatherData = new WeatherData("");
        // 添加观察者
        weatherData.addObserver(w1);
        weatherData.addObserver(w2);

        // 气温变化
        weatherData.changeTemp("", "");


        String title = "天气报告";
        String mailContent = weatherData.toString();
        try {
            Double tempNum = Double.valueOf(temp);
            if (tempNum <= 20) {
                mailContent += "今天气温有点低，有点小冷，记得穿好外套，莫要着凉感冒了嗷~";
            } else if (tempNum > 24) {
                mailContent += "今天温度真的非常非常高，太阳很大，出门记得抹防晒也可以打个伞，不要被晒黑咯，更不要中暑了喔~";
            } else {
                mailContent += "今天的温度不高也不低，温度变化喜怒无常，要外出的话，记得添衣噢~";
            }
            Mail mail = new Mail();
            mail.sendMail(title, mailContent);
            System.out.println("Send complete");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}