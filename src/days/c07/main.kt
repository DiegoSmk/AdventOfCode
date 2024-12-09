package days.c07

import utils.measureExecutionTime
import utils.printLine
import utils.printTestResult
import utils.readInput

fun main() {
    val inputTest = readInput("inputC7e")
    val input = readInput("inputC7")

    measureExecutionTime({
        val equation = parseEquations(inputTest)
        val resultTest = solveBridgeRepair(equation)
        printTestResult(resultTest.toInt(), 3749)

        println("Total calibration result: $resultTest")
    }, "MAIN - PART ONE - Test")
    printLine()

    measureExecutionTime({
        val equation = parseEquations(input)
        val result = solveBridgeRepair(equation)
        println(result)
        println("Total calibration result: $result")
    }, "MAIN - PART ONE")
    printLine()

    measureExecutionTime({
        val resultTest = 0
        printTestResult(resultTest, 0)
    }, "MAIN - PART TWO - Test")
    printLine()

    measureExecutionTime({}, "MAIN - PART TWO")
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
 * @param operators The list of operators (`+` or `*`) to apply between numbers.
 * @return The result of evaluating the expression.
 */
fun evaluateExpression(numbers: List<Long>, operators: Int): Long {
    var result = numbers[0]

    // Iterate over the numbers and apply operators according to the bitmask
    for (i in 1..<numbers.size) {
        val operator = (operators shr (i - 1)) and 1
        result = if (operator == 0) {
            result + numbers[i]
        } else {
            result * numbers[i]
        }
    }
    return result
}

/**
 * Checks if the given equation is valid by testing all possible operator combinations.
 *
 * @param equation The equation to validate.
 * @return True if the equation can be made valid, false otherwise.
 */
fun isValidEquation(equation: Equation): Boolean {
    val n = equation.numbers.size
    val maxCombinations = 1 shl (n - 1) // Number of possible combinations of operators

    // Test all combinations of operators
    for (operators in 0..<maxCombinations) {
        if (evaluateExpression(equation.numbers, operators) == equation.target) {
            return true
        }
    }
    return false
}


/**
 * Solves the bridge repair problem by summing the valid equation targets.
 *
 * @param equations A list of equations to process.
 * @return The total sum of target values for valid equations.
 */
fun solveBridgeRepair(equations: List<Equation>): Long {
    return equations.asSequence()
        .filter { isValidEquation(it) } // Keep only valid equations
        .sumOf { it.target }            // Sum their target values
}