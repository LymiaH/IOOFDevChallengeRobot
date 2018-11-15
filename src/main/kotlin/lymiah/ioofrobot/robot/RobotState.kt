package lymiah.ioofrobot.robot

/**
 * Class holding the Location and Facing of a Robot.
 * Origin (0, 0) is South-East.
 */
data class RobotState (
    val x: Int = 0,
    val y: Int = 0,
    val facing: Facing = Facing.NORTH
) {
    /**
     * Returns the RobotState if moved in the direction it is facing
     */
    fun move() : RobotState {
        return RobotState(x + facing.dx, y + facing.dy, facing)
    }

    /**
     * Returns the RobotState if turned left
     */
    fun left() : RobotState {
        return RobotState(x, y, facing.left)
    }

    /**
     * Returns the RobotState if turned right
     */
    fun right() : RobotState {
        return RobotState(x, y, facing.right)
    }
}
