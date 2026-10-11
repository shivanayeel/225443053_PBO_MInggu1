class Car(
    plateNumber: String,
    brand: String,
    model: String,
    year: Int,
    val fuelType: String,
    val numberOfDoors: Int
) : Vehicle(plateNumber, brand, model, year) {

    override fun getType(): String {
        return "Mobil"
    }

    override fun calculateFare(distanceKm: Double): Double {
        tarifDasar = 8000 + (distanceKm * 2500)
        return tarifDasar
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Jenis BBM  : $fuelType")
        println("Jumlah Pintu: $numberOfDoors")
    }
}