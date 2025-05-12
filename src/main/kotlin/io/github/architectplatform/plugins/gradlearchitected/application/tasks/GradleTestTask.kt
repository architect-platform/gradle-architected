package io.github.architectplatform.plugins.gradlearchitected.application.tasks

import io.github.architectplatform.plugins.gradlearchitected.application.executor.GradleExecutor
import io.github.architectplatform.api.tasks.release.ReleaseTask
import io.github.architectplatform.api.tasks.release.ReleaseTaskResult
import io.github.architectplatform.api.tasks.run.RunTask
import io.github.architectplatform.api.tasks.run.RunTaskResult

class GradleTestTask : RunTask() {
	private val gradleExecutor: GradleExecutor = GradleExecutor()

	override fun executeTask(path: String): RunTaskResult {
		println("Executing gradle test in path: $path")
		try {
			gradleExecutor.execute(path, "test")
			println("Gradle test completed successfully")
			return RunTaskResult.success("Gradle test completed successfully")
		} catch (e: Exception) {
			println("Error executing gradle test: ${e.message}")
			return RunTaskResult.failure("Error executing gradle test: ${e.message}")
		}
	}

	override val name: String = "gradle-test-task"
}