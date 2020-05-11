fun generateReferences(howMany: Int, process: Process, useLocality: Boolean): List<Int> = List(howMany) {
    // leaving this at-first-glance useless asignment to ease future convertion into a list of processes
    val itProcess = process
    if (useLocality) {
        if (itProcess.isLocality) {
            itProcess.tickLocality()
            if (itProcess.isLocalityReferencesLeftZero()) itProcess.stopLocality()
            itProcess.localityCurrentRange.random()
        } else {
            if ((0..100).random() <= process.localityCurrentChance) process.startLocality()
            process.incrementLocalityChance()
            process.pages.random()
        }
    } else process.pages.random()
}