package io.github.architectplatform.plugins.gradlearchitected

import io.github.architectplatform.api.core.project.ProjectContext
import io.github.architectplatform.api.core.tasks.Environment
import io.github.architectplatform.api.core.tasks.Task
import io.github.architectplatform.api.core.tasks.TaskResult
import io.github.architectplatform.api.core.tasks.phase.Phase
import kotlin.io.path.Path

class GradleTask(
    private val command: String,
    private val phase: Phase,
    private val context: GradleContext,
    private val isEnabled: (GradleProjectContext) -> Boolean = { true }
) : Task {
  override val id: String = "gradle-$command"

  override fun phase(): Phase = phase

  override fun execute(
      environment: Environment,
      projectContext: ProjectContext,
      args: List<String>
  ): TaskResult {
    val results = this.context.projects.map { singleProjectTask(projectContext, args, it) }
    return TaskResult.success("Gradle task: $id completed successfully", results)
  }

  private fun singleProjectTask(
      projectContext: ProjectContext,
      args: List<String>,
      gradleProjectContext: GradleProjectContext
  ): TaskResult {
    if (!isEnabled(gradleProjectContext)) {
      println("Gradle task: $id not enabled on gradle project: $gradleProjectContext. Skipping...")
      return TaskResult.success(
          "Gradle task: $id not enabled on gradle project: $gradleProjectContext. Skipping...")
    }
    println(
        "Executing Gradle task: $id with args: ${args.joinToString(", ")} over gradle project: $gradleProjectContext")
    val gradleProjectDir =
        Path(projectContext.dir.toString(), gradleProjectContext.path).toAbsolutePath()
    val gradleCommand = gradleProjectDir.resolve(gradleProjectContext.gradlePath)
    val processBuilder =
        ProcessBuilder(gradleCommand.toString(), command, *args.toTypedArray())
            .directory(gradleProjectDir.toFile())
            .inheritIO()
    val process = processBuilder.start()
    val exitCode = process.waitFor()
    return if (exitCode != 0) {
      TaskResult.failure(
          "Gradle task: $id over gradle project: $gradleProjectContext failed with exit code $exitCode")
    } else {
      TaskResult.success(
          "Gradle task: $id over gradle project: $gradleProjectContext completed successfully")
    }
  }
}
