class TransportSystem(val name: String) {
    private val vehicles = mutableListOf<Vehicle>()
    private val drivers = mutableListOf<Driver>()
    private val customers = mutableListOf<Customer>()
    private val orders = mutableListOf<Order>()
    private val payments = mutableListOf<Payment>()

    // Menambahkan data
    fun addVehicle(vehicle: Vehicle) {
        vehicles.add(vehicle)
    }

    fun addDriver(driver: Driver) {
        drivers.add(driver)
    }

    fun addCustomer(customer: Customer) {
        customers.add(customer)
    }

    // Mencari data
    fun findVehicle(plateNumber: String): Vehicle? {
        return vehicles.find { it.plateNumber == plateNumber }
    }

    fun findDriver(id: String): Driver? {
        return drivers.find { it.id == id }
    }

    fun findCustomer(id: String): Customer? {
        return customers.find { it.idCustomer == id }
    }

    fun findOrder(id: String): Order? {
        return orders.find { it.id == id }
    }

    // Membuat pesanan
    fun createOrder(
        customerId: String,
        driverId: String,
        pickup: String,
        dest: String,
        distance: Double
    ): Order? {
        val customer = findCustomer(customerId)
        val driver = findDriver(driverId)

        if (customer == null || driver == null) {
            println("Customer atau driver tidak ditemukan!")
            return null
        }

        val order = Order(
            "ORD${(orders.size + 1).toString().padStart(3, '0')}",
            customer,
            driver,
            pickup,
            dest,
            distance
        )

        orders.add(order)
        println("Pesanan ${order.id} berhasil dibuat!")
        return order
    }

    // Memproses pembayaran
    fun processPayment(
        orderId: String,
        method: PaymentMethod,
        paidAmount: Double
    ): PaymentResult {
        val order = findOrder(orderId)

        if (order == null) {
            return PaymentResult.Failed("Pesanan tidak ditemukan", 404)
        }

        if (order.status !is OrderStatus.Completed &&
            order.status !is OrderStatus.Ongoing &&
            order.status !is OrderStatus.Waiting
        ) {
            return PaymentResult.Failed("Pesanan sudah dibatalkan", 400)
        }

        var payment = payments.find { it.order.id == orderId }

        if (payment == null) {
            payment = Payment(order, method)
            payments.add(payment)
        } else {
            payment.method = method
        }

        return payment.processPayment(paidAmount)
    }

    // Menyelesaikan pesanan
    fun completeOrder(orderId: String): Boolean {
        val order = findOrder(orderId) ?: return false

        if (order.status == OrderStatus.Waiting) {
            order.startTrip()
        }

        return order.completeTrip()
    }

    // Membatalkan pesanan
    fun cancelOrder(orderId: String, reason: String): Boolean {
        val order = findOrder(orderId) ?: return false
        return order.cancelTrip(reason)
    }

    // Laporan kendaraan
    fun displayAllVehicles() {
        println("\n===== SEMUA KENDARAAN =====")
        for (vehicle in vehicles) {
            println("${vehicle.plateNumber} | ${vehicle.brand} ${vehicle.model} | ${vehicle.getType()}")
        }
    }

    // Laporan driver
    fun displayAllDrivers() {
        println("\n===== SEMUA DRIVER =====")
        for (driver in drivers) {
            println("${driver.id} | ${driver.nameDriver} | ${driver.vehicle.getType()} | ${driver.vehicle.plateNumber}")
        }
    }

    // Laporan customer
    fun displayAllCustomers() {
        println("\n===== SEMUA CUSTOMER =====")
        for (customer in customers) {
            println("${customer.idCustomer} | ${customer.nameCustomer} | Saldo: Rp${customer.balance.toInt()}")
        }
    }

    // Laporan pesanan
    fun displayAllOrders() {
        println("\n===== SEMUA PESANAN =====")
        for (order in orders) {
            println(
                "${order.id} | ${order.customer.nameCustomer} | " +
                        "${order.driver.nameDriver} | Rp${order.getTotalFare().toInt()} | " +
                        order.status.display()
            )
        }
    }

    // Laporan pendapatan dari pesanan selesai
    fun displayRevenueReport() {
        val revenue = orders
            .filter { it.status == OrderStatus.Completed }
            .sumOf { it.getTotalFare() }

        println("\n===== LAPORAN PENDAPATAN =====")
        println("Jumlah pesanan selesai: ${orders.count { it.status == OrderStatus.Completed }}")
        println("Total pendapatan: Rp${revenue.toInt()}")
    }
}