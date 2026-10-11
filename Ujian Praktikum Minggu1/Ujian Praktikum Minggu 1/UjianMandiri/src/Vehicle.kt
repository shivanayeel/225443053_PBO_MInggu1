open class Vehicle(
    val plateNumber: String,
    val brand: String,
    val model: String,
    val year: Int
) {
    var isAvailable: Boolean = true
    var tarifDasar: Double = 0.0

    open fun getType(): String {
        return "Kendaraan Umum"
    }

    open fun calculateFare(distanceKm: Double): Double {
        tarifDasar = 5000 + (distanceKm * 2000)
        return tarifDasar
    }

    open fun displayInfo() {
        println("Nomor Plat : $plateNumber")
        println("Brand      : $brand")
        println("Model      : $model")
        println("Tahun      : $year")
        println("Tipe       : ${getType()}")
        println("Tersedia   : $isAvailable")
    }
}