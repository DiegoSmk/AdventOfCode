package days.c08

import utils.measureExecutionTime
import utils.printLine
import utils.printTestResult
import utils.readInput

fun main() {
    val inputTest = readInput("inputC8e")
    val input = readInput("inputC8")

    measureExecutionTime({
        val resultTest = countAntinodes(inputTest)
        println("Total number of antinode locations (Part One - Test): $resultTest")
        printTestResult(resultTest, 14)

    }, "MAIN - PART ONE - Test")
    printLine()

    measureExecutionTime({
        val result = countAntinodes(input)
        println("Total number of antinode locations (Part One): $result")
    }, "MAIN - PART ONE")
    printLine()

    measureExecutionTime({
        val resultTest = countAntinodesPartTwo(inputTest)
        println("Total number of antinode locations (Part Two - Test): $resultTest")
        printTestResult(resultTest, 34)

    }, "MAIN - PART TWO - Test")
    printLine()

    measureExecutionTime({
        val result = countAntinodesPartTwo(input)
        println("Total number of antinode locations (Part Two): $result")

    }, "MAIN - PART TWO")
    printLine()
}

/**
 * This function calculates the number of unique antinodes generated by antennas on a map.
 * Each antinode is a specific point aligned with two antennas of the same frequency,
 * and located at a distance twice the separation of the antennas. Antinodes are only
 * counted if they fall within the bounds of the map.
 *
 * @param map A list of strings representing the map. Each character represents either
 * an antenna (a letter, digit, or symbol) or empty space (e.g., '.').
 * @return The count of unique locations that contain an antinode within the map bounds.
 */
fun countAntinodes(map: List<String>): Int {
    // Step 1: Map each antenna type to its positions on the grid
    val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()

    for (y in map.indices) {
        for (x in map[y].indices) {
            val char = map[y][x]
            if (char.isLetterOrDigit()) {
//                if (antennas.containsKey(char)) {
//                    antennas[char]?.add(Pair(x, y))
//                } else {
//                    antennas[char] = mutableListOf(Pair(x, y))
//                }
                // Add the position of the antenna to the map.
                antennas.computeIfAbsent(char) { mutableListOf() }.add(Pair(x, y))

            }
        }
    }

    // Step 2: Calculate unique antinode locations
    val antinodes = mutableSetOf<Pair<Int, Int>>()

    for (positions in antennas.values) {
        if (positions.size < 2) continue // Skip if there are fewer than two antennas of the same type

        // Iterate through all unique pairs of antenna positions
        for (i in positions.indices) {
            for (j in i + 1..<positions.size) {
                // x -> ROW
                // y -> COL
                val (x1, y1) = positions[i]
                val (x2, y2) = positions[j]

                // Calculate the vector difference between the two antennas
                val deltaX = x2 - x1
                val deltaY = y2 - y1

                // Calculate the positions of the two possible antinodes
                val antinode1 = Pair(x1 + 2 * deltaX, y1 + 2 * deltaY)
                val antinode2 = Pair(x2 - 2 * deltaX, y2 - 2 * deltaY)

                // Add antinode1 if it is within the map bounds
                if (antinode1.first in map[0].indices && antinode1.second in map.indices) {
                    antinodes.add(antinode1)
                }

                // Add antinode2 if it is within the map bounds
                if (antinode2.first in map[0].indices && antinode2.second in map.indices) {
                    antinodes.add(antinode2)
                }
            }
        }
    }

    // Return the count of unique antinode positions
    return antinodes.size
}

/**
 * Counts the antinodes based on the updated rules.
 * Antinodes occur at any grid position in line with at least two antennas of the same frequency,
 * regardless of distance. Additionally, every antenna itself is also an antinode (unless it is the only one of its frequency).
 */
fun countAntinodesPartTwo(map: List<String>): Int {
    // Step 1: Create a map to store antenna positions categorized by their frequency/letter
    val antennas = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()

    for (y in map.indices) {
        for (x in map[y].indices) {
            val char = map[y][x]
            if (char.isLetterOrDigit()) {
                antennas.computeIfAbsent(char) { mutableListOf() }.add(Pair(x, y))
            }
        }
    }

    // Step 2: Store unique antinode locations
    val antinodes = mutableSetOf<Pair<Int, Int>>()

    // Step 3: Iterate through all antenna positions
    for (positions in antennas.values) {
        // If only one antenna of this frequency exists, skip alignment calculation
        if (positions.size == 1) {
            antinodes.add(positions[0]) // Add single antenna as an antinode
            continue
        }

        // For each pair of antennas of the same frequency
        for (first in positions) {
            for (second in positions) {
                if (first == second) continue

                val (x1, y1) = first
                val (x2, y2) = second

                // Calculate the distance vector between two antennas
                val deltaX = x2 - x1
                val deltaY = y2 - y1

                // Check positions in both directions from the first antenna
                var positiveNextLocation = first
                do {
                    positiveNextLocation = Pair(positiveNextLocation.first + deltaX, positiveNextLocation.second + deltaY)
                    if (positiveNextLocation.first in map[0].indices && positiveNextLocation.second in map.indices) {
                        antinodes.add(positiveNextLocation)
                    }
                } while (positiveNextLocation.first in map[0].indices && positiveNextLocation.second in map.indices)

                // Check positions in the negative direction from the first antenna
                var negativeNextLocation = first
                do {
                    negativeNextLocation = Pair(negativeNextLocation.first - deltaX, negativeNextLocation.second - deltaY)
                    if (negativeNextLocation.first in map[0].indices && negativeNextLocation.second in map.indices) {
                        antinodes.add(negativeNextLocation)
                    }
                } while (negativeNextLocation.first in map[0].indices && negativeNextLocation.second in map.indices)
            }
        }
    }

    // Step 4: Return the total number of unique antinodes
    return antinodes.size
}