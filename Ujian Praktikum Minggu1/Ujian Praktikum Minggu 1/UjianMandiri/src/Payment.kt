class Payment(
    val order: Order,
    var method: PaymentMethod
) {
    private val _amount: Double = order.getTotalFare()

    fun getAmount(): Double {
        return _amount
    }

    fun getMethod(): String {
        return method.name
    }

    fun isPaid(): Boolean {
        return result is PaymentResult.Success
    }

    private var result: PaymentResult = PaymentResult.Pending

    fun processPayment(amount: Double): PaymentResult {
        if (result is PaymentResult.Success) {
            return PaymentResult.Failed(
                "Pembayaran sudah dilakukan",
                409
            )
        }

        if (amount < _amount + method.getFee(_amount)) {
            result = PaymentResult.Failed(
                "Nominal pembayaran kurang",
                400
            )
            return result
        }

        result = method.processPayment(_amount + method.getFee(_amount))
        return result
    }

    fun displayPayment() {
        println("\n===== DETAIL PEMBAYARAN =====")
        println("ID Pesanan : ${order.id}")
        println("Metode     : ${method.name}")
        println("Tarif      : Rp${_amount.toInt()}")
        println("Biaya      : Rp${method.getFee(_amount).toInt()}")
        println("Total Bayar: Rp${(_amount + method.getFee(_amount)).toInt()}")
        println("Status     : ${result.display()}")
    }
}