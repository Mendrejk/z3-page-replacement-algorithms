fun main() {
    val mainProcess: Process = Process(0, PROCESS_PAGE_COUNT)
    val references: List<Int> = generateReferences(REFERENCE_COUNT, mainProcess, true)
    print(FirstInFirstOut(references))
}