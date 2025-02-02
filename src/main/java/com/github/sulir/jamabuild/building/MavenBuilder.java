package com.github.sulir.jamabuild.building;

import com.github.sulir.jamabuild.Project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MavenBuilder extends Builder {
    public static final String[] COPY_DEPENDENCIES = new String[] {"mvn", "dependency:copy-dependencies",
            "-DoutputDirectory="};

    public MavenBuilder(Project project) {
        super(project);
    }

    @Override
    protected String getToolName() {
        return "Maven";
    }

    @Override
    protected List<String> getBuildToolCommand() {
        List<String> command = new ArrayList<>(List.of("mvn", "-B", "clean", "package"));

        if (project.getSettings().skipTests())
            command.add("-DskipTests");

        return command;
    }

    @Override
    public void copyJARs() {
        project.copySourceFiles("{**/,}target/*.jar", project.getJARsDir());
    }

    public void copyDependencies() {
        try {
            String[] command = Arrays.copyOf(COPY_DEPENDENCIES, COPY_DEPENDENCIES.length);
            command[command.length - 1] = command[command.length - 1].concat(project.getDependenciesDir().toString());

            Process process = Runtime.getRuntime().exec(command, null, project.getSourceDir().toFile());
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
