package io.github.architectplatform.plugins.gradlearchitected.application.tasks

import io.github.architectplatform.plugins.gradlearchitected.application.executor.GradleExecutor
import io.github.architectplatform.api.tasks.release.ReleaseTask
import io.github.architectplatform.api.tasks.release.ReleaseTaskResult

class GradleReleaseTask : ReleaseTask() {
	private val gradleExecutor: GradleExecutor = GradleExecutor()

	override fun executeTask(path: String): ReleaseTaskResult {
		println("Executing gradle release in path: $path")
		try {
			gradleExecutor.execute(path, "clean", "build --no-build-cache", "publishGprPublicationToGitHubPackagesRepository")
			println("Gradle release completed successfully")
			return ReleaseTaskResult.success("Gradle release completed successfully")
		} catch (e: Exception) {
			println("Error executing gradle release: ${e.message}")
			return ReleaseTaskResult.failure("Error executing gradle release: ${e.message}")
		}
	}

	override val name: String = "gradle-release-task"
}