package lymiah.ioofrobot.commands

import lymiah.ioofrobot.robot.Facing
import lymiah.ioofrobot.robot.RobotState

class CommandPlace : ICommand {
    companion object {
        fun facingFromString(text: String): Facing? {
            if(text.isEmpty()) return null
            return when(text[0]) {
                'n', 'N' -> Facing.NORTH
                's', 'S' -> Facing.SOUTH
                'w', 'W' -> Facing.WEST
                'e', 'E' -> Facing.EAST
                else -> null
            }
        }
    }

    /**
     * Create a new Robot State with the given position and orientation.
     *
     * @param args X,Y,N(ORTH)/S(OUTH)/E(AST)/W(EST)
     * @param state Ignored
     * @return a new robot state as described by the args
     * @throws IllegalArgumentException if the args are incorrect
     */
    override fun apply(args: String, state: RobotState?): RobotState? {
        val split = args.split(',')
        if (split.size != 3) {
            throw IllegalArgumentException("Expected X,Y,N(ORTH)/S(OUTH)/E(AST)/W(EST)")
        }
        val x = split[0].toIntOrNull() ?: throw IllegalArgumentException("X must be an integer (was: ${split[0]})")
        val y = split[1].toIntOrNull() ?: throw IllegalArgumentException("Y must be an integer (was: ${split[1]})")
        val facing = facingFromString(split[2]) ?:
                throw IllegalArgumentException("Facing must be N(ORTH)/S(OUTH)/E(AST)/W(EST) (was: ${split[2]})")
        return RobotState(x, y, facing)
    }

}
