import sbt._
import Keys._

object Build extends Build {

  val liftVersion = "2.6"
  val jettyVersion: String = "8.1.7.v20120910"

  def sharedSettings = Seq(
    scalaVersion := "2.11.4",
    scalacOptions += "-deprecation",
    resolvers += "Sonatype Repo" at "http://oss.sonatype.org/content/groups/public/",
    resolvers += "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
    libraryDependencies ++= Seq(
      "net.liftweb" %% "lift-webkit" % liftVersion,
      "net.liftmodules" %% "lift-jquery-module_2.6" % "2.8",
      "im.mange" %% "little-server" % "0.0.1",
      "ch.qos.logback" % "logback-classic" % "1.0.6",
      "com.github.agmenc" %% "lijq" % "2.0.1",
      "junit" % "junit" % "4.11" % "test",
      "org.scalatest" %% "scalatest" % "2.2.0" % "test"
    ),
    // add scala-xml dependency when needed (for Scala 2.11 and newer)
    // this mechanism supports cross-version publishing
    libraryDependencies := {
      CrossVersion.partialVersion(scalaVersion.value) match {
        case Some((2, scalaMajor)) if scalaMajor >= 11 => libraryDependencies.value :+ "org.scala-lang.modules" %% "scala-xml" % "1.0.1"
        case _ => libraryDependencies.value
      }
    }
  )

  lazy val main = Project(id = "main", base = file(".")).settings(sharedSettings: _*)
}