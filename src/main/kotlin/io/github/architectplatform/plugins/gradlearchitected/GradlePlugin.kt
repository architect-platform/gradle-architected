package io.github.architectplatform.plugins.gradlearchitected

import io.github.architectplatform.api.phase.SimpleTask
import io.github.architectplatform.api.plugins.ArchitectPlugin
import io.github.architectplatform.api.project.ProjectContext
import io.github.architectplatform.api.tasks.TaskRegistry
import io.github.architectplatform.api.tasks.TaskResult
import io.github.architectplatform.api.workflows.code.CodeWorkflow


class GradlePlugin : ArchitectPlugin<Map<String, String>> {
	override val id = "gradle-plugin"
	override val contextKey: String = "gradle"

	@Suppress("UNCHECKED_CAST")
	override val ctxClass: Class<Map<String, String>> = Map::class.java as Class<Map<String, String>>
	override lateinit var context: Map<String, String>

	override fun register(registry: TaskRegistry) {
		println("Registering GradlePlugin with ID: $id")
		registry.add(
			SimpleTask(
				id = "gradle-build-task",
				phase = CodeWorkflow.BUILD,
				task = ::buildTask,
			)
		)
		registry.add(
			SimpleTask(
				id = "gradle-test-task",
				phase = CodeWorkflow.TEST,
				task = ::testTask,
			)
		)
		registry.add(
			SimpleTask(
				id = "gradle-run-task",
				phase = CodeWorkflow.RUN,
				task = ::runTask,
			)
		)
		registry.add(
			SimpleTask(
				id = "gradle-release-task",
				phase = CodeWorkflow.RELEASE,
				task = ::releaseTask,
			)
		)
	}

	private fun buildTask(projectContext: ProjectContext): TaskResult {
		return executeGradleTask(projectContext, "build")
	}

	private fun testTask(projectContext: ProjectContext): TaskResult {
		return executeGradleTask(projectContext, "test")
	}

	private fun runTask(projectContext: ProjectContext): TaskResult {
		return executeGradleTask(projectContext, "run")
	}

	private fun releaseTask(projectContext: ProjectContext): TaskResult {
		return executeGradleTask(projectContext, "publishGprPublicationToGitHubPackagesRepository")
	}

	private fun executeGradleTask(projectContext: ProjectContext, vararg args: String): TaskResult {
		println("Executing Gradle task with args: ${args.joinToString(", ")}")
		val gradleCommand = projectContext.dir.resolve("gradlew")
		val processBuilder = ProcessBuilder(gradleCommand.toString(), *args)
			.directory(projectContext.dir.toFile())
			.inheritIO()
		val process = processBuilder.start()
		val exitCode = process.waitFor()
		return if (exitCode != 0) {
			TaskResult.failure("Gradle task failed with exit code $exitCode")
		} else {
			TaskResult.success("Gradle task completed successfully")
		}
	}
}

