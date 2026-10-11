open class Vehicle(
    val brand: String,
    val model: String,
    val year: Int,
    val baseRentalPrice: Double
) {
    open fun calculateRentalCost(days: Int): Double {
        return baseRentalPrice * days
    }

    open fun displayInfo() {
        println("Merek          : $brand")
        println("Model          : $model")
        println("Tahun          : $year")
        println("Harga Sewa/Hari: Rp$baseRentalPrice")
    }
}

class Car(
    brand: String,
    model: String,
    year: Int,
    baseRentalPrice: Double,
    val numberOfDoors: Int,
    val isElectric: Boolean
) : Vehicle(brand, model, year, baseRentalPrice) {

    override fun calculateRentalCost(days: Int): Double {
        val total = super.calculateRentalCost(days)
        return if (isElectric) {
            total * 0.90
        } else {
            total
        }
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Jumlah Pintu   : $numberOfDoors")
        println("Mobil Listrik  : ${if (isElectric) "Ya" else "Tidak"}")
    }
}

class Motorcycle(
    brand: String,
    model: String,
    year: Int,
    baseRentalPrice: Double,
    val engineCapacity: Int
) : Vehicle(brand, model, year, baseRentalPrice) {

    override fun calculateRentalCost(days: Int): Double {
        val total = super.calculateRentalCost(days)
        return if (engineCapacity > 250) {
            total * 1.15
        } else {
            total
        }
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas Mesin: $engineCapacity cc")
    }
}

class Truck(
    brand: String,
    model: String,
    year: Int,
    baseRentalPrice: Double,
    val cargoCapacityTon: Double
) : Vehicle(brand, model, year, baseRentalPrice) {

    override fun calculateRentalCost(days: Int): Double {
        val total = super.calculateRentalCost(days)
        return total + (cargoCapacityTon * 50000.0 * days)
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Kapasitas Muatan: $cargoCapacityTon Ton")
    }
}

fun main() {
    val car1 = Car("Mercedes-Benz", "AMG ONE", 2024, 45000000000.0, 2, false)
    val car2 = Car("Ferrari", "SF90 Stradale", 2023, 20000000000.0, 2, true)
    val motor1 = Motorcycle("Vespa", "Sprint Justin Bieber X", 2022, 75000000.0, 150)
    val motor2 = Motorcycle("Harley-Davidson", "CVO Road Glide", 2024, 1500000000.0, 1923)
    val truck = Truck("Volvo", "FH16 750", 2024, 3500000000.0, 44.0)

    val vehicles: List<Vehicle> = listOf(car1, car2, motor1, motor2, truck)
    val rentalDays = 3

    println("--- Sistem Manajemen Sewa Kendaraan ---")
    println("Lama Sewa: $rentalDays hari\n")

    for (vehicle in vehicles) {
        vehicle.displayInfo()
        println("Total Biaya Sewa ($rentalDays hari): Rp${vehicle.calculateRentalCost(rentalDays)}")
        println("----------------------------------------------")
    }
}