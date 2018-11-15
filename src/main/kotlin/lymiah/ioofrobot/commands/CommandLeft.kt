package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState

/**
 * Turn robot left.
 */
class CommandLeft : ICommand {
    /**
     * Turns the robot left.
     *
     * @param args Ignored.
     * @param state Current Robot State.
     * @return Robot State rotated 90 degrees anti-clockwise.
     */
    override fun apply(args: String, state: RobotState?): RobotState? {
        if (state == null) return null
        return state.left()
    }

}
