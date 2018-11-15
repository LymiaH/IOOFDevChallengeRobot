package lymiah.ioofrobot

import lymiah.ioofrobot.bounds.IBoundary
import lymiah.ioofrobot.bounds.RectangleBoundary
import lymiah.ioofrobot.commands.CommandRegistry
import lymiah.ioofrobot.robot.RobotSimulation
import lymiah.ioofrobot.robot.RobotState
import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintStream

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
    return fun(state: RobotState?): RobotState? {
        try {
            return this(state)
        } catch (e: IllegalArgumentException) {
            eprintln("Error Executing Command: " + e.message)
        }
        // Return the current state if errored
        return state
    }
}

/**
 * Attempts to parse the line, and obtain an initial state from it.
 *
 * @param parser CommandRegistry for parsing lines.
 * @param boundary Boundary condition for checking if the initial state is valid.
 * @return True iff has a valid initial state. False otherwise.
 */
fun lineHasInitialState(parser: CommandRegistry, boundary: IBoundary, line: String): Boolean {
    val transform = (parser.getTransformFromCommandString(line) ?: return false).wrapIllegalArguments()
    val initialState = transform(null) ?: return false
    // Make sure the placement is valid (since the simulation does not enforce this on initial state)
    if (!boundary.inBounds(initialState)) {
        eprintln("Place Command resulted invalid state: ${initialState}")
        return false
    }
    // Place command at head of sequence now
    return true
}

/**
 * Attempts to parse the line, and obtain an initial state from it.
 *
 * @param parser CommandRegistry for parsing lines
 * @param sim Simulation of robot
 * @return True iff the command was successful. False otherwise.
 */
fun parseCommandAndApplyToSimulation(parser: CommandRegistry, sim: RobotSimulation, line: String): Boolean {
    val transform = (parser.getTransformFromCommandString(line) ?: return false).wrapIllegalArguments()

    // Documentation contract on ICommand states that if a non-null state is given, a non-null state should be returned
    return sim.apply { state ->
        transform(state)!!
    }
}

fun processInput(input: BufferedReader, output: PrintStream) {

    val parser = CommandRegistry(registerDefaults = true, output = output)
    val boundary = RectangleBoundary(width = 5, height = 5)

    // Runs commands till once specifies initial state (by default only the PlaceCommand)
    var initialStateLine: String? = null
    while(true) {
        val line = input.readLine() ?: break
        if(lineHasInitialState(parser, boundary, line)) {
            initialStateLine = line
            break
        }
    }

    if(initialStateLine == null) {
        eprintln("No Place Command found?")
        return
    }

    // We've confirmed that the first element is a valid Place Command at this point, so this should not NPE
    val initialState = parser.getTransformFromCommandString(initialStateLine)!!(null)!!
    eprintln("Initial State: ${initialState}")

    // Creating a simulation using the initial state found
    val sim =  RobotSimulation(initialState = initialState) //TODO: Configurable boundary?
    input.useLines{ ls ->
        //val lines = reader.lines().asSequence()
        val lines = ls.onEach {
            eprintln("Read: ${it}")
        }

        // Runs the remaining commands on the simulation
        lines.forEach { line ->
            parseCommandAndApplyToSimulation(parser, sim, line)
            output.flush()
        }
    }
}

fun main(args: Array<String>) {
    // Take input from stdin if not file is specified, or take from file if one is specified.
    val input: BufferedReader = when(args.size) {
        0 -> System.`in`.bufferedReader()
        1 -> FileReader(args[0]).buffered()
        else -> throw IllegalArgumentException("Expected no input (use stdin) or a single file as input.")
    }

    input.use {
        processInput(input, System.out)
    }
}
