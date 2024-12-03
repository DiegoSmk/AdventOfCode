package utils

import java.io.File

fun readInput(name: String): List<String> {
    return File("src/inputs/$name").readLines()
}