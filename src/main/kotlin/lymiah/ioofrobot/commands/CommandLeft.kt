package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState
import java.io.BufferedWriter
import java.io.PrintStream

/**
 * Turn robot left.
 */
class CommandLeft : ICommand {
    /**
     * Turns the robot left.
     *
     * @param args Ignored.
     * @param state Current Robot State.
     * @param output For writing any messages back to the command sender
     * @return Robot State rotated 90 degrees anti-clockwise.
     */
    override fun apply(args: String, state: RobotState?, output: PrintStream): RobotState? {
        if (state == null) return null
        return state.left()
    }

}
