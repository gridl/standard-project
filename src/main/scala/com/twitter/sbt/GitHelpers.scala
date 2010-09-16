package com.twitter.sbt

import _root_.sbt.{BasicManagedProject, DefaultProject, ProcessBuilder}
import _root_.sbt.Process._
import java.io.File

trait GitHelpers {
  private def run(command: ProcessBuilder) = command !! NullLogger

  def gitCommitSavedEnvironment(message: Option[String]) {
    run(
      <x>git add project/build.properties</x> #&&
      <x>git commit -m {message.getOrElse("Updating build.properties")}</x>
    )
  }

  def gitTag(tag: String) { run(<x>git tag -m {tag} {tag}</x>) }

  def gitIsCleanWorkingTree = run("git status").contains("nothing to commit (working directory clean)")
}
