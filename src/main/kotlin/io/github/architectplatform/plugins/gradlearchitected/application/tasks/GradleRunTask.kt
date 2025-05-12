package io.github.architectplatform.plugins.gradlearchitected.application.tasks

import io.github.architectplatform.plugins.gradlearchitected.application.executor.GradleExecutor
import io.github.architectplatform.api.tasks.release.ReleaseTask
import io.github.architectplatform.api.tasks.release.ReleaseTaskResult
import io.github.architectplatform.api.tasks.run.RunTask
import io.github.architectplatform.api.tasks.run.RunTaskResult

class GradleRunTask : RunTask() {
	private val gradleExecutor: GradleExecutor = GradleExecutor()

	override fun executeTask(path: String): RunTaskResult {
		println("Executing gradle run in path: $path")
		try {
			gradleExecutor.execute(path, "run")
			println("Gradle run completed successfully")
			return RunTaskResult.success("Gradle run completed successfully")
		} catch (e: Exception) {
			println("Error executing gradle run: ${e.message}")
			return RunTaskResult.failure("Error executing gradle run: ${e.message}")
		}
	}

	override val name: String = "gradle-run-task"
}