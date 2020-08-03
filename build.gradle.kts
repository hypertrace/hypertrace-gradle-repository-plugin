import org.hypertrace.gradle.publishing.License.APACHE_2_0

plugins {
  `java-gradle-plugin`
  id("org.hypertrace.publish-plugin") version "0.3.0"
  id("org.hypertrace.ci-utils-plugin") version "0.1.1"
}

group = "org.hypertrace.gradle.repositories"

java {
  targetCompatibility = JavaVersion.VERSION_1_8
  sourceCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
  plugins {
    create("gradlePlugin") {
      id = "org.hypertrace.repository-plugin"
      implementationClass = "org.hypertrace.gradle.repositories.RepositoryPlugin"
    }
  }
}

hypertracePublish {
  license.set(APACHE_2_0)
}
