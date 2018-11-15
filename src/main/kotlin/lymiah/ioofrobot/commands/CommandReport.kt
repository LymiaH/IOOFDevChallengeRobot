package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState

/**
 * Reports the current state of the robot.
 */
class CommandReport : ICommand {
    /**
     * Prints 'X,Y,Facing' of the robot to stdout.
     * Does not print if the state is null.
     *
     * @param args Ignored.
     * @param state Current Robot State.
     * @return unchanged state.
     */
    override fun apply(args: String, state: RobotState?): RobotState? {
        if(state == null) return null
        println("${state.x},${state.y},${state.facing.name}")
        return state
    }

}
