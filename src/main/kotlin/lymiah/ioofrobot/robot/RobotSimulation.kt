package lymiah.ioofrobot.robot

import lymiah.ioofrobot.bounds.IBoundary
import lymiah.ioofrobot.bounds.RectangleBoundary

/**
 *  Represents a simulation of a Robot.
 *
 *  This class allows transformation functions to be applied to the state if within the boundary.
 *
 *  @property boundary Boundary class for identifying invalid positions. The default limits X and Y to [0, 5).
 *  @property initialState The initial state the robot should have (must satisfy boundary, this is NOT checked).
 */
class RobotSimulation (
        val boundary: IBoundary = RectangleBoundary(5, 5),
        initialState: RobotState
){
    /**
     * Current state of the robot.
     */
    var state: RobotState = initialState
        protected set

    /**
     * Apply a transformation to the robot state.
     *
     * @param transform Function mapping a previous state to a new state.
     * @return if the new state was successfully applied (passes boundary conditions).
     */
    fun apply(transform: (RobotState) -> RobotState) : Boolean {
        val nextState = transform(state)

        if(!boundary.inBounds(nextState))
            return false

        state = nextState
        return true

    }

}