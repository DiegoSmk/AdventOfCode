package utils

import kotlin.system.measureTimeMillis

/**
 * Measures the execution time of a given block of code and prints the result.
 *
 * @param action The block of code whose execution time is to be measured.
 * @param label A label that can be used to distinguish between multiple measurements.
 *               Default value is "MAIN", but it can be customized for different parts of the program.
 */
fun measureExecutionTime(action: () -> Unit, label: String = "MAIN") {
    // Record the time taken to execute the action
    val time = measureTimeMillis(action)

    // Print the execution time with a label for easy identification
    println("$label - Execution time: $time ms")
}
