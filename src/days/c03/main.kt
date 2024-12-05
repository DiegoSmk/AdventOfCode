package days.c03

import utils.measureExecutionTime
import utils.readInput
import utils.readInputAsString

private const val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)"""

// PART 2
private const val doRegex = """do\(\)"""
private const val dontRegex = """don't\(\)"""

fun main () {
    val inputTest = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
    val input = readInputAsString("inputC3")
    val inputWithList = readInput("inputC3")

    println(mulRegex.toRegex().matches("xmul(2,4)"))

    println("================= PART ONE =================")

    measureExecutionTime({
        println("===================================\nResult test:")
        println(calculateValidMultiplications(inputTest))

    })

    measureExecutionTime({
        println("===================================\nResult of the input:")
        println(calculateValidMultiplications(input))

    })

    println("================= PART TWO =================")

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

fun calculateValidMultiplications(memory: String): Int {
    val regex = mulRegex.toRegex().findAll(memory).sumOf { match ->
        val (fist, second) = match.destructured
        fist.toInt() * second.toInt()
    }
    return regex
}

fun calculateConditionalMultiplications(memory: String): Int {
    var isEnabled = true
    var total = 0

    val regex = """$mulRegex|$doRegex|$dontRegex""".toRegex()

    val matches = regex.findAll(memory)

    matches.forEach { match  ->
        val token = match.value

        when {
            doRegex.toRegex().matches(token) -> isEnabled = true
            dontRegex.toRegex().matches(token) -> isEnabled = false
            mulRegex.toRegex().matches(token) && isEnabled -> {
                val (first, second) = mulRegex.toRegex().find(token)!!.destructured
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

    """$mulRegex|$doRegex|$dontRegex""".toRegex().findAll(memory).forEach { match ->
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
