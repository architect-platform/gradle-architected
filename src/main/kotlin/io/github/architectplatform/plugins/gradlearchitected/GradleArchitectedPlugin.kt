package io.github.architectplatform.plugins.gradlearchitected

import io.github.architectplatform.api.command.Command
import io.github.architectplatform.api.plugins.Plugin
import io.github.architectplatform.plugins.gradlearchitected.application.tasks.GradleBuildTask
import io.github.architectplatform.plugins.gradlearchitected.application.tasks.GradleReleaseTask
import io.github.architectplatform.plugins.gradlearchitected.application.tasks.GradleRunTask
import io.github.architectplatform.plugins.gradlearchitected.application.tasks.GradleTestTask

class GradleArchitectedPlugin : Plugin {
    override val name = "gradle-architected"

    val _commands = listOf(
        GradleBuildTask(),
        GradleTestTask(),
        GradleRunTask(),
        GradleReleaseTask()
    )

    override fun getCommands(): List<Command<*>> {
        return _commands
    }

}