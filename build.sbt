name := """cheat"""

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "com.typesafe" % "config" % "1.3.1" withSources() withJavadoc()

mainClass in assembly := Some("com.saville.Cheat")
