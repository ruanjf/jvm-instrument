package com.runjf.test.jvm.instrument;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * 用于命令行中启动代理的入口，配合<code>-javaagent:jarpath[=options]</code>使用
 *
 * Created by rjf on 2018/2/25.
 */
public class Premain {

    public static void premain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException, IOException, ClassNotFoundException {

        Util.redefineClasses(agentArgs, inst);

        System.out.println("Premain done: " + agentArgs);
    }

}
