package lymiah.ioofrobot.robot

/**
 * Represents the four cardinal directions.
 * Origin (0, 0) is South-East.
 */
enum class Facing(val index: Int, val dx: Int, val dy: Int) {
    NORTH(0, 0, 1),
    EAST(1, 1, 0),
    SOUTH(2, 0, -1),
    WEST(3, -1, 0);

    // TODO: If performance is needed, these can be vals instead
    /**
     * The cardinal direction 90 degrees anti-clockwise
     */
    fun getLeft(): Facing = values()[(this.index - 1 + values().size) % values().size]

    /**
     * The cardinal direction 90 degrees clockwise
     */
    fun getRight(): Facing = values()[(index + 1) % values().size]

    /**
     * The cardinal direction 180 degrees clockwise
     */
    fun getOpposite(): Facing = values()[(index + 2) % values().size]

    override fun toString(): String {
        return "Facing.${name}(dx=${dx}, dy=${dy})"
    }
}
