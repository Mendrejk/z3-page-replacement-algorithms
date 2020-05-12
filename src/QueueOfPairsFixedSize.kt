class QueueOfPairsFixedSize<A, B>(capacity: Int): QueueFixedSize<Pair<A, B>>(capacity) {
    fun containsByFirst(element: A): Boolean {
        for (pair: Pair<A, B> in super.queue) {
            if (pair.first?.equals(element) == true) {
                return true
            }
        }
        return false
    }

    fun getByFirst(element: A): Pair<A, B>? {
        for (pair: Pair<A, B> in super.queue) {
            if (pair.first == element) {
                return pair
            }
        }
        return null
    }
}