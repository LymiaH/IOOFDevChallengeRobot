package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState
import java.io.BufferedWriter

/**
 * Describes a command that takes a string parameter with the current state and returns a new state.
*/
interface ICommand {
    /**
     * Transforms the current robot state or provides a new one.
     *
     * Must return a non-null state if the current state is not null.
     * If state is null and command requires a state to work then return null.
     * Otherwise return a non-null state if the command supports it.
     *
     * @param args Text that comes after the command. Can be an empty string.
     * @param state Current robot state (this can be null if state not initialized yet)
     * @param output For writing any messages back to the command sender
     * @return Next robot state
     * @throws IllegalArgumentException Can be thrown if there is incorrect args.
     */
    fun apply(args: String, state: RobotState?, output: BufferedWriter): RobotState?
}
