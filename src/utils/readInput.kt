package utils

import kotlin.io.path.Path
import kotlin.io.path.readText

fun readInput(name: String): List<String> = Path("src/inputs/$name").readText().trim().lines()

fun readInputAsString(name: String): String = Path("src/inputs/$name").readText().trim()