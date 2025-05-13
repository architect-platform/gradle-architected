package io.github.architectplatform.plugins.gradlearchitected.application.tasks

import io.github.architectplatform.api.tasks.build.BuildTask
import io.github.architectplatform.api.tasks.build.BuildTaskResult
import io.github.architectplatform.plugins.gradlearchitected.application.executor.GradleExecutor

class GradleBuildTask() : BuildTask() {

	override val name: String = "gradle-build-task"
	private val gradleExecutor = GradleExecutor()

	override fun executeTask(path: String): BuildTaskResult {
		println("Executing gradle build for project in path: $path")
		try {
			gradleExecutor.execute(path, "build")
			println("Gradle build completed successfully")
			return BuildTaskResult.success("Gradle build completed successfully")
		} catch (e: Exception) {
			println("Error executing gradle build: ${e.message}")
			return BuildTaskResult.failure("Error executing gradle build: ${e.message}")
		}
	}

}