package lymiah.ioofrobot

import lymiah.ioofrobot.commands.CommandRegistry
import lymiah.ioofrobot.robot.RobotSimulation
import lymiah.ioofrobot.robot.RobotState
import java.io.BufferedReader
import java.io.FileReader

fun main(args: Array<String>) {
    val input: BufferedReader = when(args.size) {
        0 -> System.`in`.bufferedReader()
        1 -> FileReader(args[0]).buffered()
        else -> throw IllegalArgumentException("Expected no input (use stdin) or a single file as input.")
    }

    var sim : RobotSimulation? = null
    val parser = CommandRegistry(registerDefaults = true)
    val initialState : RobotState
    input.forEachLine { line ->

    }
}
