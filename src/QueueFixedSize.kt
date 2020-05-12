// a very barebone implementation of a FIFO queue that has a fixed max size
// had to improvise as kotlin sadly doesn't inlude one in it's standard library
open class QueueFixedSize<T>(private val capacity: Int) {
    protected val queue: ArrayDeque<T> = ArrayDeque()

    // trying to add a new element if the queue is full will do nothing
    fun add(element: T): Unit {
        if (queue.size < capacity) queue.add(element)
    }
    fun first(): T = queue.first()
    operator fun get(index: Int): T = queue[index]
    fun isEmpty(): Boolean = queue.isEmpty()
    fun isFull(): Boolean = queue.size == capacity
    fun removeFirst(): T {
        return queue.removeFirst()
    }
    fun removeAt(index: Int): T {
        return queue.removeAt(index)
    }
    fun remove(element: T): Boolean {
        return queue.remove(element)
    }
    fun size(): Int = queue.size
    operator fun contains(element: T): Boolean = queue.contains(element)
    fun toList(): List<T> = queue.toList()
}