package lymiah.ioofrobot

import lymiah.ioofrobot.commands.*
import lymiah.ioofrobot.robot.Facing
import lymiah.ioofrobot.robot.RobotState
import lymiah.ioofrobot.util.NulledOutputStream
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.nio.charset.StandardCharsets

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class TestCommands() {
    @Nested
    inner class Place() {
        val commandPlace = CommandPlace()
        val output = PrintStream(NulledOutputStream())

        @Test
        fun `produces result even given a null state`() {
            val actualState = commandPlace.apply("36,2,WEST", null, output)
            val expectedState = RobotState(36, 2, Facing.WEST)
            assert(actualState == expectedState)
        }
    }

    @Nested
    inner class Move() {
        val commandMove = CommandMove()
        val output = PrintStream(NulledOutputStream())

        @Test
        fun `move north`() {
            val x = 571
            val y = 198
            val initialState = RobotState(x, y, Facing.NORTH)
            val actualState = commandMove.apply("", initialState, output)
            val expectedState = RobotState(x, y + 1, Facing.NORTH)
            assert(actualState == expectedState)
        }
    }

    @Nested
    inner class Left() {
        val commandLeft = CommandLeft()
        val output = PrintStream(NulledOutputStream())

        @Test
        fun `turns north to west to south to east then back to north`() {
            val x = 514
            val y = 764
            val expectedStates = listOf(
                    RobotState(x, y, Facing.NORTH),
                    RobotState(x, y, Facing.WEST),
                    RobotState(x, y, Facing.SOUTH),
                    RobotState(x, y, Facing.EAST)
            )
            for(i in 0 until expectedStates.size) {
                assert(commandLeft.apply("", expectedStates[i], output) == expectedStates[(i + 1) % expectedStates.size])
            }
        }

        @Test
        fun `null when null`(){
            assert(commandLeft.apply("", null, output) == null)
        }
    }

    @Nested
    inner class Right() {
        val commandRight = CommandRight()
        val output = PrintStream(NulledOutputStream())

        @Test
        fun `turns north to east to south to west then back to north`() {
            val x = 729
            val y = 432
            val expectedStates = listOf(
                    RobotState(x, y, Facing.NORTH),
                    RobotState(x, y, Facing.EAST),
                    RobotState(x, y, Facing.SOUTH),
                    RobotState(x, y, Facing.WEST)
            )
            for(i in 0 until expectedStates.size) {
                assert(commandRight.apply("", expectedStates[i], output) == expectedStates[(i + 1) % expectedStates.size])
            }
        }

        @Test
        fun `null when null`(){
            assert(commandRight.apply("", null, output) == null)
        }
    }

    @Nested
    inner class Report() {
        val commandReport = CommandReport()
        val buffer = ByteArrayOutputStream()
        val output = PrintStream(buffer, true, StandardCharsets.UTF_8.name())

        @Test
        fun `correctly reports state`() {
            val state = RobotState(5, 60, Facing.WEST)

            commandReport.apply("", state, output)

            assert("5,60,WEST" == buffer.toString(StandardCharsets.UTF_8.name()).trim())
        }

        @Test
        fun `null when null`(){
            commandReport.apply("", null, output)
            assert(buffer.toString().trim().isEmpty())
        }
    }

}
