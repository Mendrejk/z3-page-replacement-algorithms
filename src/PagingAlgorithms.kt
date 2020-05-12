fun firstInFirstOut(references: List<Int>): Int {
    // using a FIFO queue as a frame container seems to be the most straigtforward solution
    // as it's order of elements naturally follows this algorithm's assumptions
    val frameQueue: QueueFixedSize<Int> = QueueFixedSize<Int>(FRAME_COUNT)
    var pageFaultCount: Int = 0

    references.forEach<Int> { reference: Int ->
        if (reference !in frameQueue) {
            if (frameQueue.isFull()) {
                frameQueue.removeFirst()
                pageFaultCount++
            }
            frameQueue.add(reference)
        }
    }
    return pageFaultCount
}

fun optimal(references: List<Int>): Int {
    // again FIFO queue is needed here; it will almost always be used like a normal list
    // but arrival order is necessary in edge cases when none of the pages is used in the 'forseeable future'
    val frameQueue: QueueFixedSize<Int> = QueueFixedSize<Int>(FRAME_COUNT)
    var pageFaultCount: Int = 0

    references.withIndex().forEach { (index: Int, reference: Int) ->
        if (reference !in frameQueue) {
            if (frameQueue.isFull()) {
                // findNextReference will return -1 if next reference is not found
                val nextReferences: List<Int> = frameQueue.toList().map { findNextReference(it, references, index) }
                        .filter { it > -1 }
                // if there are no future references (list is empty), remove the oldest element
                val lastestNextReferenceIndex: Int = nextReferences.withIndex().maxBy { it.value }?.index ?: 0
                frameQueue.remove(lastestNextReferenceIndex)
                pageFaultCount++
            }
            frameQueue.add(reference)
        }
    }
    return pageFaultCount
}

private fun findNextReference(reference: Int, references: List<Int>, index: Int): Int =
        when {
            index + OPTIMAL_FUTURE_VISIBILITY < references.size ->
                references.slice((index + 1)..(index + OPTIMAL_FUTURE_VISIBILITY)).indexOf(reference)
            index + 1 < references.lastIndex -> references.slice((index + 1)..(references.lastIndex)).indexOf(reference)
            references.last() == reference -> references.last()
            else -> -1
        }