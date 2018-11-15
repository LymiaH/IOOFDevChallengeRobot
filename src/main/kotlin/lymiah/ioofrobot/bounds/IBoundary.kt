package lymiah.ioofrobot.bounds

import lymiah.ioofrobot.robot.RobotState

/**
 * Represents a class that validates robot states.
 */
interface IBoundary {
    /**
     * Check if the robot state is valid.
     *
     * @param state The robot state to be validated.
     * @return True iff the given state is within bounds. False otherwise.
     */
    fun inBounds(state: RobotState): Boolean
}
