package io.github.architectplatform.plugins.gradlearchitected.application.executor


class GradleExecutor  {
	fun execute(path: String, vararg args: String) {
		println("Executing Gradle command: ${args.toList().joinToString(" ")} in $path")
	}
}