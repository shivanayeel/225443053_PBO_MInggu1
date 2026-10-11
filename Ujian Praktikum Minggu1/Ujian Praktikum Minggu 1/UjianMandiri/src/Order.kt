class Order(
    val id: String,
    val customer: Customer,
    val driver: Driver,
    val pickupLocation: String,
    val destination: String,
    val distanceKm: Double
) {
    var status: OrderStatus = OrderStatus.Waiting
        private set

    private var _totalFare: Double

    init {
        require(distanceKm > 0) {
            "Jarak harus lebih dari 0 km"
        }

        _totalFare = driver.vehicle.calculateFare(distanceKm)
    }

    fun getTotalFare(): Double {
        return _totalFare
    }

    fun startTrip(): Boolean {
        if (status == OrderStatus.Waiting) {
            status = OrderStatus.Ongoing
            return true
        }
        return false
    }

    fun completeTrip(): Boolean {
        if (status == OrderStatus.Ongoing) {
            status = OrderStatus.Completed
            return true
        }
        return false
    }

    fun cancelTrip(reason: String): Boolean {
        if (!status.isFinal()) {
            status = OrderStatus.Cancelled(reason)
            return true
        }
        return false
    }

    fun displayOrder() {
        println("\n===== DETAIL PESANAN =====")
        println("ID Pesanan : $id")
        println("Pelanggan  : ${customer.nameCustomer}")
        println("Driver     : ${driver.nameDriver}")
        println("Kendaraan  : ${driver.vehicle.getType()}")
        println("Jemput     : $pickupLocation")
        println("Tujuan     : $destination")
        println("Jarak      : $distanceKm km")
        println("Total Tarif: Rp${_totalFare.toInt()}")
        println("Status     : ${status.display()}")
    }
}