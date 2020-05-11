class Process(pageCount: Int) {
    val pages: List<Int> = (0 until pageCount).toList()
    private val localityBaseChance: Int = LOCALITY_BASE_CHANCE
    private val localityGain: Int = LOCALITY_GAIN
    var localityCurrentChance = localityBaseChance
        private set
    var isLocality: Boolean = false
        private set
    private var localityReferencesLeft: Int = 0
    var localityCurrentRange: IntRange = IntRange(0, 0)
        private set
    private val localityMinimalScope = LOCALITY_MINIMAL_SCOPE
    private val localityMaximalScope = LOCALITY_MAXIMAL_SCOPE
    private val localityMinimalLength = LOCALITY_MINIMAL_LENGTH
    private val localityMaximalLength = LOCALITY_MAXIMAL_LENTH

    fun incrementLocalityChance(): Unit {
        localityCurrentChance += localityGain
        if (localityCurrentChance > 100) localityCurrentChance = 100
    }

    fun startLocality() {
        isLocality = true
        generateLocalityRange()
        generateLocalityLength()
    }

    fun stopLocality() {
        isLocality = false
        localityCurrentChance = localityBaseChance
    }

    fun tickLocality(): Unit {
        localityReferencesLeft--;
    }

    fun isLocalityReferencesLeftZero(): Boolean = localityReferencesLeft == 0

    private fun generateLocalityRange(): Unit {
        val rangeLength = generateLocalityScope()
        val rangeStartIndex = (0 until (pages.size - rangeLength)).random()
        localityCurrentRange = pages[rangeStartIndex]..pages[rangeStartIndex + rangeLength]
    }

    private fun generateLocalityLength(): Unit {
        localityReferencesLeft = (localityMinimalLength..localityMaximalLength).random()
    }

    private fun generateLocalityScope(): Int = (localityMinimalScope..localityMaximalScope).random()
}