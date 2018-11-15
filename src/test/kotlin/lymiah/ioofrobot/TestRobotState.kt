package lymiah.ioofrobot

import lymiah.ioofrobot.robot.Facing
import lymiah.ioofrobot.robot.RobotState
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestRobotState() {
    @Test
    fun `move north`() {
        val x = 2654
        val y = 1497
        val initialState = RobotState(x, y, Facing.NORTH)
        val actualState = initialState.move()
        val expectedState = RobotState(x, y + 1, Facing.NORTH)
        assert(actualState == expectedState)
    }

    @Test
    fun `move south`() {
        val x = 5968
        val y = 1097
        val initialState = RobotState(x, y, Facing.SOUTH)
        val actualState = initialState.move()
        val expectedState = RobotState(x, y - 1, Facing.SOUTH)
        assert(actualState == expectedState)
    }

    @Test
    fun `move east`() {
        val x = 1397
        val y = 142
        val initialState = RobotState(x, y, Facing.EAST)
        val actualState = initialState.move()
        val expectedState = RobotState(x + 1, y, Facing.EAST)
        assert(actualState == expectedState)
    }

    @Test
    fun `move west`() {
        val x = 1276
        val y = 2765
        val initialState = RobotState(x, y, Facing.WEST)
        val actualState = initialState.move()
        val expectedState = RobotState(x - 1, y, Facing.WEST)
        assert(actualState == expectedState)
    }

    @Test
    fun `right turns clockwise`() {
        val x = 166
        val y = 742
        val expectedStates = listOf(
                RobotState(x, y, Facing.NORTH),
                RobotState(x, y, Facing.EAST),
                RobotState(x, y, Facing.SOUTH),
                RobotState(x, y, Facing.WEST)
        )
        for(i in 0 until expectedStates.size) {
            assert(expectedStates[i].right() == expectedStates[(i + 1) % expectedStates.size])
        }
    }

    @Test
    fun `left turns anti-clockwise`() {
        val x = 13496
        val y = 421
        val expectedStates = listOf(
                RobotState(x, y, Facing.NORTH),
                RobotState(x, y, Facing.WEST),
                RobotState(x, y, Facing.SOUTH),
                RobotState(x, y, Facing.EAST)
        )
        for(i in 0 until expectedStates.size) {
            assert(expectedStates[i].left() == expectedStates[(i + 1) % expectedStates.size])
        }
    }
}
