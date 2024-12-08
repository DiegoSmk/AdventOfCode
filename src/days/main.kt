package days

import utils.measureExecutionTime
import utils.printLine
import utils.printTestResult

// TEMPLATE
fun main() {
    measureExecutionTime({
        val resultTest = 0
        printTestResult(resultTest, 0)

    }, "MAIN - PART ONE - Test")
    printLine()

    measureExecutionTime({}, "MAIN - PART ONE")
    printLine()

    measureExecutionTime({
        val resultTest = 0
        printTestResult(resultTest, 0)
    }, "MAIN - PART TWO - Test")
    printLine()

    measureExecutionTime({}, "MAIN - PART TWO")
    printLine()
}