package org.hypertrace.gradle.repositories;

import static org.gradle.api.attributes.java.TargetJvmVersion.TARGET_JVM_VERSION_ATTRIBUTE;

import org.gradle.api.Project;
import org.gradle.api.artifacts.CacheableRule;
import org.gradle.api.artifacts.ComponentMetadataContext;
import org.gradle.api.artifacts.ComponentMetadataRule;

@CacheableRule
public abstract class GuavaRule implements ComponentMetadataRule {

  static void addToProjectIfJavaBased(Project project) {
    project
        .getPluginManager()
        .withPlugin(
            "java",
            appliedPlugin -> {
              project
                  .getDependencies()
                  .getComponents()
                  .withModule("com.google.guava:guava", GuavaRule.class);
            });
  }

  @Override
  public void execute(ComponentMetadataContext context) {
    String versionWithClassifier = context.getDetails().getId().getVersion();
    String version = versionWithClassifier.substring(0, versionWithClassifier.indexOf("-"));
    this.addVariantsForBase(context, "compile", version);
    this.addVariantsForBase(context, "runtime", version);
  }

  private void addVariantsForBase(
      ComponentMetadataContext context, String variantBase, String guavaVersion) {
    this.addVariant(context, variantBase, 6, "android", guavaVersion);
    this.addVariant(context, variantBase, 8, "jre", guavaVersion);
  }

  private void addVariant(
      ComponentMetadataContext context,
      String variantBase,
      int targetJvmVersion,
      String guavaClassifier,
      String guavaVersion) {
    // Ultimately, what we're doing here is adding more metadata to guava such that it can remap
    // any declared guava dependency to the appropriate classifier for the target jvm version

    context
        .getDetails()
        .addVariant(
            String.format("jdk%d%s", targetJvmVersion, capitalize(variantBase)),
            variantBase,
            metadata -> {
              metadata.attributes(
                  attributes ->
                      attributes.attribute(TARGET_JVM_VERSION_ATTRIBUTE, targetJvmVersion));
              metadata.withFiles(
                  files -> {
                    // Clear out the default target jar
                    files.removeAllFiles();
                    // Tell gradle where each of the jars are located
                    String fullVersion = String.format("%s-%s", guavaVersion, guavaClassifier);
                    String jarName = String.format("guava-%s.jar", fullVersion);
                    files.addFile(jarName, String.format("../%s/%s", fullVersion, jarName));
                  });
            });
  }

  private String capitalize(String string) {
    return string.substring(0, 1).toUpperCase() + string.substring(1);
  }
}
