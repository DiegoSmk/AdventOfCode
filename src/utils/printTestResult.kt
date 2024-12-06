package utils

fun printTestResult(yourAnswer: Int, correctAnswer: Int) {
    println("Test ${if (yourAnswer == correctAnswer) "passed" else "failed"}")
}