package days.c05

import utils.measureExecutionTime
import utils.printLine
import utils.printTestResult
import utils.readInput

fun main() {
    val input = readInput("inputC5")
    val inputTest = readInput("inputC5e")

    val rulesTest = parseRules(inputTest)
    val updatesTest = parseUpdates(inputTest)

    val rules = parseRules(input)
    val updates = parseUpdates(input)

    measureExecutionTime({
        val resultTest = calculateMiddleSum(updatesTest, rulesTest)
        println("Sum of the middle pages: $resultTest")
        printTestResult(resultTest, 143)

    }, "MAIN - Test")
    printLine()

    measureExecutionTime({
        val result = calculateMiddleSum(updates, rules)
        println("Sum of the middle pages: $result")

    }, "MAIN")
    printLine()
}

/**
 * Represents a rule where X must be printed before Y.
 */
data class Rule(val x: Int, val y: Int)

/**
 * Represents an update, which is a list of page numbers to be printed.
 */
data class Update(val pages: List<Int>)

/**
 * Determines if a given update respects all the ordering rules.
 *
 * @param update The update containing the list of pages to validate.
 * @param rules The list of rules specifying the ordering constraints.
 * @return True if the update respects all the rules, false otherwise.
 */
fun isValidUpdate(update: Update, rules: List<Rule>): Boolean {
    for (rule in rules) {
        val xIndex = update.pages.indexOf(rule.x)
        val yIndex = update.pages.indexOf(rule.y)

        // Skip rules where either page is not in the update
        if (xIndex == -1 || yIndex == -1) continue

        // If pageX appears after pageY, the update is invalid
        if (xIndex > yIndex) return false
    }
    return true
}

/**
 * Retrieves the middle page number of an update.
 *
 * @param update The update containing the list of pages.
 * @return The page number in the middle of the sorted update.
 */
fun middlePage(update: Update): Int {
    return update.pages[update.pages.size/2]
}

/**
 * Calculates the sum of the middle pages for all updates that are valid according to the rules.
 *
 * @param updates The list of updates to process.
 * @param rules The list of ordering rules to validate against.
 * @return The sum of the middle page numbers from all valid updates.
 */
fun calculateMiddleSum(updates: List<Update>, rules: List<Rule>): Int {
    return updates
        .filter { isValidUpdate(it, rules) } // Only process valid updates
        .sumOf { middlePage(it) } // Sum the middle pages
}

/**
 * Parses a list of input strings into a list of ordering rules.
 *
 * @param input The input strings, each containing a rule in the format "X|Y".
 * @return A list of Rule objects parsed from the input.
 */
fun parseRules(input: List<String>): List<Rule> {
    return input.takeWhile { it.contains("|") }
        .map { line ->
            val (x, y) = line.split("|").map { it.toInt() }
            Rule(x, y)
        }
}

/**
 * Parses a list of input strings into a list of updates.
 *
 * @param input The input strings, each containing a list of pages separated by commas.
 * @return A list of Update objects parsed from the input.
 */
fun parseUpdates(input: List<String>): List<Update> {
    return input.dropWhile { it.contains("|") } // Skip rules section
        .filter { it.isNotBlank() } // Ignore blank lines
        .map { line ->
            val pages = line.split(",").map { it.toInt() }
            Update(pages)
        }
}
