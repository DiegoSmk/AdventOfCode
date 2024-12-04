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

        println("Number of safe reports with Problem Dampener $countSafeReportsWithProblemDampener")
    }, "MAIN")

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
 * 3. (Optional) The Problem Dampener allows removing one level to make a report safe.
 *
 * @param reports A list of reports, each represented as a string of space-separated integers.
 * @param enableProblemDampener Whether the Problem Dampener is enabled.
 * @return The count of safe reports.
 */
fun countSafeReports(reports: List<String>, enableProblemDampener: Boolean = false): Int {
    return reports.count { report ->
        val levels = report.split(" ").map(String::toInt)
        isSafeReport(levels, enableProblemDampener)
    }
}

/**
 * Determines if a report is safe based on the criteria.
 *
 * @param levels A list of integer levels in the report.
 * @param enableProblemDampener Whether to enable the Problem Dampener logic.
 * @return True if the report is safe; otherwise, false.
 */
fun isSafeReport(levels: List<Int>, enableProblemDampener: Boolean = false): Boolean {
    if (!isDirectionalWithValidSteps(levels)) {
        return enableProblemDampener && isSafeWithDampener(levels)
    }
    return true
}

/**
 * Check if the levels are directional (all increase or all decrease)
 * and if adjacent diferreces are withn the valid range [1,3]
 *
 * @param levels A list of the integer levels.
 * @return True if the levels are directional and valid; otherwise, false.
 */
fun isDirectionalWithValidSteps(levels: List<Int>): Boolean {
    var isIncreasing = true
    var isDecreasing = true

    for (i in 0..<levels.size - 1) {
        val diff = levels[i + 1] - levels[i] // Difference between adjacent levels

        // If the difference is negative, it's not increasing
        if (diff < 0) isIncreasing = false
        // If the difference is positive, it's not decreasing
        if (diff > 0) isDecreasing = false
        // If the difference is out of the acceptable range, mark both as false
        if (abs(diff) !in 1..3) return false // Invalid difference
        // if it's neither increasing nor decreasing.
        if (!isIncreasing && !isDecreasing) return false
    }
    return true
}

/**
 * Determines if the levels can be made safe by removing one level (Problem Dampener logic).
 *
 * @param levels A list of integer levels.
 * @return True if the levels can be made safe with one removal; otherwise, false.
 */
fun isSafeWithDampener(levels: List<Int>): Boolean {
    for (i in levels.indices) {
        val modifiedLevels = levels.toMutableList().apply { removeAt(i) }
        if (isDirectionalWithValidSteps(modifiedLevels)) return true
    }
    return false
}

// ========== (chatgpt solution) ==============
/**
 * Checks if a report is safe.
 *
 * A report is considered safe if:
 * - Levels are strictly increasing or strictly decreasing.
 * - Differences between adjacent levels are within the range [1, 3].
 *
 * @param report A single report represented as a string of space-separated numbers.
 * @return `true` if the report is safe, otherwise `false`.
 */
fun isReportSafe(report: String): Boolean {
    // Convert the report into a list of numbers
    val levels = report.split(" ").map { it.toInt() }

    return isLevelsSafe(levels)
}

/**
 * Determines if the levels are safe based on the rules:
 * - Levels are either strictly increasing or strictly decreasing.
 * - Differences between adjacent levels are within the range [1, 3].
 *
 * @param levels A list of integers representing the levels.
 * @return `true` if the levels are safe.
 */
fun isLevelsSafe(levels: List<Int>): Boolean {
    // Check for strictly increasing levels with valid differences
    val isIncreasing = levels.zipWithNext().all { (a, b) -> b > a && (b - a) in 1..3 }

    // Check for strictly decreasing levels with valid differences
    val isDecreasing = levels.zipWithNext().all { (a, b) -> a > b && (a - b) in 1..3 }

    return isIncreasing || isDecreasing
}

/**
 * Checks if a report is safe considering that one level can be removed to make it safe.
 *
 * @param levels A list of integers representing the levels in the report.
 * @return `true` if the report is safe either directly or by removing one level.
 */
fun isSafeReportWithProblemDampener(levels: List<Int>): Boolean {
    // Check if the report is directly safe
    if (isLevelsSafe(levels)) return true

    // Check if removing a single level can make the report safe
    return levels.indices.any { index ->
        val modifiedLevels = levels.filterIndexed { i, _ -> i != index }
        isLevelsSafe(modifiedLevels)
    }
}