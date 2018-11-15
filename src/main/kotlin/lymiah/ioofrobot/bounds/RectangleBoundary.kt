package lymiah.ioofrobot.bounds

import lymiah.ioofrobot.robot.RobotState

/**
 * Describes a Rectangle Boundary.
 *
 * Facing is not constrained.
 *
 * @property width Limit of the X coordinate (exclusive). Must be positive.
 * @property height Limit of the Y coordinate (exclusive). Must be positive.
 */
data class RectangleBoundary(
        val width: Int = 5,
        val height: Int = 5
) : IBoundary {
    /**
     * Checks that the position is within the rectangle.
     *
     * @param state The robot state to be validated.
     * @return True iff X is within [0, width) and Y is within [0, height). False otherwise.
     */
    override fun inBounds(state: RobotState): Boolean {
        return state.x in 0 until width && state.y in 0 until height
    }
}