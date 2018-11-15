package lymiah.ioofrobot

import lymiah.ioofrobot.commands.CommandRegistry
import lymiah.ioofrobot.robot.RobotSimulation
import lymiah.ioofrobot.robot.RobotState
import java.io.BufferedReader
import java.io.FileReader

/**
 * Print line to stderr.
 * @param msg Error message.
 */
fun eprintln(msg: String) {
    System.err.println(msg);
}

/**
 * Wraps the IllegalArgumentExceptions from the ICommand objects, printing them out and returning current state instead
 */
fun ((RobotState?) -> RobotState?).wrapIllegalArguments(): (RobotState?) -> RobotState? {
    return { state ->
        try {
            this(state)
        } catch (e: IllegalArgumentException) {
            eprintln("Error Executing Command: " + e.message)
        }
        // Return the current state if errored
        state
    }
}

fun main(args: Array<String>) {
    // Take input from stdin if not file is specified, or take from file if one is specified.
    val input: BufferedReader = when(args.size) {
        0 -> System.`in`.bufferedReader()
        1 -> FileReader(args[0]).buffered()
        else -> throw IllegalArgumentException("Expected no input (use stdin) or a single file as input.")
    }


    val parser = CommandRegistry(registerDefaults = true)

    input.useLines { lines ->

        // Runs commands till once specifies initial state (by default only the PlaceCommand)
        val linesWithStartingPlaceCommand = lines.dropWhile { line ->
            val transform = (parser.getTransformFromCommandString(line) ?: return@dropWhile true).wrapIllegalArguments()
            val initialState = transform(null) ?: return@dropWhile true
            // Place command at head of sequence
            return@dropWhile false
        }
        if(!linesWithStartingPlaceCommand.any()) {
            eprintln("No Place Command found?")
            return@useLines
        }

        // We've confirmed that the first element is a Place Command at this point, so this should not NPE
        val initialState = parser.getTransformFromCommandString(linesWithStartingPlaceCommand.first())!!(null)!!

        // Creating a simulation using the initial state found
        val sim =  RobotSimulation(initialState = initialState) //TODO: Configurable boundary?

        // Runs the remaining commands on the simulation
        val linesAfterPlaceCommand = linesWithStartingPlaceCommand.drop(1)
        linesAfterPlaceCommand.forEach { line ->
            val transform = (parser.getTransformFromCommandString(line) ?: return@forEach).wrapIllegalArguments()

            // Documentation contract on ICommand states that if a non-null state is given, a non-null state should be returned
            val success = sim.apply { state ->
                transform(state)!!
            }
        }
    }
}
