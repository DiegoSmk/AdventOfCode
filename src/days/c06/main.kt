package days.c06

import utils.measureExecutionTime
import utils.printLine
import utils.printTestResult
import utils.readInput

private const val CORRECT_ANSWER_TEST = 41

fun main() {
    val inputText = readInput("inputC6e")
    val input = readInput("inputC6")

    println(inputText)

    measureExecutionTime({
        val resultTest = countVisitedPositions(inputText)
        println("Distinct positions visited: $resultTest")
        printTestResult(resultTest, CORRECT_ANSWER_TEST)

    }, "MAIN - PART ONE - Test")
    printLine()

    measureExecutionTime({
        val mapGrid = MapGrid(input)
        val result = mapGrid.run()
        println("Distinct positions visited: $result")

    }, "MAIN - PART ONE - 1")
    printLine()

    measureExecutionTime({
        val result = countVisitedPositions(input)
        println("Distinct positions visited: $result")

    }, "MAIN - PART ONE - 2")
    printLine()

    measureExecutionTime({
        val resultTest = 0
        printTestResult(resultTest, 0)
    }, "MAIN - PART TWO - Test")
    printLine()

    measureExecutionTime({}, "MAIN - PART TWO")
    printLine()
}

// MAIN - code 1 ===================================================================
/**
 * Represents a grid map with obstacles, an initial guard position, and patrol logic.
 *
 * @param grid A list of strings representing the lab's grid.
 */
class MapGrid(private val grid: List<String>) {
    // Directions as (dx, dy) representing movement on a 2D plane
    private val directions = listOf(
        Pair(-1, 0), // Up
        Pair(0, 1),  // Right
        Pair(1, 0),  // Down
        Pair(0, -1)  // Left
    )

    // Initial guard state
    private var guardX: Int = 0
    private var guardY: Int = 0
    private var guardDirection: Int = 0 // Index of the directions list

    init {
        initializeGuard()
    }

    /**
     * Locates the guard's initial position and direction on the grid.
     */
    private fun initializeGuard() {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                when (grid[i][j]) {
                    '^' -> {
                        guardX = i
                        guardY = j
                        guardDirection = 0 // Up
                        return
                    }
                    '>' -> {
                        guardX = i
                        guardY = j
                        guardDirection = 1 // Right
                        return
                    }
                    'v' -> {
                        guardX = i
                        guardY = j
                        guardDirection = 2 // Down
                        return
                    }
                    '<' -> {
                        guardX = i
                        guardY = j
                        guardDirection = 3 // Left
                        return
                    }
                }
            }
        }

        throw IllegalStateException("Guard position not found in the map.")
    }

    /**
     * Runs the simulation and returns the number of distinct positions visited by the guard.
     */
    fun run(): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        visited.add(Pair(guardX, guardY))

        while (true) {
            val nextX = guardX + directions[guardDirection].first
            val nextY = guardY + directions[guardDirection].second

            // Check if the guard is moving out of bounds
            if (nextX !in grid.indices || nextY !in grid[nextX].indices) break

            // Check if there's an obstacle in the next position
            if (grid[nextX][nextY] == '#') {
                // Turn right
                guardDirection = (guardDirection + 1) % directions.size
            } else {
                // Move forward
                guardX = nextX
                guardY = nextY
                visited.add(Pair(guardX, guardY))
            }
        }

        return visited.size
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