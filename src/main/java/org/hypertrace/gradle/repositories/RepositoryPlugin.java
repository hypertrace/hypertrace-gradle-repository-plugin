package org.hypertrace.gradle.repositories;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class RepositoryPlugin implements Plugin<Project> {

  private static final String HYPERTRACE_REPOSITORY_URL =
      "https://us-maven.pkg.dev/hypertrace-repos/maven";
  private static final String CONFLUENT_REPOSITORY_URL = "https://packages.confluent.io/maven";

  @Override
  public void apply(Project target) {

    target.allprojects(
        project -> {
          addMavenLocalToProject(project);
          addMavenCentralToProject(project);
          addHypertraceRepositoryToProject(project);
          addConfluentRepositoryToProject(project);
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
              mavenArtifactRepository.setUrl(HYPERTRACE_REPOSITORY_URL);
            });
  }

  private void addMavenCentralToProject(Project project) {
    project.getRepositories().mavenCentral();
  }

  private void addConfluentRepositoryToProject(Project project) {
    project
        .getRepositories()
        .maven(
            mavenArtifactRepository -> {
              mavenArtifactRepository.setName("confluent-maven");
              mavenArtifactRepository.setUrl(CONFLUENT_REPOSITORY_URL);
            });
  }
}
