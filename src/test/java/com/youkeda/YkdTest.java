package com.youkeda;

import com.youkeda.test.j5c4s5p1.model.WeatherData;
import com.youkeda.test.j5c4s5p1.WeatherObserver;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Observable;

public class YkdTest {

    public static void error(String msg) {
        System.err.println("<YkdError>" + msg + "</YkdError>");
    }

    @Test
    public void runB() {
        PrintStream out = System.out;

        boolean checkResult = checkClass(out);
        if (!checkResult) {
            return;
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        // 在天气变化后发邮件的观察者
        WeatherObserver w1 = new WeatherObserver();
        w1.setName("天气邮件观察者");

        // 城市天气数据
        WeatherData weatherData = new WeatherData("余杭");
        // 添加观察者
        weatherData.addObserver(w1);
        // 气温变化
        weatherData.changeTemp("12:03", "18.5");

        String result = baos.toString();
        System.setOut(out);
        String[] strings = result.split("\n");

        String numStr = strings[strings.length - 1];
        if (!"发送成功".equals(numStr)) {
            error("最后一行应该输出“发送成功”");
        }

    }

    private boolean checkClass(PrintStream out) {
        boolean result = true;
        try {
            Class<?> afClass = Class.forName("com.youkeda.test.j5c4s5p1.model.WeatherData");
            Method method = afClass.getDeclaredMethod("toString");
            Method method2 = afClass.getDeclaredMethod("changeTemp", String.class, String.class);
            Field field = afClass.getDeclaredField("time");
            Field field2 = afClass.getDeclaredField("temp");
        } catch (ClassNotFoundException e) {
            System.setOut(out);
            error("没有创建`com.youkeda.test.j5c4s5p1.model.WeatherData`类");
            result = false;
        } catch (NoSuchMethodException e) {
            System.setOut(out);
            error("`com.youkeda.test.j5c4s5p1.model.WeatherData`类没有创建`toString(String)`方法和`changeTemp()`方法");
            result = false;
        } catch (NoSuchFieldException e) {
            System.setOut(out);
            error("`com.youkeda.test.j5c4s5p1.model.WeatherData`类没有创建`time`属性和`temp`属性");
            result = false;
        }

        try {
            Class<?> afClass = Class.forName("com.youkeda.test.j5c4s5p1.WeatherObserver");
            Method method = afClass.getDeclaredMethod("update", Observable.class, Object.class);
        } catch (ClassNotFoundException e) {
            System.setOut(out);
            error("没有创建`com.youkeda.test.j5c4s5p1.WeatherObserver`类");
            result = false;
        } catch (NoSuchMethodException e) {
            System.setOut(out);
            error("`com.youkeda.test.j5c4s4p2.AbstractDrink`类没有创建`update()`");
            result = false;
        }

        return result;
    }
}
