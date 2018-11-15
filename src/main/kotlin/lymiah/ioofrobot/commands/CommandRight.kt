package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState

/**
 * Turn robot right.
 */
class CommandRight : ICommand {
    /**
     * Turns the robot right.
     *
     * @param args Ignored.
     * @param state Current Robot State.
     * @return Robot State rotated 90 degrees clockwise.
     */
    override fun apply(args: String, state: RobotState?): RobotState? {
        if (state == null) return null
        return state.right()
    }

}
