// === Project Definition ===

description          in ThisBuild := "A zero-dependency micro library to deal with errors"
homepage             in ThisBuild := Some(url("https://github.com/makiftutuncu/e"))
startYear            in ThisBuild := Some(2019)
licenses             in ThisBuild := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))
organization         in ThisBuild := "dev.akif"
organizationName     in ThisBuild := "Mehmet Akif Tütüncü"
organizationHomepage in ThisBuild := Some(url("https://akif.dev"))
developers           in ThisBuild := List(Developer("makiftutuncu", "Mehmet Akif Tütüncü", "m.akif.tutuncu@gmail.com", url("https://akif.dev")))
scmInfo              in ThisBuild := Some(ScmInfo(url("https://github.com/makiftutuncu/e"), "git@github.com:makiftutuncu/e.git"))

// === Modules ===

lazy val e = project
  .in(file("."))
  .aggregate(`e-scala`, `e-kotlin`, `e-java`, `e-circe`, `e-play-json`, `e-gson`, `e-zio`)

lazy val `e-docs` = project
  .in(file("e-docs"))
  .dependsOn(`e-scala`, `e-kotlin`, `e-java`, `e-circe`, `e-play-json`, `e-gson`, `e-zio`)
  .enablePlugins(MdocPlugin)
  .settings(Settings.scalaSettings)
  .settings(Settings.mdocSettings)

lazy val `e-scala`  = project.in(file("e-scala")).settings(Settings.scalaSettings)
lazy val `e-kotlin` = project.in(file("e-kotlin")).settings(Settings.kotlinSettings)
lazy val `e-java`   = project.in(file("e-java")).settings(Settings.javaSettings)

lazy val `e-circe` = project
  .in(file("e-circe"))
  .dependsOn(`e-scala` % "compile->compile;test->test")
  .settings(Settings.scalaSettings)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.circeCore,
      Dependencies.circeParser
    )
  )

lazy val `e-play-json` = project
  .in(file("e-play-json"))
  .dependsOn(`e-scala` % "compile->compile;test->test")
  .settings(Settings.scalaSettings)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.catsCore,
      Dependencies.playJson
    )
  )

lazy val `e-zio` = project
  .in(file("e-zio"))
  .dependsOn(`e-scala` % "compile->compile;test->test")
  .settings(Settings.scalaSettings)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.zio,
      Dependencies.zioTest,
      Dependencies.zioTestSBT
    ),

    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
)

lazy val `e-gson` = project
  .in(file("e-gson"))
  .dependsOn(`e-java` % "compile->compile;test->test")
  .settings(Settings.javaSettings)
  .settings(
    libraryDependencies ++= Seq(
      Dependencies.gson
    )
  )

// === Release Settings ===

import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

val sonatypeUser = sys.env.getOrElse("SONATYPE_USER", "")
val sonatypePass = sys.env.getOrElse("SONATYPE_PASS", "")

credentials          in ThisBuild += Credentials("Sonatype Nexus Repository Manager", "oss.sonatype.org", sonatypeUser, sonatypePass)
pomIncludeRepository in ThisBuild := { _ => false }
publishMavenStyle    in ThisBuild := true
publishTo            in ThisBuild := { Some(if (isSnapshot.value) "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots" else "releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2") }

val updateDocumentation = ReleaseStep(
  releaseStepCommand("e-docs/mdoc") andThen { st =>
    import sys.process._
    "git add -A".!
    st
  }
)

val checkCredentials = ReleaseStep { state =>
  if (sonatypeUser.isEmpty || sonatypePass.isEmpty) {
    throw new Exception("Sonatype credentials are missing! Make sure to provide SONATYPE_USER and SONATYPE_PASS environment variables.")
  }

  state
}

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  checkCredentials,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  updateDocumentation,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("+e-scala/publishSigned"),
  releaseStepCommandAndRemaining("e-java/publishSigned"),
  releaseStepCommandAndRemaining("e-kotlin/publishSigned"),
  releaseStepCommandAndRemaining("+e-circe/publishSigned"),
  releaseStepCommandAndRemaining("+e-play-json/publishSigned"),
  releaseStepCommandAndRemaining("e-gson/publishSigned"),
  releaseStepCommandAndRemaining("+e-zio/publishSigned"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
