package days.c01

import utils.readInput
import kotlin.math.abs
import kotlin.time.measureTime

fun main () {
    // Measure code in milleSeconds.
    val startTime = measureTime {
        // ===== PART ONE ======
        val input = readInput("inputC1")
        val organize = organizeList(input)
        val calculateDistance = calculateTotalDistance(organize.left, organize.right)
        println("Part One - $calculateDistance")

        // ===== PART TWO ======
        val rightFrequencyMap = countOccurrences(organize.right)
        val score = organize.left.sumOf { num ->
            num * rightFrequencyMap.getOrDefault(num, 0)
        }
        println("Part Two - $score")
    }

    println("Execution time: $startTime")
}

// ===== PART ONE ======

data class ResultListOrganize(
    val left: List<Int>,
    val right: List<Int>
)

fun organizeList (list: List<String>): ResultListOrganize {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()

    list.forEach { line ->
        val (left, right) = line.split("   ")

        leftList.add(left.toInt())
        rightList.add(right.toInt())
    }

    leftList.sort()
    rightList.sort()

    return ResultListOrganize(leftList, rightList)
}

// Function to calculate the total distance.
fun calculateTotalDistance(leftList: List<Int>, rightList: List<Int>): Int {
    var totalDistance = 0

    for (i in leftList.indices) {
        totalDistance += abs(leftList[i] - rightList[i])
    }
    return totalDistance
}

// ===== PART TWO ======

fun countOccurrences(list: List<Int>): Map<Int, Int> {
    return list.groupingBy { it }.eachCount()
}
