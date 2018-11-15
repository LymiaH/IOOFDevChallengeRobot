package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState
import java.io.BufferedWriter

/**
 * Turn robot right.
 */
class CommandRight : ICommand {
    /**
     * Turns the robot right.
     *
     * @param args Ignored.
     * @param state Current Robot State.
     * @param output For writing any messages back to the command sender
     * @return Robot State rotated 90 degrees clockwise.
     */
    override fun apply(args: String, state: RobotState?, output: BufferedWriter): RobotState? {
        if (state == null) return null
        return state.right()
    }

}
