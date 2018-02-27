package com.runjf.test.jvm.instrument;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

/**
 * 用于在VM 启动后启动代理
 *
 * Created by rjf on 2018/2/25.
 */
public class AgentMain {

    public static void agentmain(String agentArgs, Instrumentation inst) throws ClassNotFoundException, IOException,
            UnmodifiableClassException {

        Util.redefineClasses(agentArgs, inst);

        System.out.println("AgentMain done: " + agentArgs);
    }

}
