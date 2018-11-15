package lymiah.ioofrobot.robot

/**
 * Represents the four cardinal directions.
 * Origin (0, 0) is South-East.
 */
enum class Facing(index: Int, val dx: Int, val dy: Int) {
    NORTH(0, 0, 1),
    EAST(1, 1, 0),
    SOUTH(2, 0, -1),
    WEST(3, -1, 0);

    /**
     * The cardinal direction 90 degrees anti-clockwise
     */
    val left: Facing = values()[(index - 1 + values().size) % values().size]

    /**
     * The cardinal direction 90 degrees clockwise
     */
    val right: Facing = values()[(index + 1) % values().size]

    /**
     * The cardinal direction 180 degrees clockwise
     */
    val opposite: Facing = values()[(index + 2) % values().size]

    override fun toString(): String {
        return "Facing.${name}(dx=${dx}, dy=${dy})"
    }
}
