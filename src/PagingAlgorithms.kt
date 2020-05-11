fun FirstInFirstOut(references: List<Int>): Int {
    // using a FIFO queue as a frame container seems to be the most straigtforward solution
    // as it's order of elements naturally follows this algorithm's assumptions
    val referenceQueue: QueueFixedSize<Int> = QueueFixedSize<Int>(FRAME_COUNT)
    var pageFaultCount: Int = 0

    references.forEach<Int> { reference: Int ->
        if (reference !in referenceQueue) {
            if (referenceQueue.size() == referenceQueue.capacity) {
                referenceQueue.removeFirst()
                pageFaultCount++
            }
            referenceQueue.add(reference)
        }
    }
    return pageFaultCount
}