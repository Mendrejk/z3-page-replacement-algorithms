fun main() {
    val mainProcess: Process = Process(PROCESS_PAGE_COUNT)
    val references: List<Int> = generateReferences(REFERENCE_COUNT, mainProcess, true)
    println("fifo: ${firstInFirstOut(references)}")
    println("opt: ${optimal(references)}")
    println("lru: ${leastRecentlyUsed(references)}")
}