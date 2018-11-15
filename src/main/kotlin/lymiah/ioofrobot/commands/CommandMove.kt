package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState

/**
 * Move robot one space forward.
 */
class CommandMove : ICommand {
    /**
     * Moves the robot one space forward.
     *
     * @param args Ignored.
     * @param state Current Robot State.
     * @return Robot State moved in its facing direction. Or null if the state was null.
     */
    override fun apply(args: String, state: RobotState?): RobotState? {
        if (state == null) return null
        return state.move()
    }

}
