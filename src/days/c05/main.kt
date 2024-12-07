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

    }, "MAIN - PART ONE - Test")
    printLine()

    measureExecutionTime({
        val result = calculateMiddleSum(updates, rules)
        println("Sum of the middle pages: $result")

    }, "MAIN - PART ONE")
    printLine()

    measureExecutionTime({
        val resultTest = calculateMiddleSumOfTheCorrected(updatesTest, rulesTest)
        println("Sum of middle pages for corrected updates: $resultTest")
        printTestResult(resultTest, 123)

    }, "MAIN - PART TWO - Test")
    printLine()

    measureExecutionTime({
        val result = calculateMiddleSumOfTheCorrected(updates, rules)

        println("Sum of middle pages for corrected updates: $result")
    }, "MAIN - PART TWO")
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
 * Calculates the sum of the middle page numbers from the corrected updates.
 * Only processes updates that are not already valid and reorders them before summing the middle pages.
 *
 * @param updates The list of updates to process.
 * @param rules The list of rules defining the order constraints between pages.
 * @return The sum of the middle page numbers from the corrected updates.
 */
fun calculateMiddleSumOfTheCorrected(updates: List<Update>, rules: List<Rule>): Int {
    return updates
        .filterNot { isValidUpdate(it, rules) } // Select updates that are not valid
        .map { reorderUpdate(it, rules) }  // Correct their order using reorder logic
        .sumOf { middlePage(it) } // Sum the middle page of each corrected update
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

/**
 * Reorders the pages in an update according to the provided rules.
 * Uses topological sorting to ensure that all order constraints are satisfied.
 *
 * @param update The update to reorder.
 * @param rules The rules defining the order constraints between pages.
 * @return A new Update object with pages reordered to meet the constraints.
 */
fun reorderUpdate(update: Update, rules: List<Rule>): Update {
    val pageSet = update.pages.toSet() // Convert pages to a set for quick lookup

    // Build a dependency map where each page points to its dependencies
    val dependencyMap = mutableMapOf<Int, MutableList<Int>>()
    for (rule in rules) {
        if (rule.x in pageSet && rule.y in pageSet) {
            dependencyMap.computeIfAbsent(rule.y) { mutableListOf() }.add(rule.x)
        }
    }

    // Sort the pages topologically according to the dependency map
    val sortedPages = topologicalSort(update.pages, dependencyMap)
    return Update(sortedPages)
}


/**
 * Performs a topological sort on the given pages based on their dependencies.
 * Ensures that pages are ordered such that all constraints are respected.
 *
 * @param pages The list of pages to sort.
 * @param dependencyMap A map where each page points to a list of its dependencies.
 * @return A list of pages sorted to satisfy all constraints.
 */
fun topologicalSort(pages: List<Int>, dependencyMap: Map<Int, List<Int>>): List<Int> {
    val visited = mutableSetOf<Int>() // Tracks visited pages
    val result = mutableListOf<Int>()

    /**
     * Depth-First Search (DFS) helper function.
     * Recursively visits dependencies of the given page and adds it to the result list.
     *
     * @param page The page to process.
     */
    fun dfs(page: Int) {
        if (page in visited) return // Skip pages already visited
        visited.add(page) // Mark the page as visited
        dependencyMap[page]?.forEach { dfs(it) }
        result.add(page) // Add after visiting all dependencies
    }

    // Perform DFS for each page
    pages.forEach { dfs(it) }
    return result
}
