package days.c01

import utils.readInput
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main () {
    // Measure code in milleSeconds.
    val executionTime = measureTimeMillis {
        // ===== PART ONE ======
        val input = readInput("inputC1") // Reads input data from a file or other source
        val organizedLists = organizeList(input)  // Organizes the input into sorted left and right lists

        // Calculate the total distance for Part One
        val totalDistance = calculateTotalDistance(organizedLists.left, organizedLists.right)
        println("Part One - Total Distance: $totalDistance")

        // ===== PART TWO ======
        // Create a frequency map for the right list
        val rightFrequencyMap = countOccurrences(organizedLists.right)

        // Calculate the similarity score for Part Two
        val similarityScore  = organizedLists.left.sumOf { num ->
            num * rightFrequencyMap.getOrDefault(num, 0) // Multiply each number in the left list by its frequency in the right list
        }
        println("Part Two - Similarity Score: $similarityScore")
    }

    // Output the total execution time
    println("Execution time: $executionTime ms")
}

// ===== PART ONE ======
/**
 * Data class to store the organized left and right lists.
 */
data class ResultListOrganize(
    val left: List<Int>, // Sorted left list
    val right: List<Int> // Sorted right list
)

/**
 * Organizes the input list into two sorted lists: left and right.
 *
 * @param list The input list of strings, where each string contains a pair of numbers separated by whitespace.
 * @return A ResultListOrganize containing the sorted left and right lists.
 */
fun organizeList (list: List<String>): ResultListOrganize {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    // Split each line into left and right numbers and populate respective lists
    list.forEach { line ->
        val (left, right) = line.split("   ") // Assumes a fixed format with three spaces as a delimiter
        leftList.add(left.toInt())
        rightList.add(right.toInt())
    }

    // Sort both lists in ascending order
    leftList.sort()
    rightList.sort()

    return ResultListOrganize(leftList, rightList)
}

/**
 * Calculates the total distance between two sorted lists.
 *
 * @param leftList The sorted left list.
 * @param rightList The sorted right list.
 * @return The total distance, which is the sum of absolute differences between corresponding elements.
 */
fun calculateTotalDistance(leftList: List<Int>, rightList: List<Int>): Int {
    return leftList.indices.sumOf { i -> abs(leftList[i] - rightList[i]) }
}

// ===== PART TWO ======
/**
 * Counts the occurrences of each number in a list.
 *
 * @param list The list of integers.
 * @return A map where keys are the numbers and values are their respective counts.
 */
fun countOccurrences(list: List<Int>): Map<Int, Int> {
    return list.groupingBy { it }.eachCount() // Groups and counts occurrences of each number
}
