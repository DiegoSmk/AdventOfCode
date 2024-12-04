package days.c02

import utils.measureExecutionTime
import utils.readInput

fun main() {
    val input = readInput("inputC2")

    // Measure the execution time of the process
    measureExecutionTime({
        val countSafeReports = countSafeReports(input)

        println("The number of safe reports is $countSafeReports")
    })
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
fun countSafeReports(reports: List<String>): Int {
    var countSafe = 0 // Initialize the count of safe reports

    for (report in reports) {
        // Parse the report into a list of integers
        val levels = report.split(" ").map { it.toInt() }

        // Assume initially that the report is both increasing and decreasing
        var isIncreasing = true
        var isDecreasing = true

        // Iterate through pairs of consecutive levels
        for (i in 0..<levels.size -1) {
            val diff =  levels[i+1] - levels[i] // Difference between adjacent levels

            // If the difference is out of the acceptable range, mark both as false
            if (diff !in 1..3 && diff !in -3..-1) {
                isIncreasing = false
                isDecreasing = false
            } else {
                // If the difference is negative, it's not increasing
                if (diff < 0) isIncreasing = false
                // If the difference is positive, it's not decreasing
                if (diff > 0) isDecreasing = false
            }
        }

        // Increment the count if the report is safe
        if (isIncreasing || isDecreasing) {
            countSafe++
        }
    }

    return countSafe
}