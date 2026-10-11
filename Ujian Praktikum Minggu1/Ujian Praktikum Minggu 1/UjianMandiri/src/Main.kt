fun main() {
    // 1. Membuat sistem transportasi
    val system = TransportSystem("Go-Transport 2024")

    // 2. Membuat kendaraan
    val car = Car("B 1234 XYZ", "Toyota", "Innova", 2021, "Bensin", 4)
    val motorcycle = Motorcycle("D 5678 ABC", "Honda", "Beat", 2022, 125, true)
    val truck = Truck("E 9012 DEF", "Hino", "Dutro", 2020, 5.0, 2)

    system.addVehicle(car)
    system.addVehicle(motorcycle)
    system.addVehicle(truck)

    // 3. Membuat driver
    system.addDriver(Driver("D001", "Andi", "08123456789", car))
    system.addDriver(Driver("D002", "Budi", "08129876543", motorcycle))
    system.addDriver(Driver("D003", "Citra", "08125678901", truck))

    // 4. Membuat customer
    system.addCustomer(Customer("C001", "Dewi", "08134567890", "dewi@email.com", 100000.0))
    system.addCustomer(Customer("C002", "Eko", "08135678901", "eko@email.com", 50000.0))
    system.addCustomer(Customer("C003", "Fani", "08136789012", "fani@email.com", 200000.0))

    // 5. Menampilkan data awal
    println("===== ${system.name} =====")
    system.displayAllVehicles()
    system.displayAllDrivers()
    system.displayAllCustomers()

    // 6. Membuat tiga pesanan
    val order1 = system.createOrder("C001", "D001", "Kampus A", "Mall B", 12.0)
    val order2 = system.createOrder("C002", "D002", "Stasiun", "Kantor", 8.0)
    val order3 = system.createOrder("C003", "D003", "Gudang", "Pelabuhan", 25.0)

    system.displayAllOrders()

    // 7. Pembayaran order 1 menggunakan QRIS
    println("\n===== PEMBAYARAN ORDER 1 =====")
    if (order1 != null) {
        val qris = QRIS("QRISABC12345")
        val total = order1.getTotalFare() + qris.getFee(order1.getTotalFare())

        val result = system.processPayment(order1.id, qris, total)
        println(result.display())
    }

    // 8. Pembayaran order 2 menggunakan tunai, nominal kurang Rp10.000
    println("\n===== PEMBAYARAN ORDER 2 =====")
    if (order2 != null) {
        val cash = Cash()
        val total = order2.getTotalFare() + cash.getFee(order2.getTotalFare())
        val kurang = total - 10000.0

        println("Tagihan: Rp${total.toInt()}")
        println("Dibayar: Rp${kurang.toInt()}")

        val result = system.processPayment(order2.id, cash, kurang)
        println(result.display())

        // Top-up saldo Eko
        println("\nTop-up saldo Eko Rp10.000")
        system.findCustomer("C002")?.topUp(10000.0)

        // Membayar kembali menggunakan kartu kredit
        val creditCard = CreditCard("1234567890123456")
        val totalKartu = order2.getTotalFare() +
                creditCard.getFee(order2.getTotalFare())

        println("\nPembayaran ulang dengan Kartu Kredit")
        println("Total tagihan: Rp${totalKartu.toInt()}")

        val retryResult = system.processPayment(
            order2.id,
            creditCard,
            totalKartu
        )
        println(retryResult.display())
    }

    // 9. Menyelesaikan order 1
    println("\n===== SELESAIKAN ORDER 1 =====")
    if (order1 != null) {
        order1.startTrip()
        println("Berhasil diselesaikan: ${system.completeOrder(order1.id)}")
    }

    // 10. Membatalkan order 3
    println("\n===== BATALKAN ORDER 3 =====")
    if (order3 != null) {
        println(
            "Pembatalan berhasil: " +
                    system.cancelOrder(order3.id, "Hujan deras")
        )
    }

    // 11. Menampilkan status akhir
    system.displayAllOrders()

    // 12. Laporan pendapatan
    system.displayRevenueReport()

    // 13. Demonstrasi polymorphism
    println("\n===== DEMONSTRASI POLYMORPHISM =====")
    val vehicles: List<Vehicle> = listOf(car, motorcycle, truck)

    for (vehicle in vehicles) {
        println(
            "${vehicle.getType()} | " +
                    "Tarif 15 km: Rp${vehicle.calculateFare(15.0).toInt()}"
        )
    }

    // 14. Demonstrasi smart casting
    println("\n===== DEMONSTRASI SMART CASTING =====")
    val driver = system.findDriver("D001")

    if (driver != null) {
        when (val vehicle = driver.vehicle) {
            is Car -> println("Driver Andi menggunakan mobil ${vehicle.model}, BBM: ${vehicle.fuelType}")
            is Motorcycle -> println("Driver Andi menggunakan motor ${vehicle.model}")
            is Truck -> println("Driver Andi menggunakan truk ${vehicle.model}")
        }
    }

    // 15. Demonstrasi sealed class OrderStatus
    println("\n===== DEMONSTRASI ORDER STATUS =====")

    val statuses: List<OrderStatus> = listOf(
        OrderStatus.Waiting,
        OrderStatus.Ongoing,
        OrderStatus.Completed,
        OrderStatus.Cancelled("Hujan deras")
    )

    for (status in statuses) {
        when (status) {
            OrderStatus.Waiting -> println(status.display())
            OrderStatus.Ongoing -> println(status.display())
            OrderStatus.Completed -> println(status.display())
            is OrderStatus.Cancelled -> println(status.display())
        }
    }
}