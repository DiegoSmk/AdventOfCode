package days.c04

import utils.measureExecutionTime
import utils.printTestResult
import utils.readInput

fun main() {
    val inputTest = readInput("inputC4e")
    val input = readInput("inputC4")
    val resultTest = 18
    val resultTestTwo = 9

    measureExecutionTime({
        val result = findXmas(inputTest)
        val answer = findXmas(input)
        printTestResult(result, resultTest)
        println("Total occurrences of 'XMAS': $answer")
    }, "MAIN - PART 1")
    println("==============================================")

    printTestResult(countXMasOccurrences(inputTest), resultTestTwo)
    measureExecutionTime({
        val resultNewTest = countXMasOccurrences(input)
        println("Total occurrences of 'XMAS': $resultNewTest")
    }, "MAIN - PART 2")
    println("==============================================")

    measureExecutionTime({
        val word = "XMAS"
        val occurrences = countWordOccurrences(inputTest, word)
        val answer = countWordOccurrences(input, word)
        printTestResult(occurrences, resultTest)
        println("Total occurrences of 'XMAS': $answer")
    }, "ChatGPT")
    println("==============================================")

    measureExecutionTime({
        val resultDace = day4Part1Dace(inputTest)
        val answer = day4Part1Dace(input)

        printTestResult(resultDace, resultTest)
        println("Total occurrences of 'XMAS': $answer")
    }, "Dace Leeds")
    println("==============================================")

    measureExecutionTime({
        val grid1 = Grid(inputTest.map {
            it.toCharArray().toList()
        })
        val grid2 = Grid(input.map {
            it.toCharArray().toList()
        })

        val resultSebastian = day4part1Sebi(grid1)
        val answerOne = day4part1Sebi(grid2)
        val answerTwo = day4Part2Sebi(grid2)

        printTestResult(resultSebastian, resultTest)
        println("Total occurrences of 'XMAS': $answerOne")
        println("Total occurrences of 'X-MAS': $answerTwo")
    }, "Sebastian")
    println("==============================================")
}
// PART ONE ============================================================================================================
/**
 * Checks if the given coordinates are within the bounds of the grid.
 *
 * @param row The row index to check.
 * @param col The column index to check.
 * @param rows The total number of rows in the grid.
 * @param cols The total number of columns in the grid.
 * @return True if the coordinates are valid, false otherwise.
 */
fun isValid(row: Int, col: Int, rows: Int, cols: Int): Boolean = row in 0..<rows && col in 0..<cols

/**
 * Finds the total occurrences of the word "XMAS" in the grid.
 * The word can appear horizontally, vertically, diagonally,
 * and even backwards in any of these directions.
 *
 * @param grid The grid of characters where the word will be searched.
 * @return The total number of occurrences of "XMAS".
 */
fun findXmas(grid: List<String>): Int {
    val rows = grid.size
    val cols = grid[0].length
    val word = "XMAS"

    /**
     * Checks if the word "XMAS" exists starting from a specific position
     * in the given direction.
     *
     * @param row The starting row index.
     * @param col The starting column index.
     * @param dr The row direction increment.
     * @param dc The column direction increment.
     * @return True if the word is found, false otherwise.
     */
    fun check(row: Int, col: Int, dr: Int, dc: Int): Boolean {
        var r = row
        var c = col
        for (i in word.indices) {
            if (!isValid(r, c, rows, cols) || grid[r][c] != word[i]) return false
            r += dr
            c += dc
        }
        return true
    }

    var count = 0

    // all possible directions.
    val directions = listOf(
        Pair(0, 1),
        Pair(1, 0),
        Pair(1, 1),
        Pair(1, -1),
        Pair(0, -1),
        Pair(-1, 0),
        Pair(-1, -1),
        Pair(-1, 1)
    )

    // Iterate through all grid cells.
    for (row in 0..<rows) {
        for (col in 0..<cols) {
            for ((dr, dc) in directions) {
                if (check(row, col, dr, dc)) {
                    count++
                }
            }
        }
    }

    return count
}

// PART TWO ============================================================================================================
/**
 * Class responsible for storing and managing all possible X-MAS patterns.
 */
class XmasPatterns {

    // List of valid X-MAS patterns represented as concatenated strings.
    private val patterns: List<String> = listOf(
        "MSAMS", // Standard X-MAS pattern
        "SMASM", // Inverted X-MAS pattern
        "MMASS", // Mixed diagonal pattern
        "SSAMM"  // Mixed diagonal inverted pattern
    )

