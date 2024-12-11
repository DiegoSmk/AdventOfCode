package days.c07

import utils.measureExecutionTime
import utils.printLine
import utils.printTestResult
import utils.readInput
import kotlin.math.pow

fun main() {
    val inputTest = readInput("inputC7e")
    val input = readInput("inputC7")

    measureExecutionTime({
        val equation = parseEquations(inputTest)
        val resultTest = solveBridgeRepair(equation)
        printTestResult(resultTest.toInt(), 3749)

        println("Total calibration result (Part One - Test): $resultTest")
    }, "MAIN - PART ONE - Test")
    printLine()

    measureExecutionTime({
        val equation = parseEquations(input)
        val result = solveBridgeRepair(equation)
        println("Total calibration result (Part One): $result")
    }, "MAIN - PART ONE")
    printLine()

    measureExecutionTime({
        val equation = parseEquations(inputTest)
        val resultTest = solveBridgeRepair(equation, true)
        printTestResult(resultTest.toInt(), 11387)

        println("Total calibration result (Part Two - Test): $resultTest")
    }, "MAIN - PART TWO - Test")
    printLine()

    measureExecutionTime({
        val equation = parseEquations(input)
        val result = solveBridgeRepair(equation, true)
        println("Total calibration result (Part Two): $result")
    }, "MAIN - PART TWO")
    printLine()
}

/**
 * Represents a single equation with a target value and a list of numbers.
 *
 * @param target The target value to match by inserting operators.
 * @param numbers The list of numbers in the equation.
 */
data class Equation(val target: Long, val numbers: List<Long>)

/**
 * Parses the input into a list of equations.
 *
 * @param input A list of strings, where each string represents an equation in the format "target: numbers".
 * @return A list of parsed equations.
 */
fun parseEquations(input: List<String>): List<Equation> {
    return input.map { line ->
        // Split each line into target and numbers parts
        val (target, numbers) = line.split(":").map { it.trim() }
        // Convert target to Long and numbers to List<Int>
        Equation(target.toLong(), numbers.split(" ").map { it.toLong() })
    }
}

/**
 * Evaluates a given expression left-to-right for a specific operator combination.
 *
 * @param numbers The list of numbers in the expression.
 * @param operatorCombination Encoded operators (`+`, `*`, `||`) represented as a base-N integer.
 * @param operatorBase The base for operator encoding: 2 for `+` and `*`, 3 for `+`, `*`, and `||`.
 * @return The result of evaluating the expression.
 */
fun evaluateExpression(numbers: List<Long>, operatorCombination: Int, operatorBase: Int): Long {
    var result = numbers[0]

    for (i in 1..<numbers.size) {
        val operator = (operatorCombination / operatorBase.toDouble().pow(i - 1).toInt()) % operatorBase

        result = when (operator) {
            0 -> result + numbers[i]
            1 -> result * numbers[i]
            2 -> (result.toString() + numbers[i].toString()).toLong() // Concatenation
            else -> error("Invalid operator: $operator")
        }
    }
    return result
}

/**
 * Checks if the given equation is valid by testing all possible operator combinations.
 *
 * @param equation The equation to validate.
 * @param allowConcatenation Whether concatenation (`||`) is allowed.
 * @return True if the equation can be made valid, false otherwise.
 */
fun isValidEquation(equation: Equation, allowConcatenation: Boolean = false): Boolean {
    val numberOfOperators = equation.numbers.size - 1
    val operatorBase = if (allowConcatenation) 3 else 2
    val maxOperatorCombinations = operatorBase.toDouble().pow(numberOfOperators).toInt()

    // Test all combinations of operators
    for (operatorCombination in 0..<maxOperatorCombinations) {
        if (evaluateExpression(equation.numbers, operatorCombination, operatorBase) == equation.target) {
            return true
        }
    }
    return false
}

/**
 * Solves the bridge repair problem by summing the valid equation targets.
 *
 * @param equations A list of equations to process.
 * @param allowConcatenation Whether concatenation (`||`) is allowed.
 * @return The total sum of target values for valid equations.
 */
fun solveBridgeRepair(equations: List<Equation>, allowConcatenation: Boolean = false): Long {
    return equations.asSequence()
        .filter { isValidEquation(it, allowConcatenation) } // Keep only valid equations
        .sumOf { it.target }            // Sum their target values
}