package days.c06

import utils.measureExecutionTime
import utils.printLine
import utils.printTestResult
import utils.readInput

private const val CORRECT_ANSWER_TEST = 41

fun main() {
    val inputText = readInput("inputC6e")
    val input = readInput("inputC6")

    measureExecutionTime({
        val resultTest = countVisitedPositions(inputText)
        println("Distinct positions visited: $resultTest")
        printTestResult(resultTest, CORRECT_ANSWER_TEST)

    }, "MAIN - PART ONE - Test")
    printLine()

    measureExecutionTime({
        val guardPatrol = GuardPatrol(input)
        val result = guardPatrol.calculateDistinctVisitedPositions()
        println("Distinct positions visited: $result")

    }, "MAIN - PART ONE - Used class GuardPatrol")
    printLine()

    measureExecutionTime({
        val result = countVisitedPositions(input)
        println("Distinct positions visited: $result")

    }, "MAIN - PART ONE - Used fun countVisitedPositiion")
    printLine()

    measureExecutionTime({
        val guardPatrol = GuardPatrol(inputText)
        val resultTest = guardPatrol.findValidObstaclePositions().size
        printTestResult(resultTest, 6)
    }, "MAIN - PART TWO - Test")
    printLine()

    measureExecutionTime({
        val guardPatrol = GuardPatrol(input)
        val result = guardPatrol.findValidObstaclePositions().size

        println(result)
    }, "MAIN - PART TWO")
    printLine()
}

// MAIN - code 1 ===================================================================
/**
 * Represents a position on the grid with x and y coordinates.
 */
data class Position(val x: Int, val y: Int)

/**
 * Enumeration representing the four cardinal directions and their respective movement offsets.
 */
enum class Direction(val dx: Int, val dy: Int) {
    UP(0, -1), RIGHT(1, 0), DOWN(0, 1), LEFT(-1, 0);

    fun turnRight(): Direction {
        return when (this) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
        }
    }
}

/**
 * Represents a record of a position visited by the guard during patrol,
 * including its direction and whether it caused a loop.
 */
data class PatrolLog(val position: Position, val direction: Direction, private var isLoop: Boolean = false) {
    /**
     * Marks the current patrol log as part of a loop.
     */
    fun markAsLoop() {
        this.isLoop = true
    }

    /**
     * Checks if this patrol log is a loop.
     * @return True if it's a loop, false otherwise.
     */
    fun isLopp(): Boolean = isLoop
}

/**
 * Represents a patrol grid map for a guard. Handles simulation of guard movements
 * and identifies valid positions for adding obstacles to create a loop.
 *
 * @param grid The grid map represented as a list of strings.
 */
class GuardPatrol(private val grid: List<String>) {
    private val guardSymbols = setOf('^', 'v', '<', '>')
    private val obstacleSymbols = setOf('#')

