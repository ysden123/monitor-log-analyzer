

lazy val sparkVersion = "2.3.2"
lazy val scalaLoggingVersion = "3.9.0"
lazy val commonIoVersion = "1.3.2"
lazy val scalaTestVersion = "3.2.0-SNAP10"
lazy val scalaCheckVersion = "1.14.0"
lazy val scalaMockVersion = "4.1.0"

lazy val commonSettings = Seq(
  organization := "com.stulsoft",
  version := "1.1.0",
  scalaVersion := "2.11.12",
  scalacOptions ++= Seq(
    "-feature",
    "-language:implicitConversions",
    "-language:postfixOps"),
  libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion,
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "org.scalacheck" %% "scalacheck" % scalaCheckVersion % "test",
    "org.scalamock" %% "scalamock" % scalaMockVersion % "test"
  )
)

lazy val monitorLogAnalyzer = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "monitor-log-analyzer"
  )