    /**
     * Concatenates a 3x3 sub-matrix into a string for pattern matching.
     * The resulting string is created using the 5 key characters:
     * - First and last character of the first row
     * - Middle character of the middle row
     * - First and last character of the last row
     *
     * @param subMatrix The 3x3 sub-matrix as a list of strings.
     * @return A concatenated string representation of the X-MAS shape.
     */
    private fun concatenate(subMatrix: List<String>): String {
        val (firstRow, middleRow, lastRow) = subMatrix
        return StringBuilder()
            .append(firstRow.first())
            .append(firstRow.last())
            .append(middleRow[1])
            .append(lastRow.first())
            .append(lastRow.last())
            .toString()
    }

    /**
     * Checks if a given 3x3 sub-matrix matches any valid X-MAS pattern.
     *
     * @param subMatrix The 3x3 sub-matrix to validate.
     * @return True if it matches any pattern, false otherwise.
     */
    fun matchesAnyPattern(subMatrix: List<String>): Boolean {
        val concatString = concatenate(subMatrix)
        return patterns.any { pattern -> concatString == pattern }
    }
}

/**
 * Counts the total number of X-MAS patterns found in the given matrix.
 *
 * @param matrix The input matrix of characters as a list of strings.
 * @return The total number of X-MAS patterns found.
 */
fun countXMasOccurrences(matrix: List<String>): Int {
    var count = 0
    val rows = matrix.size
    val cols = matrix[0].length
    val xmasPatterns = XmasPatterns()

    // Iterate through all possible 3x3 sub-matrices
    for (i in 0..<rows - 2) {
        for (j in 0..<cols - 2) {
            // Extract the 3x3 sub-matrix
            val subMatrix = List(3) { row -> matrix[i + row].substring(j, j + 3) }

            // Check if the sub-matrix matches any X-MAS pattern
            if (xmasPatterns.matchesAnyPattern(subMatrix)) {
                count++
            }
        }
    }
    return count
}

// ChatGPT ==========================================================================
/**
 * Counts all occurrences of a word in the grid, considering all possible directions.
 */
fun countWordOccurrences(grid: List<String>, word: String): Int {
    val rows = grid.size
    val cols = grid[0].length
    val directions = listOf(
        Pair(0, 1),  // Direction: left to right
        Pair(0, -1), // Direction: right to left
        Pair(1, 0),  // Direction: top to bottom
        Pair(-1, 0), // Direction: bottom to top
        Pair(1, 1),  // Diagonal: top-left to bottom-right
        Pair(-1, -1), // Diagonal: bottom-right to top-left
        Pair(1, -1), // Diagonal: top-right to bottom-left
        Pair(-1, 1)  // Diagonal: bottom-left to top-right
    )

    var totalOccurrences = 0

    for (row in 0..<rows) {
        for (col in 0..<cols) {
            // Check all possible directions from each cell
            directions.forEach { direction ->
                if (findWord(grid, word, row, col, direction)) {
                    totalOccurrences++
                }
            }
        }
    }

    return totalOccurrences
}

/**
 * Checks if the word can be found in a specific direction starting from a given cell.
 */
fun findWord(grid: List<String>, word: String, startRow: Int, startCol: Int, direction: Pair<Int, Int>): Boolean {
    val rows = grid.size
    val cols = grid[0].length
    var row = startRow
    var col = startCol

    for (char in word) {
        // Verify if the cell is within the grid boundaries
        if (row !in 0..<rows || col !in 0..<cols || grid[row][col] != char) {
            return false
        }
        // Move to the next cell in the current direction
        row += direction.first
        col += direction.second
    }

    return true
}

// Dace Leeds ==========================================================================
fun day4Part1Dace(input: List<String>): Int {

    val rightToLeft = input.map {it.reversed()}
    val topToBotton = input.pivot()
    val bottonToTop = topToBotton.map {it.reversed()}

    val bottonLeftToTopRight = input.tippedRight()
    val topRightToBottonLeft = bottonLeftToTopRight.map { it.reversed() }
    val topLeftToBottonRight = input.tippedLeft()
    val bottonRightToTopLeft = topLeftToBottonRight.map { it.reversed() }

    val all = input + rightToLeft + topToBotton + bottonToTop + bottonLeftToTopRight + topRightToBottonLeft + topLeftToBottonRight + bottonRightToTopLeft

    return all.sumOf { Regex("XMAS").findAll(it).count() }
}