    /**
     * Finds the starting position of the guard on the grid.
     * @return The starting position as a [Position].
     */
    private fun findGuardStartingPosition(): Position {
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                if (grid[y][x] in guardSymbols) {
                    return Position(x, y)
                }
            }
        }
        throw IllegalStateException("Guard starting position not found on the grid.")
    }

    /**
     * Determines the initial direction of the guard based on its symbol.
     * @param position The position of the guard.
     * @return The guard's initial direction as a [Direction].
     */
    private fun determineGuardDirection(position: Position): Direction {
        return when (grid[position.y][position.x]) {
            '^' -> Direction.UP
            'v' -> Direction.DOWN
            '<' -> Direction.LEFT
            '>' -> Direction.RIGHT
            else -> throw IllegalStateException("Invalid guard direction at position.")
        }
    }

    /**
     * Checks if a position is out of bounds on the grid.
     * @param position The position to check.
     * @return True if the position is outside the grid, false otherwise.
     */
    private fun isOutOfBounds(position: Position): Boolean {
        return position.x !in grid[0].indices || position.y !in grid.indices
    }

    /**
     * Checks if a position contains an obstacle.
     * @param position The position to check.
     * @return True if the position contains an obstacle, false otherwise.
     */
    private fun isObstacle(position: Position): Boolean {
        if (isOutOfBounds(position)) return false
        return grid[position.y][position.x] in obstacleSymbols
    }

    /**
     * Counts the number of unique positions visited by the guard.
     * @param patrolLogs The set of patrol logs.
     * @return The count of distinct positions visited.
     */
    private fun countDistinctVisitedPositions(patrolLogs: Set<PatrolLog>): Int {
        return patrolLogs.map { it.position }.toSet().size
    }

    /**
     * Simulates the guard's patrol movements, optionally adding a new obstacle.
     * @param additionalObstacle The position of the additional obstacle, if any.
     * @return A set of [PatrolLog] representing the guard's movements.
     */
    private fun simulateGuardMoviment(additionalObstacle: Position? = null): Set<PatrolLog> {
        val patrolLogs = mutableSetOf<PatrolLog>()
        var guardPosition = findGuardStartingPosition()
        var guardDirection = determineGuardDirection(guardPosition)

        while (true) {
            if (isOutOfBounds(guardPosition)) break

            val nextPosition = Position(guardPosition.x + guardDirection.dx, guardPosition.y + guardDirection.dy)
            val currentLog = PatrolLog(guardPosition, guardDirection)

            // Check for loop
            if (patrolLogs.contains(currentLog)) {
                patrolLogs.last().markAsLoop()
                return patrolLogs
            }
            patrolLogs.add(currentLog)

            // Handle obstacle or move forward
            if (isObstacle(nextPosition) || nextPosition == additionalObstacle) {
                guardDirection = guardDirection.turnRight()
            } else {
                guardPosition = nextPosition
            }
        }

        return patrolLogs
    }

    /**
     * Runs the patrol simulation without additional obstacles.
     * @return The number of distinct positions visited by the guard.
     */
    fun calculateDistinctVisitedPositions(): Int {
        return countDistinctVisitedPositions(simulateGuardMoviment())
    }

    /**
     * Finds all valid positions for placing an obstacle to cause a loop.
     * @return A list of valid [Position] for placing obstacles.
     */
    fun findValidObstaclePositions(): List<Position> {
        val validPositions = mutableListOf<Position>()
        val guardStart = findGuardStartingPosition()

        for (y in grid.indices) {
            for (x in grid[y].indices) {
                val position = Position(x, y)
                if (grid[y][x] == '.' && position != guardStart) {
                    val simulationResult  = simulateGuardMoviment(position)
                    if (simulationResult.last().isLopp()) {
                        validPositions.add(position)
                    }
                }
            }
        }

        return validPositions
    }
}

// MAIN - code 2 ===================================================================
/**
 * Simulates the movement of a guard on a map following strict rules
 * and calculates the number of distinct positions visited.
 *
 * @param map A list of strings representing the lab's grid.
 * @return The count of distinct positions visited by the guard.
 */
fun countVisitedPositions(map: List<String>): Int {
    /**
     * Directions representing movements: [x, y]
     * Order: Up, Right, Down, Left
     */
    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    val visited = mutableSetOf<Pair<Int, Int>>() // Tracks unique visited positions

    // Find the guard's starting position and direction
    var guardPosition: Pair<Int, Int>? = null
    var directionIndex = 0

    for (i in map.indices) {
        for (j in map[i].indices) {
            when (map[i][j]) {
                '^' -> { guardPosition = Pair(i, j); directionIndex = 0 } // Facing up
                '>' -> { guardPosition = Pair(i, j); directionIndex = 1 } // Facing right
                'v' -> { guardPosition = Pair(i, j); directionIndex = 2 } // Facing down
                '<' -> { guardPosition = Pair(i, j); directionIndex = 3 } // Facing left
            }
        }
    }

    // If no starting position is found, exit early
    guardPosition ?: return 0

    // Add the starting position to the set of visited positions
    visited.add(guardPosition)

    // Simulate the guard's movement until they leave the map
    while (guardPosition!!.first in map.indices && guardPosition.second in map[0].indices) {
        val (currentX, currentY) = guardPosition
        val (deltaX, deltaY) = directions[directionIndex]
        val nextPosition = Pair(currentX + deltaX, currentY + deltaY)

        // Check if the next position is patrolable
        if (isPatrolable(nextPosition, map)) {
            guardPosition = nextPosition // Move to the next position
            visited.add(guardPosition)  // Mark as visited
        } else {
            directionIndex = (directionIndex + 1) % 4 // Turn 90 degrees to the right
        }
    }

    // Return the count of distinct positions visited
    return visited.size - 1
}

/**
 * Checks if the guard can patrol to a given position.
 *
 * @param position The position to check.
 * @param map The map representing the lab grid.
 * @return True if the position is patrolable, false otherwise.
 */
fun isPatrolable(position: Pair<Int, Int>, map: List<String>): Boolean {
    return if(position.first in map.indices && position.second in map[0].indices) {
        map[position.first][position.second] != '#'
    } else {
        true
    }
}