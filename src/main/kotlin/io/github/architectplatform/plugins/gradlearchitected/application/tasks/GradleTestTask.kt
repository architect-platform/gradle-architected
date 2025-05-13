package io.github.architectplatform.plugins.gradlearchitected.application.tasks

import io.github.architectplatform.plugins.gradlearchitected.application.executor.GradleExecutor
import io.github.architectplatform.api.tasks.release.ReleaseTask
import io.github.architectplatform.api.tasks.release.ReleaseTaskResult
import io.github.architectplatform.api.tasks.run.RunTask
import io.github.architectplatform.api.tasks.run.RunTaskResult
import io.github.architectplatform.api.tasks.test.TestTask
import io.github.architectplatform.api.tasks.test.TestTaskResult

class GradleTestTask : TestTask() {
	private val gradleExecutor: GradleExecutor = GradleExecutor()

	override fun executeTask(path: String): TestTaskResult {
		println("Executing gradle test in path: $path")
		try {
			gradleExecutor.execute(path, "test")
			println("Gradle test completed successfully")
			return TestTaskResult.success("Gradle test completed successfully")
		} catch (e: Exception) {
			println("Error executing gradle test: ${e.message}")
			return TestTaskResult.failure("Error executing gradle test: ${e.message}")
		}
	}

	override val name: String = "gradle-test-task"
}