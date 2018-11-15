package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.RobotState
import java.io.PrintStream

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
     * @param output For writing any messages back to the command sender
     * @return unchanged state.
     */
    override fun apply(args: String, state: RobotState?, output: PrintStream): RobotState? {
        if(state == null) return null
        output.println("${state.x},${state.y},${state.facing.name}")
        return state
    }

}
