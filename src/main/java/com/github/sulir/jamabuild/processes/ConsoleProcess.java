package com.github.sulir.jamabuild.processes;

import java.io.IOException;

public class ConsoleProcess {
    private final String[] command;

    public ConsoleProcess(String[] command) {
        this.command = command;
    }

    public void run() {
        try {
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            builder.redirectError(ProcessBuilder.Redirect.INHERIT);

            Process process = builder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
