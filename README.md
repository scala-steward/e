
[//]: # "This file is generated by [mdoc](https://scalameta.org/mdoc). Do not edit it directly as it will be overwritten. Instead edit corresponding file in docs folder."

# e 🐞

e is a zero-dependency micro library for dealing with errors on JVM. It aims to unify error handling across different JVM applications by providing an error model, ways to encode/decode errors and ways to use errors throughout your codebase.

It consists of different modules for different needs. You can find details, instructions and documentation about each of them on their own pages.

| Latest Version of e | Java Version          | Scala Versions          | Kotlin Version   |
| ------------------- | --------------------- | ----------------------- | ---------------- |
| 1.1.2           | 1.8        | 2.12, 2.13  | 1.3.41 |

## Table of Contents

1. [Modules](#modules)
2. [Installation](#installation)
3. [Examples and Use Cases](#examples-and-use-cases)
4. [Development and Testing](#development-and-testing)
5. [Documentation](#documentation)
6. [Dependencies](#dependencies)
7. [Releases](#releases)
8. [Contributing](#contributing)
9. [License](#license)

## Modules

| Name        | Platform | Details                                                            | Documentation                 |
| ----------- | -------- | ------------------------------------------------------------------ | ----------------------------- |
| e-core      | Java     | Core module for sharing common code between modules                | [Link](e-core/README.md)      |
| e-java      | Java     | Java implementation                                                | [Link](e-java/README.md)      |
| e-scala     | Scala    | Scala implementation                                               | [Link](e-scala/README.md)     |
| e-kotlin    | Kotlin   | Kotlin implementation                                              | [Link](e-kotlin/README.md)    |
| e-circe     | Scala    | Extras for [circe](https://circe.github.io/circe)                  | [Link](e-circe/README.md)     |
| e-play-json | Scala    | Extras for [play-json](https://github.com/playframework/play-json) | [Link](e-play-json/README.md) |
| e-zio       | Scala    | Extras for [ZIO](https://zio.dev)                                  | [Link](e-zio/README.md)       |
| e-gson      | Java     | Extras for [gson](https://github.com/google/gson)                  | [Link](e-gson/README.md)      |

## Installation

* Replace `[MODULE]` with a module name and `[SCALA_VERSION]` with your project's Scala version (if applicable)
* If you use SBT, add following to your `build.sbt` for each module you want to use
```scala
libraryDependencies ++= Seq(
  // Scala modules
  "dev.akif" %% "[MODULE]" % "1.1.2",

  // Java/Kotlin modules
  "dev.akif" % "[MODULE]" % "1.1.2"
)
```
* If you use Maven, add following to your `pom.xml` for each module you want to use
```xml
<dependencies>
  <!-- Scala modules -->
  <dependency>
    <groupId>dev.akif</groupId>
    <artifactId>[MODULE]_[SCALA_VERSION]</artifactId>
    <version>1.1.2</version>
  </dependency>

  <!-- Java/Kotlin modules -->
  <dependency>
    <groupId>dev.akif</groupId>
    <artifactId>[MODULE]</artifactId>
    <version>1.1.2</version>
  </dependency>
</dependencies>
```
* If you use Gradle, add following to your project's `build.gradle` for each module you want to use

```javascript
dependencies {
  // Scala modules
  implementation('dev.akif:[MODULE]_[SCALA_VERSION]:1.1.2'),

  // Java/Kotlin modules
  implementation('dev.akif:[MODULE]:1.1.2')
}
```

## Examples and Use Cases

There are some example projects using e and some documented use cases where e can be helpful. You can find them in [examples](examples) folder.

## Development and Testing

e is built with SBT. You can use `clean`, `compile`, `test` tasks for development and testing.

## Documentation

Documentation is managed with [mdoc](https://scalameta.org/mdoc), check out [docs](docs) folder for editing.

## Dependencies

Versions of Scala, SBT, plugins and external dependencies are kept up-to-date by [scala-steward](https://github.com/fthomas/scala-steward).

## Releases

e packages are published to Maven Central and they are versioned according to [semantic versioning](https://semver.org). Release process is managed by [sbt-release](https://github.com/sbt/sbt-release).

## Contributing

All contributions are welcome, including requests to feature your project utilizing e. Please feel free to send a pull request. Thank you.

## License

e is licensed with [MIT License](LICENSE.md).
