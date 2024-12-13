package days.c09

import utils.measureExecutionTime
import utils.printLine
import utils.printTestResult
import utils.readInputAsString

fun main() {
    val inputTest = readInputAsString("inputC9e")
    val input = readInputAsString("inputC9")

    measureExecutionTime({
        val resultTest = compactAndCalculateChecksum(inputTest)
        println("(Part One - Test)")
        println("The compacting process is complete, and the resulting filesystem checksum is $resultTest.")
        printTestResult(resultTest.toInt(), 1928)

    }, "MAIN - PART ONE - Test")
    printLine()

    measureExecutionTime({
        val result = compactAndCalculateChecksum(input)
        println("(Part One)")
        println("The compacting process is complete, and the resulting filesystem checksum is $result.")
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
 * Compacts the disk map by moving all file blocks to the left and calculates the checksum.
 * @param diskMap The input disk map represented as a dense string of file and free space lengths.
 * @return The checksum after compaction.
 */
fun compactAndCalculateChecksum(diskMap: String): Long {
    // Parse the disk map into a list of blocks, where file blocks are represented by IDs, and free space is null.
    val blocks = parseDiskMap(diskMap)

    // Compact the blocks by removing gaps and moving file blocks to the leftmost free spaces.
    val compactedBlocks = compactBlocks(blocks)

    // Calculate and return the checksum based on the compacted blocks.
    return calculateChecksum(compactedBlocks)
}

/**
 * Parses the disk map into a list of file and free space blocks.
 * File blocks are represented by integers (file IDs), and free space blocks are represented as null.
 * @param diskMap The input string containing alternating file and free space lengths.
 * @return A list where file blocks are integers, and free spaces are null.
 */
private fun parseDiskMap(diskMap: String): List<Int?> {
    val blocks = mutableListOf<Int?>()
    var fileId = 0

    // Process the input string in chunks of two characters (file length and free space length).
    diskMap.chunked(2).forEach { chunk ->
        val fileLength = chunk[0].digitToInt()  // First digit represents the file length.

        // Add file blocks with the current file ID.
        repeat(fileLength) { blocks.add(fileId) }
        fileId++

        // If the chunk has a second character, process the free space length.
        if (chunk.length == 2) {
            val freeLength = chunk[1].digitToInt()
            repeat(freeLength) { blocks.add(null) } // Add null for free space blocks.
        }
    }

    return blocks
}

/**
 * Compacts the disk blocks by shifting file blocks to the leftmost free spaces.
 * @param blocks The list of blocks, where file blocks are integers, and free spaces are null.
 * @return A new list where file blocks are compacted, and free spaces are pushed to the end.
 */
private fun compactBlocks(blocks: List<Int?>): List<Int?> {
    // Count the number of null (free space) blocks in the input.
    val countNull = blocks.count { it == null }
    // Create a reversed list of all non-null (file) blocks.
    val reversedNonNulls = blocks.filterNotNull().asReversed()


    val result = blocks.toMutableList()
    var i = 0 // Index for iterating over reversed non-null blocks.

    // Replace free space blocks (null) with file blocks from the reversed list.
    for (j in result.indices) {
        if (result[j] == null && i < reversedNonNulls.size) {
            result[j] = reversedNonNulls[i++]
        }
    }

    // Add null blocks at the end to maintain the original size.
    for (j in blocks.size - countNull..<blocks.size) {
        result[j] = null
    }

    return result
}

/**
 * Calculates the checksum for the given list of blocks.
 * The checksum is the sum of the product of each block's position and its file ID.
 * Free space blocks (null) are ignored in the calculation.
 * @param blocks The list of blocks, where file blocks are integers, and free spaces are null.
 * @return The calculated checksum.
 */
private fun calculateChecksum(blocks: List<Int?>): Long {
    // Iterate through the blocks with their indices and sum the product of index and block ID.
    return blocks.withIndex()
        .filter { it.value != null } // Ignore free space blocks (null).
        .sumOf { it.index.toLong() * it.value!!.toLong() } // Multiply index by file ID and sum the results.
}

