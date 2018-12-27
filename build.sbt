name := """play-gimme-webapp"""
organization := "com.gimme"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.6"

libraryDependencies += guice
libraryDependencies += ws
/*libraryDependencies += "com.squareup.retrofit2" % "retrofit" % "2.4.0"
libraryDependencies += "com.squareup.retrofit2" % "converter-jackson" % "2.4.0"*/


