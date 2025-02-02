package com.github.sulir.jamabuild.loading;

import com.github.sulir.jamabuild.Project;
import org.eclipse.jgit.api.Git;

import java.nio.file.Path;

public class GitLoader extends Loader {
    public GitLoader(String projectsDirectory, String projectId) {
        super(projectsDirectory, projectId);
    }

    @Override
    public Project load() throws Exception {
        return gitClone(projectId);
    }

    protected Project gitClone(String url) throws Exception {
        Project project = new Project(projectId, getDirectory());

        if (!project.getSourceDir().toFile().exists()) {
            Git.cloneRepository()
                    .setURI(url)
                    .setDirectory(project.getSourceDir().toFile())
                    .call();
        }

        return project;
    }

    private String getDirectory() {
        return Path.of(projectsDirectory, projectId.replaceAll("\\W", "_")).toString();
    }
}
