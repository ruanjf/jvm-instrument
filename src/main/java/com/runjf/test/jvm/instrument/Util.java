package com.runjf.test.jvm.instrument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * 提供类的动态加载
 *
 * Created by rjf on 2018/2/25.
 */
public class Util {

    /**
     * 使用提供的类文件重定义提供的类集
     *
     * @param agentArgs 替换参数，格式：A,B 其中A为需要动态加载的类，B为新的class文件位置。 例如：Demo,/Demo.class.2
     * @param inst 代理服务
     * @throws ClassNotFoundException
     * @throws IOException
     * @throws UnmodifiableClassException
     */
    public static void redefineClasses(String agentArgs, Instrumentation inst) throws ClassNotFoundException, IOException, UnmodifiableClassException {
        if (agentArgs != null && agentArgs.trim().length() > 0 && agentArgs.indexOf(',') > 0) {
            String[] args = agentArgs.split(",");

            Class<?> aClass = Class.forName(args[0]);

            InputStream input = null;
            ByteArrayOutputStream output = null;
            try {
                input = AgentMain.class.getResourceAsStream(args[1]);
                if (input != null) {
                    output = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = input.read(buffer)) != -1) {
                        output.write(buffer, 0, length);
                    }
                } else {
                    System.out.println("resource not found: " + args[1]);
                }
            } finally {
                if (input != null) {
                    input.close();
                }
            }

            if (output != null) {
                inst.redefineClasses(new ClassDefinition(aClass, output.toByteArray()));
            }
        }
    }
}
