class Motorcycle(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val engineCapacity: Int,
    val hasHelmet: Boolean
) : Vehicle(plateNumber, brand, model, year) {

    override fun getType(): String {
        return "Motor"
    }

    override fun calculateFare(distanceKm: Double): Double {
        tarifDasar = 3000 + (distanceKm * 1500)
        return tarifDasar
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas Mesin: $engineCapacity cc")
        println("Ada Helm      : $hasHelmet")
    }
}