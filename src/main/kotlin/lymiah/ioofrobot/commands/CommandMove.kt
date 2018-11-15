package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState
import java.io.BufferedWriter
import java.io.PrintStream

/**
 * Move robot one space forward.
 */
class CommandMove : ICommand {
    /**
     * Moves the robot one space forward.
     *
     * @param args Ignored.
     * @param state Current Robot State.
     * @param output For writing any messages back to the command sender
     * @return Robot State moved in its facing direction. Or null if the state was null.
     */
    override fun apply(args: String, state: RobotState?, output: PrintStream): RobotState? {
        if (state == null) return null
        return state.move()
    }

}
