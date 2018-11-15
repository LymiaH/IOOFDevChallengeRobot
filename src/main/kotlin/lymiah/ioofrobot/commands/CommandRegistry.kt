package lymiah.ioofrobot.commands

import java.util.*

/**
 * Manages a collection of commands.
 * Commands parse a string into a RobotState transforming function.
 *
 * @property registerDefaults Should register default commands.
 */
class CommandRegistry(
        val registerDefaults: Boolean = true
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
}
