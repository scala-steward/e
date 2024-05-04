[//]: # "This file is generated by [mdoc](https://scalameta.org/mdoc). Do not edit it directly as it will be overwritten. Instead edit corresponding file in docs folder."

# e 🐞

e is a zero-dependency micro library for dealing with errors on JVM. It aims to unify error handling across different JVM applications by providing an error model, ways to encode/decode errors and ways to use errors throughout your codebase.

It consists of different modules for different needs. You can find details, instructions and documentation about each of them on their own pages.

| Latest Version of e | Java Version   | Scala Version   | Kotlin Version   |
|---------------------|----------------|-----------------|------------------|
| 3.0.0-SNAPSHOT           | 21 | 3.4.0 | 1.9.23 |

## Table of Contents

1. [Modules](#modules)
2. [Migrating from 1.x](MIGRATION.md#1-migrating-from-1x-to-2x)
3. [Examples and Use Cases](#examples-and-use-cases)
4. [Development and Testing](#development-and-testing)
5. [Documentation](#documentation)
6. [Dependencies](#dependencies)
7. [Releases](#releases)
8. [Contributing](#contributing)
9. [License](#license)

## Modules

| Name                                 | Platform | Details                                                            | Documentation                                                                                                                                                                   |
|--------------------------------------|----------|--------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [e-scala](e-scala/README.md)         | Scala    | Scala implementation                                               | [![](https://img.shields.io/badge/docs-3.0.0-SNAPSHOT-brightgreen.svg?style=for-the-badge&logo=scala&color=dc322f&labelColor=333333)](https://javadoc.io/doc/dev.akif/e-scala_3)     |
| [e-kotlin](e-kotlin/README.md)       | Kotlin   | Kotlin implementation                                              | [![](https://img.shields.io/badge/docs-3.0.0-SNAPSHOT-brightgreen.svg?style=for-the-badge&logo=kotlin&color=0095d5&labelColor=333333)](https://javadoc.io/doc/dev.akif/e-kotlin)     |
| [e-java](e-java/README.md)           | Java     | Java implementation                                                | [![](https://img.shields.io/badge/docs-3.0.0-SNAPSHOT-brightgreen.svg?style=for-the-badge&logo=java&color=007396&labelColor=333333)](https://javadoc.io/doc/dev.akif/e-java)         |
| [e-circe](e-circe/README.md)         | Scala    | Extras for [circe](https://circe.github.io/circe)                  | [![](https://img.shields.io/badge/docs-3.0.0-SNAPSHOT-brightgreen.svg?style=for-the-badge&logo=scala&color=dc322f&labelColor=333333)](https://javadoc.io/doc/dev.akif/e-circe_3)     |
| [e-play-json](e-play-json/README.md) | Scala    | Extras for [play-json](https://github.com/playframework/play-json) | [![](https://img.shields.io/badge/docs-3.0.0-SNAPSHOT-brightgreen.svg?style=for-the-badge&logo=scala&color=dc322f&labelColor=333333)](https://javadoc.io/doc/dev.akif/e-play-json_3) |
| [e-zio](e-zio/README.md)             | Scala    | Extras for [ZIO](https://zio.dev)                                  | [![](https://img.shields.io/badge/docs-3.0.0-SNAPSHOT-brightgreen.svg?style=for-the-badge&logo=scala&color=dc322f&labelColor=333333)](https://javadoc.io/doc/dev.akif/e-zio_3)       |
| [e-gson](e-gson/README.md)           | Java     | Extras for [gson](https://github.com/google/gson)                  | [![](https://img.shields.io/badge/docs-3.0.0-SNAPSHOT-brightgreen.svg?style=for-the-badge&logo=java&color=007396&labelColor=333333)](https://javadoc.io/doc/dev.akif/e-gson)         |

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
