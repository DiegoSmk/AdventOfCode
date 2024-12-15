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
        val resultTest = compactAndCalculateChecksum(inputTest, true)
        println("(Part Two - Test)")
        println("Checksum with new method: $resultTest")
        printTestResult(resultTest.toInt(), 2858)
    }, "MAIN - PART TWO - Test")
    printLine()

    measureExecutionTime({
        val result = compactAndCalculateChecksum(input, true)
        println("(Part Two)")
        println("Checksum with new method: $result")
    }, "MAIN - PART TWO")
    printLine()


    measureExecutionTime({
        val list = mutableListOf<Segment>()
        var cnt = 0
        for ((idx, char) in input.withIndex()) {
            val digit = char.digitToInt()
            val id = if (idx % 2 == 1) -1 else (cnt++)
            list += Segment(digit, id)
        }
//    part1(list)
        part2(list)
    }, "@sebi.io - PART TWO")
    printLine()
}

/**
 * Compacts the disk map by moving file blocks to the left and calculates the checksum.
 * @param diskMap The input disk map represented as a dense string of file and free space lengths.
 * @param useNewMethod If true, uses the new method to compact blocks by moving entire files. Defaults to false.
 * @return The checksum after compaction.
 */
fun compactAndCalculateChecksum(diskMap: String, useNewMethod: Boolean = false): Long {
    // Parse the disk map into a list of blocks, where file blocks are represented by IDs, and free space is null.
    val blocks = parseDiskMap(diskMap)

    // Compact the blocks: use the selected method to rearrange file blocks and free spaces.
    val compactedBlocks = if (useNewMethod) {
        compactBlocksByFile(blocks) // New method: moves entire files to free space.
    } else {
        compactBlocks(blocks) // Original method: compacts by moving blocks individually.
    }


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
 * Compacts a list of blocks using the new method:
 * Moves entire files (groups of contiguous blocks with the same ID) to the leftmost available
 * span of free space (nulls) that can fit the file. Files are moved in descending order of file ID.
 *
 * @param blocks The input list representing the disk's blocks, where file blocks are represented by integers and free spaces by nulls.
 * @return The compacted list of blocks after rearranging according to the specified rules.
 */
private fun compactBlocksByFile(blocks: List<Int?>): List<Int?> {
    // Group contiguous elements into a mutable list of groups.
    val groupedBlocks = groupContiguousBlocks(blocks)

    var filePointer = groupedBlocks.size - 1 // Points to the last group to process.

    var idProgress = groupedBlocks.filter { it.first() != null }.maxOf { it.first()!! } // Starts with the highest file ID.

    // Process files in descending order of file ID.
    outer@ while (filePointer >= 0) {
        val currentFileGroup = groupedBlocks[filePointer]
        val currentFileId = currentFileGroup.first()

        if (currentFileId != null) {
            if (currentFileId > idProgress) {
                filePointer--
                continue@outer
            } else {
                idProgress = currentFileId
            }
        }

        // Find the first free space large enough to fit the current file.
        for (freeSpacePointer in 0..< filePointer) {
            val freeSpaceGroup = groupedBlocks[freeSpacePointer]

            if (freeSpaceGroup.first() == null) {
                if (freeSpaceGroup.size >= currentFileGroup.size) {
                    val leftoverSpace = freeSpaceGroup.size - currentFileGroup.size

                    // Mark the original file position as free space.
                    groupedBlocks[filePointer] = MutableList(currentFileGroup.size) { null }

                    // Move the file into the free space group.
                    groupedBlocks[freeSpacePointer] = currentFileGroup

                    if (leftoverSpace > 0) {
                        val nextSpacePointer = freeSpacePointer + 1
                        if (groupedBlocks[nextSpacePointer][0] == null) {
                            repeat(leftoverSpace) {
                                groupedBlocks[nextSpacePointer].add(null)
                            }
                        } else {
                            groupedBlocks.add(nextSpacePointer, MutableList(leftoverSpace) { null })
                            continue@outer // Restart the process for the next file.
                        }
                    }

                    filePointer--
                    continue@outer // Restart the process for the next file.
                }
            }
        }
        filePointer-- // No valid space found; move to the next file.
    }

    return groupedBlocks.flatten()
}

/**
 * Groups contiguous elements in a list into sublists.
 * Each sublist contains contiguous elements of the same value.
 *
 * @param blocks The input list of blocks.
 * @return A mutable list of grouped elements.
 */
fun groupContiguousBlocks(blocks: List<Int?>): MutableList<MutableList<Int?>> {
    return sequence {
        var currentGroup = mutableListOf(blocks[0])

        for (i in 1..<blocks.size) {
            val current = blocks[i]
            val previous = blocks[i - 1]

            if (current == previous) {
                currentGroup.add(current) // Add to the current group if values match.
            } else {
                yield(currentGroup) // Emit the completed group.
                currentGroup = mutableListOf(current) // Start a new group.
            }
        }
        yield(currentGroup) // Emit the final group.
    }.toMutableList() // Convert the sequence to a mutable list for further processing.
}

private fun calculateChecksum(blocks: List<Int?>): Long {
    var checksum = 0L
    blocks.forEachIndexed { index, value ->
        if (value != null) {
            checksum += (index * value).toLong()
//            println("Index: $index, Value: $value, Partial Checksum: $checksum")
        }
    }
    return checksum
}
