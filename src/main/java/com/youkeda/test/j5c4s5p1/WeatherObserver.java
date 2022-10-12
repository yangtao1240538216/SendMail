package com.youkeda.test.j5c4s5p1;

import com.youkeda.test.j5c4s5p1.model.WeatherData;

import java.util.Observable;
import java.util.Observer;

/**
 * 天气观察者
 */
public class WeatherObserver implements Observer {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.print(this.name + "观察到天气变化为：");
        System.out.print(o.toString());
        System.out.println(" " + arg);
    }
}
