package com.runjf.test.jvm.instrument;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;
import java.util.Arrays;

/**
 * 执行调用Attach API
 *
 * Created by rjf on 2018/2/25.
 */
public class AgentAttach {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException,
            AgentInitializationException {
        String jarFileName = args[0];
        String processId = args[1];
        VirtualMachine virtualMachine = VirtualMachine.attach(processId);
        try {
            virtualMachine.loadAgent(jarFileName, args[2]);
        } finally {
            virtualMachine.detach();
        }
        System.out.println("AgentAttach done: " + Arrays.toString(args));
    }

}
