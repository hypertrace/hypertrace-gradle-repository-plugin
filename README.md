# Hypertrace Repository Plugin
###### org.hypertrace.repository-plugin
[![CircleCI](https://circleci.com/gh/hypertrace/hypertrace-gradle-repository-plugin.svg?style=svg)](https://circleci.com/gh/hypertrace/hypertrace-gradle-repository-plugin)

### Purpose
This plugin configures the target project and its descendants to use mavenLocal(), mavenCentral(), 
the [Hypertrace maven](https://hypertrace.jfrog.io/artifactory/maven) repository, and the
[Confluent maven](https://packages.confluent.io/maven) repository for dependency resolution.
They are resolved in that order. Additional repositories can still be added directly as needed.


### Example

```kotlin
plugins {
  id("org.hypertrace.repository-plugin") version "<version>"
}
```
