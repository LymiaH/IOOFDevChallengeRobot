package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState
import lymiah.ioofrobot.util.NulledOutputStream
import java.io.PrintStream
import java.util.*

/**
 * Manages a collection of commands.
 * Commands parse a string into a RobotState transforming function.
 *
 * @property registerDefaults Should register default commands.
 */
class CommandRegistry(
        val registerDefaults: Boolean = true,
        val output: PrintStream = PrintStream(NulledOutputStream())
) {
    /**
     * The registered commands.
     * TreeMap is not constant time lookup but this is the easiest way to support case insensitivity.
     */
    private val commands: MutableMap<String, ICommand> = TreeMap(String.CASE_INSENSITIVE_ORDER)

    init {
        if (registerDefaults) {
            register("PLACE", CommandPlace())
            register("MOVE", CommandMove())
            register("LEFT", CommandLeft())
            register("RIGHT", CommandRight())
            register("REPORT", CommandReport())
        }
    }

    /**
     * Register a command.
     *
     * @param name Case insensitive command name
     * @param cmd ICommand implementation
     */
    fun register(name: String, cmd: ICommand) {
        commands[name] = cmd
    }

    /**
     * Obtains a state transformation function given the command string.
     * @return
     * @see ICommand.apply
     */
    fun getTransformFromCommandString(command: String) : ((RobotState?) -> RobotState?)? {
        val spaceIndex = command.indexOf(' ')
        val name: String
        val args: String

        if(spaceIndex != -1) {
            name = command.substring(0, spaceIndex)
            args = command.substring(spaceIndex + 1)
        } else {
            name = command
            args = ""
        }

        val cmd = commands[name] ?: return null

        return { state -> cmd.apply(args, state, output)}
    }
}
