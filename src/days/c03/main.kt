package days.c03

import utils.measureExecutionTime
import utils.readInput

private const val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)"""

fun main () {
    val inputTest = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"
    val input = readInput("inputC3")

    measureExecutionTime({
        println("===================================\nResult test:")
        println(calculateValidMultiplications(inputTest))

    })

    measureExecutionTime({
        println("===================================\nResult of the input:")
        println(calculateValidMultiplications(input.toString()))

    })

}

fun calculateValidMultiplications(memory: String): Int {
    val regex = mulRegex.toRegex().findAll(memory).sumOf { match ->
        val (fist, second) = match.destructured
        fist.toInt() * second.toInt()
    }
    return regex
}
