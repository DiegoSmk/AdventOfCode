package days.c09
// Sebastian @Sebi.io
data class Segment(val len: Int, val id: Int) // -1 == empty

class BlockStorage() {
    val storage = mutableListOf<Segment>()
    fun addSegment(segment: Segment) {
        storage += segment
    }

    fun firstGapOfMinimumSize(n: Int): Int {
        return storage.indexOfFirst { segment -> segment.id == -1 && segment.len >= n }
    }

    fun moveSegmentIntoGap(segmentIdx: Int, gapIdx: Int) {
        val gap = storage[gapIdx]
        val segment = storage[segmentIdx]
        val newGapLength = gap.len - segment.len
        val newGapSegment = Segment(newGapLength, -1)
        storage[segmentIdx] = Segment(segment.len, -1)
        storage[gapIdx] = segment
        storage.add(gapIdx + 1, newGapSegment)
    }

    private fun materialize(): List<Int> {
        val materialized = mutableListOf<Int>()
        for (segment in storage) {
            repeat(segment.len) {
                materialized.add(segment.id)
            }
        }
        return materialized
    }

    fun checksum(): Long {
        var checkSum = 0L
        for ((idx, block) in materialize().withIndex()) {
            if (block == -1) continue
            checkSum += idx * block
//            println(checkSum)
        }
        return checkSum
    }
}

fun part2(list: List<Segment>) {
//    println("enterping part 2")
    val blockStorage = BlockStorage()
    for (segment in list) {
        blockStorage.addSegment(segment)
    }
    while (true) {
        var didMoveSuccessfully = false
        for ((idx, segment) in blockStorage.storage.withIndex().reversed()) {
            if (segment.id == -1) continue // data segments only
            // find a location to see if it's possible to move
            val potentialTarget = blockStorage.firstGapOfMinimumSize(segment.len)
            if (potentialTarget == -1) continue // can't move this data segment
            if (potentialTarget >= idx) continue // could only move it further to the end
            blockStorage.moveSegmentIntoGap(idx, potentialTarget) // this should always succeed
            didMoveSuccessfully = true
//            println("successfully moved $segment from $idx to $potentialTarget")
            break
        }
        if (!didMoveSuccessfully) break
    }


    val materialized = mutableListOf<Int>()
    for (segment in blockStorage.storage) {
        repeat(segment.len) {
            materialized.add(segment.id)
        }
    }
//    println(materialized)
    println(blockStorage.checksum())
}