fun List<String>.pivot(): List<String> {
    val characters = this
    return buildList {
        for (x in characters[0].indices) {
            add(buildString {
                for (y in characters.indices) {
                    append(characters[y][x])
                }
            })
        }
    }
}

fun List<String>.tippedRight(): List<String> {
    val characters  = this
    val width = characters.first().length
    val height = characters.size
    return buildList {
        for (y in characters.indices) {
            add(buildString {
                var cx = 0
                var cy = y
                do {
                    append(characters[cy][cx])
                    cx += 1
                    cy -= 1
                } while (cx < width && cy >= 0)
            })
        }
        for (x in characters[0].indices.drop(1)) {
            add(buildString {
                var cx = x
                var cy = height - 1
                do {
                    append(characters[cy][cx])
                    cx += 1
                    cy -= 1
                } while (cx < width && cy >= 0)
            })
        }
    }
}

fun List<String>.tippedLeft(): List<String> {
    val characters = this
    val width = characters.first().length
    val height = characters.size
    return buildList {
        for (x in characters[0].indices.reversed()) {
            add(buildString {
                var cx = x
                var cy = 0
                do {
                    append(characters[cy][cx])
                    cx += 1
                    cy += 1
                } while (cx < width && cy < height)
            })
        }
        for (y in characters.indices.drop(1)) {
            add(buildString {
                var cx = 0
                var cy = y
                do {
                    append(characters[cy][cx])
                    cx += 1
                    cy += 1
                } while (cx < width && cy < height)
            })
        }
    }
}

// Sebastian (@sebi.io) ==========================================================================
data class Vec2(val x: Int, val y: Int)

data class Grid(private val elems: List<List<Char>>) {
    private val allowedDirections = listOf(
        Vec2(1,0), //east
        Vec2(1,1), //southeast
        Vec2(0,1), //south
        Vec2(-1,1), //southwest
        Vec2(-1,0), //west
        Vec2(-1,-1), //northwest
        Vec2(0,-1), //north
        Vec2(1,-1) // northeast
    )

    val indices = sequence {
        for (y in elems[0].indices) {
            for (x in elems.indices) {
                yield(Pair(x,y))
            }
        }
    }

    private fun getAtPos(x: Int, y: Int): Char? = elems.getOrNull(y)?.getOrNull(x)

    fun countXmasWordsForPosition(startX: Int, startY: Int): Int {
        return allowedDirections.count { direction ->
            checkXmasWordForDirection(startX, startY, direction)
        }
    }

    private fun checkXmasWordForDirection(startX: Int, startY: Int, direction: Vec2): Boolean {
        var runningX = startX
        var runningY = startY

        for (letter in listOf('X', 'M', 'A', 'S')) {
            if (getAtPos(runningX, runningY) != letter) {
                return false
            }
            runningX += direction.x
            runningY += direction.y
        }
        return true
    }

    fun isMASCrossAtPosition(centerX: Int, centerY: Int): Boolean {
        if (getAtPos(centerX, centerY) != 'A') {
            return false // invalid cross-center
        }
        // we start at the 'A' of 'MAS', because it is the center.
        val isFallingDiagonalMAS =
            getAtPos(centerX - 1, centerY - 1) == 'M' && getAtPos(centerX + 1, centerY + 1) == 'S'
        val isFallingDiagonalSAM =
            getAtPos(centerX - 1, centerY - 1) == 'S' && getAtPos(centerX + 1, centerY + 1) == 'M'
        val fallingDiagonalOk = isFallingDiagonalMAS || isFallingDiagonalSAM

        val isRisingDiagonalMAS =
            getAtPos(centerX - 1, centerY + 1) == 'M' && getAtPos(centerX + 1, centerY - 1) == 'S'
        val isRisingDiagonalSAM =
            getAtPos(centerX - 1, centerY + 1) == 'S' && getAtPos(centerX + 1, centerY - 1) == 'M'
        val risingDiagonalOk = isRisingDiagonalMAS || isRisingDiagonalSAM

        return risingDiagonalOk && fallingDiagonalOk
    }
}



fun day4part1Sebi(grid: Grid): Int {
    var xmasWordCount = 0

    for ((startX, startY) in grid.indices) {
        xmasWordCount += grid.countXmasWordsForPosition(startX, startY)
    }

    return xmasWordCount
}

fun day4Part2Sebi(grid: Grid): Int {
    var crossCount = 0
    for ((centerX, centerY) in grid.indices) {
        crossCount += if (grid.isMASCrossAtPosition(centerX, centerY)) 1 else 0
    }

    return crossCount
}