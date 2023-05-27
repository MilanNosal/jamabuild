package com.sulir.github.jamabuild;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static final String PROJECTS_FILE = "projects.tsv";

    public static void main(String[] args) {
        String rootDirectory = args.length > 0 ? args[0] : System.getProperty("user.dir");
        log.info("Starting JaMaBuild in {}", rootDirectory);

        ProcessList processList = new ProcessList();
        processList.addProjects(Path.of(rootDirectory, PROJECTS_FILE));
        processList.runAll(rootDirectory);
    }
}
