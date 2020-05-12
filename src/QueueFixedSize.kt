// a very barebone implementation of a FIFO queue that has a fixed max size
// had to improvise as kotlin sadly doesn't inlude one in it's standard library
class QueueFixedSize<T>(val capacity: Int) {
    private val queue: ArrayDeque<T> = ArrayDeque()

    // trying to add a new element if the queue is full will do nothing
    fun add(element: T): Unit {
        if (queue.size < capacity) queue.add(element)
    }
    fun first(): T = queue.first()
    fun get(index: Int): T = queue[index]
    fun isEmpty(): Boolean = queue.isEmpty()
    fun isFull(): Boolean = queue.size == capacity
    fun removeFirst(): Unit {
        queue.removeFirst()
    }
    fun remove(index: Int): Unit {
        queue.removeAt(index)
    }
    fun size(): Int = queue.size

    operator fun contains(element: T): Boolean = queue.contains(element)
    fun toList(): List<T> = queue.toList()
}