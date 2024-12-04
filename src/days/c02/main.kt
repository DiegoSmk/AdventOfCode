package days.c02

import utils.measureExecutionTime
import utils.readInput
import kotlin.math.abs

fun main() {
    val input = readInput("inputC2")

    // ========== PART TWO ==============
    println("========== PART ONE ==============\n")

    // Measure the execution time of the process
    measureExecutionTime({
        val countSafeReports = countSafeReports(input)

        println("The number of safe reports is $countSafeReports")
    })

    measureExecutionTime({
        // Process the reports and count how many are safe
        val safeReportsCount = input.count { isReportSafe(it) }

        // Display the result
        println("CHATGPT - The number of safe reports is $safeReportsCount")
    }, "CHATGPT")

    // ========== PART TWO ==============
    println("========== PART TWO ==============\n")

    measureExecutionTime({
        val countSafeReportsWithProblemDampener = countSafeReports(input, true)

        println("The number of safe reports is $countSafeReportsWithProblemDampener")
    })

    measureExecutionTime({
        // Process the reports and count how many are safe
        val safeReportsCount = input.count { report ->
            isSafeReportWithProblemDampener(report.split(" ").map { it.toInt() })
        }

        // Display the result
        println("CHATGPT - Number of safe reports with Problem Dampener: $safeReportsCount")
    }, "CHATGPT")

}

/**
 * Counts the number of safe reports based on the given criteria.
 *
 * A report is considered safe if:
 * 1. All levels are either increasing or decreasing.
 * 2. Each adjacent level differs by at least 1 and at most 3.
 *
 * @param reports A list of reports, each represented as a string of space-separated integers.
 * @return The count of safe reports.
 */
fun countSafeReports(reports: List<String>, enableProblemDampener: Boolean = false): Int {
    var countSafe = 0 // Initialize the count of safe reports

    for (report in reports) {
        // Parse the report into a list of integers
        val levels = report.split(" ").map { it.toInt() }

        // Increment the count if the report is safe
        if (isSafe(levels, enableProblemDampener)) {
            countSafe++
        }
    }

    return countSafe
}

fun isSafe(levels: List<Int>, enableProblemDampener: Boolean = false): Boolean {
    // Assume initially that the report is both increasing and decreasing
    var isIncreasing = true
    var isDecreasing = true

    // Iterate through pairs of consecutive levels
    for (i in 0..<levels.size -1) {
        val diff =  levels[i +1] - levels[i] // Difference between adjacent levels

        // If the difference is negative, it's not increasing
        if (diff < 0) isIncreasing = false

        // If the difference is positive, it's not decreasing
        if (diff > 0) isDecreasing = false

        if (!isIncreasing && !isDecreasing) {
            //DAMPENER SAVE
            if (enableProblemDampener) {
                return dampener(levels)
            } else {
                return false
            }

        }

        // If the difference is out of the acceptable range, mark both as false
        if (abs(diff) !in 1..3) {
            //DAMPENER SAVE
            if (enableProblemDampener) {
                return dampener(levels)
            } else {
                return false
            }
        }
    }

    // The report is safe if it is either increasing or decreasing
    return true
}

fun dampener(list: List<Int>): Boolean {
    for (j in list.indices) {
        val newList = list.toMutableList().apply { removeAt(j) }
        if (isSafe(newList, false)) {
            return true
        }
    }
    return false
}

// ========== (chatgpt solution) ==============
/**
 * Checks if a report is safe.
 *
 * @param report A single report represented as a string of space-separated numbers.
 * @return `true` if the report is safe, otherwise `false`.
 */
fun isReportSafe(report: String): Boolean {
    // Convert the report into a list of numbers
    val levels = report.split(" ").map { it.toInt() }

    // Check if levels are in strictly increasing order
    val isIncreasing = levels.zipWithNext().all { (a, b) -> b > a && (b - a) in 1..3 }

    // Check if levels are in strictly decreasing order
    val isDecreasing = levels.zipWithNext().all { (a, b) -> b < a && (a - b) in 1..3 }

    // Return true if the report is safe based on either condition
    return isIncreasing || isDecreasing
}

/**
 * Checks if a report is safe, considering that one level can be removed to make it safe.
 *
 * @param levels List of integers representing the levels in the report.
 * @return true if the report is safe (either directly or by removing one level).
 */
fun isSafeReportWithProblemDampener(levels: List<Int>): Boolean {
    // First, check if the report is safe without removing any level
    if (isSafeReport(levels)) {
        return true
    }

    // If not safe, check if removing one level can make it safe
    for (i in levels.indices) {
        val modifiedLevels = levels.toMutableList().apply { removeAt(i) }
        if (isSafeReport(modifiedLevels)) {
            return true
        }
    }

    return false
}

/**
 * Checks if a report is safe, meaning the levels are either strictly increasing or strictly decreasing,
 * and the difference between adjacent levels is at least 1 and at most 3.
 *
 * @param levels List of integers representing the levels in the report.
 * @return true if the report is safe.
 */
fun isSafeReport(levels: List<Int>): Boolean {
    // Check if the levels are either strictly increasing or strictly decreasing
    val isIncreasing = levels.zipWithNext().all { (a, b) -> b > a && (b - a) in 1..3 }
    val isDecreasing = levels.zipWithNext().all { (a, b) -> a > b && (a - b) in 1..3 }

    return isIncreasing || isDecreasing
}