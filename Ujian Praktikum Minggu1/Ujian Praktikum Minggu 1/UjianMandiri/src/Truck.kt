class Truck(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val loadCapacity: Double,
    val numberOfAxles: Int
) : Vehicle(plateNumber, brand, model, year) {

    override fun getType(): String {
        return "Truk"
    }

    override fun calculateFare(distanceKm: Double): Double {
        tarifDasar = 10000 + (distanceKm * 3500)
        return tarifDasar
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas Muatan: $loadCapacity ton")
        println("Jumlah Sumbu    : $numberOfAxles")
    }
}