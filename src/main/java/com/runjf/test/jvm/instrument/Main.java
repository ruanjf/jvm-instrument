package com.runjf.test.jvm.instrument;

import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 用户测试动态加载效果
 *
 * Created by rjf on 2018/2/25.
 */
public class Main {
    public static void main(String[] args) {
//        https://stackoverflow.com/questions/35842/how-can-a-java-program-get-its-own-process-id
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        System.out.println(Arrays.toString(args));
        Pattern compile = Pattern.compile("\\d+");
        if (args.length > 1 && compile.matcher(args[0]).matches() && compile.matcher(args[1]).matches()) {
            int count = Integer.valueOf(args[0]);
            int sleep = Integer.valueOf(args[1]);
            Class<?> aClass = null;
            if (args.length > 2) {
                try {
                    aClass = Class.forName(args[2]);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            while (count > 0) { // 间隔sleep秒打印指定类的指定方法，重复count次
                if (aClass != null) {
                    try {
                        Object x = aClass.newInstance();
                        String ret = "";
                        if (x != null && args.length > 3) {
                            Method method = aClass.getMethod(args[3]);
                            ret = ", ret: "  + method.invoke(method.getModifiers() == Modifier.STATIC ? null : x);
                        }
                        System.out.println(x + ret);
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(sleep * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count--;
            }
        }
    }
}
