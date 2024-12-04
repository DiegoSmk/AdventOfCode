package days.c02

import utils.measureExecutionTime
import utils.readInput

fun main() {
    val input = readInput("inputC2")

    measureExecutionTime({
        val countSafeReports = countSafeReports(input)

        println("The number of safe reports is $countSafeReports")
    })
}

fun countSafeReports(reports: List<String>): Int {
    var countSafe = 0

    for (report in reports) {
        val levels = report.split(" ").map { it.toInt() }

        var isIncreasing = true
        var isDecreasing = true

        for (i in 0..<levels.size -1) {
            val diff =  levels[i+1] - levels[i]

            if (diff !in 1..3 && diff !in -3..-1) {
                isIncreasing = false
                isDecreasing = false
            } else {
                if (diff < 0) isIncreasing = false
                if (diff > 0) isDecreasing = false
            }
        }

        if (isIncreasing || isDecreasing) {
            countSafe++
        }
    }

    return countSafe
}