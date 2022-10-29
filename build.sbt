import sbt.Keys._

val sparkVersion = "2.3.1"

lazy val commonSettings = Seq(
  version := "1.0",
  name := "spark-sbt-template",
  scalaVersion := "2.11.8",
  mainClass in Compile := Some("org.sinoptik.SbtTemplate"),
  mainClass in assembly := Some("org.sinoptik.SbtTemplate")
)

lazy val commonDependencies = Seq(

  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-yarn" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided"

)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= commonDependencies,
  ).
  enablePlugins(AssemblyPlugin)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

assemblyJarName in assembly := "spark-sbt-template_2.11-1.0.jar"

