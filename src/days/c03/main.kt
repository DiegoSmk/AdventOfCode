package days.c03

import utils.measureExecutionTime
import utils.readInput
import utils.readInputAsString

// Regex patterns
private const val MUL_REGEX = """mul\((\d{1,3}),(\d{1,3})\)"""

// PART 2
private const val DO_REGEX = """do\(\)"""
private const val DONT_REGEX = """don't\(\)"""

fun main () {
    val inputTest = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    val input = readInputAsString("inputC3")
    val inputWithList = readInput("inputC3")

    println(MUL_REGEX.toRegex().matches("xmul(2,4)"))

    println("================= PART ONE =================")

    // Measure execution time for part one
    measureExecutionTime({
        println("===================================\nResult test:")
        println(calculateValidMultiplications(inputTest))

    })

    measureExecutionTime({
        println("===================================\nResult of the input:")
        println(calculateValidMultiplications(input))

    })

    println("================= PART TWO =================")

    // Measure execution time for part two
    measureExecutionTime({
        println("===================================\nResult of the input:")
        println(calculateConditionalMultiplications(input))
    })

    measureExecutionTime({
        println("===================================\nResult of the input:")
        println(addValidMultiplicationsP2(input))
    }, "Nicole Tercs")

    measureExecutionTime({
        println("===================================\nResult of the input:")
        println(part2(inputWithList))
    }, "Sebastian")
}

/**
 * Calculates the sum of all valid multiplications from a memory string.
 * Valid instructions follow the pattern `mul(X,Y)`.
 *
 * @param memory The corrupted memory as a string.
 * @return The sum of all valid multiplications.
 */
fun calculateValidMultiplications(memory: String): Int {
    val regex = MUL_REGEX.toRegex().findAll(memory).sumOf { match ->
        val (fist, second) = match.destructured
        fist.toInt() * second.toInt()
    }
    return regex
}

/**
 * Calculates the sum of all valid multiplications considering enabling and disabling conditions.
 * Multiplications are only considered if the most recent state is `do()`.
 *
 * @param memory The corrupted memory as a string.
 * @return The sum of all enabled multiplications.
 */
fun calculateConditionalMultiplications(memory: String): Int {
    var isEnabled = true // Tracks if `mul` instructions are currently enabled
    var total = 0

    // Combine all relevant regex patterns into one to efficiently find all matching tokens
    val regex = """$MUL_REGEX|$DO_REGEX|$DONT_REGEX""".toRegex()

    // Find all matches in the input memory string
    val matches = regex.findAll(memory)

    matches.forEach { match  ->
        val token = match.value // Extract the current matched token

        when {
            DO_REGEX.toRegex().matches(token) -> isEnabled = true
            DONT_REGEX.toRegex().matches(token) -> isEnabled = false
            MUL_REGEX.toRegex().matches(token) && isEnabled -> {
                val (first, second) = MUL_REGEX.toRegex().find(token)!!.destructured
                total += first.toInt() * second.toInt()
            }
        }
    }

    return total
}

// Nicole Tercs (@nicole-terc)
    fun addValidMultiplicationsP2(memory: String): Long {
    var sum = 0L
    var enabled = true

    """$MUL_REGEX|$DO_REGEX|$DONT_REGEX""".toRegex().findAll(memory).forEach { match ->
        when (match.value) {
            "don't()" -> enabled = false
            "do()" -> enabled = true
            else -> if (enabled) sum += match.multiplyNumber()
        }
    }
    return sum
}

private fun MatchResult.multiplyNumber(): Long {
    val (first, second) = destructured
    return first.toLong() * second.toLong()
}

//Sebastian (@sebi.io)
fun part2(lines: List<String>): Int {
    val mulRegex = """mul\(\d{1,3},\d{1,3}\)|do(n't)?\(\)""".toRegex()
    val all = lines.flatMap { string ->
        mulRegex.findAll(string).map {it.value}
    }

    var enabled = true
    var acc = 0

    for (instruction in all) {
        when {
            instruction == "do()" -> enabled = true
            instruction == "don't()" -> enabled = false
            enabled && instruction.startsWith("mul(") -> {
                val (a, b) = instruction
                    .removeSurrounding("mul(", ")")
                    .split(",")
                acc += a.toInt() * b.toInt()
            }
        }
    }

    return acc
}
