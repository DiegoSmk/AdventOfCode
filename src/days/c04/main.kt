package days.c04

import utils.measureExecutionTime
import utils.printTestResult
import utils.readInput

fun main() {
    val inputTest = readInput("inputC4e")
    val input = readInput("inputC4")
    val resultTest = 18

    measureExecutionTime({
        val result = findXmas(inputTest)
        val answer = findXmas(input)
        printTestResult(result, 18)
        println("Total occurrences of 'XMAS': $answer")
    }, "MAIN")
    println("==============================================")

    measureExecutionTime({
        val word = "XMAS"
        val occurrences = countWordOccurrences(inputTest, word)
        val answer = countWordOccurrences(input, word)
        printTestResult(occurrences, 18)
        println("Total occurrences of 'XMAS': $answer")
    }, "ChatGPT")
    println("==============================================")


    measureExecutionTime({
        val resultDace = day3Part1Dace(inputTest)
        val answer = day3Part1Dace(input)

        printTestResult(resultDace, 18)
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

        val resultSebastian = day3part1Sebi(grid1)
        val answer = day3part1Sebi(grid2)

        printTestResult(resultSebastian, 18)
        println("Total occurrences of 'XMAS': $answer")
    }, "Sebastian")
    println("==============================================")

}

fun findXmas(grid: List<String>): Int {
    val rows = grid.size
    val cols = grid[0].length
    val word = "XMAS"

    fun isValid(row: Int, col: Int): Boolean = row in 0..<rows && col in 0..<cols

    fun check(row: Int, col: Int, dr: Int, dc: Int): Boolean {
        var r = row
        var c = col
        for (i in word.indices) {
            if (!isValid(r, c) || grid[r][c] != word[i]) return false
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

// ChatGPT
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

// Dace Leeds
fun day3Part1Dace(input: List<String>): Int {

    val leftToRight = input
    val rightToLeft = input.map {it.reversed()}
    val topToBotton = input.pivot()
    val bottonToTop = topToBotton.map {it.reversed()}

    val bottonLeftToTopRight = input.tippedRight()
    val topRightToBottonLeft = bottonLeftToTopRight.map { it.reversed() }
    val topLeftToBottonRight = input.tippedRight()
    val bottonRightToTopLeft = topLeftToBottonRight.map { it.reversed() }

    val all = leftToRight + rightToLeft + topToBotton + bottonToTop + bottonLeftToTopRight + topRightToBottonLeft + topLeftToBottonRight + bottonRightToTopLeft

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

// Sebastian (@sebi.io)
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
        return allowedDirections.count() { directions ->
            checkXmasWordForDirection(startX, startY, directions)
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
}



fun day3part1Sebi(grid: Grid): Int {
    var xmasWordCount = 0

    for ((startX, startY) in grid.indices) {
        xmasWordCount += grid.countXmasWordsForPosition(startX, startY)
    }

    return xmasWordCount
}