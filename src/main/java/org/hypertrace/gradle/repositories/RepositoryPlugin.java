package org.hypertrace.gradle.repositories;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class RepositoryPlugin implements Plugin<Project> {

  private static final String REPOSITORY_URL = "https://dl.bintray.com/hypertrace/maven";

  @Override
  public void apply(Project target) {

    target.allprojects(
        project -> {
          addMavenLocalToProject(target);
          addMavenCentralToProject(target);
          addHypertraceRepositoryToProject(target);
        });
  }

  private void addMavenLocalToProject(Project project) {
    project.getRepositories().mavenLocal();
  }

  private void addHypertraceRepositoryToProject(Project project) {
    project
        .getRepositories()
        .maven(
            mavenArtifactRepository -> {
              mavenArtifactRepository.setName("hypertrace-maven");
              mavenArtifactRepository.setUrl(REPOSITORY_URL);
            });
  }

  private void addMavenCentralToProject(Project project) {
    project.getRepositories().mavenCentral();
  }
}
