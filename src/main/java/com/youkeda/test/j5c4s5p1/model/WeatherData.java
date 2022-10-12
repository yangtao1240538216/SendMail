package com.youkeda.test.j5c4s5p1.model;

import java.time.LocalDate;
import java.util.Observable;

public class WeatherData extends Observable {
    // 城市
    private String cityName;
    // 时间
    private String time;
    // 温度
    private String temp;

    // 城市固定了就不变了
    public WeatherData(String cityName) {
        this.cityName = cityName;
    }

    // 打印天气信息
    @Override
    public String toString() {
        return cityName + "，" + LocalDate.now().toString() + " " + time + "，气温：" + temp + "摄氏度。";
    }

    /**
     * 一个城市的气温在某个时刻发生了变化
     */
    public void changeTemp(String time, String temp) {
        if (time == null || temp == null) {
            return;
        }
        // 与原数据不同 说明发生了变化
        if (!time.equals(this.time) || !temp.equals(this.temp)) {
            // 标记变化
            super.setChanged();
            this.time = time;
            this.temp = temp;
            // 发出通知，参数是额外的信息
            super.notifyObservers("温度变化已通知");
        }
    }

    public String getCityName() {
        return cityName;
    }

    public String getTime() {
        return time;
    }

    public String getTemp() {
        return temp;
    }
}
