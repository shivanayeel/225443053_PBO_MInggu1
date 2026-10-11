interface PaymentMethod {
    val name: String

    fun processPayment(amount: Double): PaymentResult

    fun getFee(amount: Double): Double {
        return 0.0
    }
}

// Pembayaran kartu kredit
class CreditCard(val cardNumber: String) : PaymentMethod {
    override val name: String = "Kartu Kredit"

    init {
        require(cardNumber.all { it.isDigit() } && cardNumber.length >= 16) {
            "Nomor kartu harus minimal 16 digit angka"
        }
    }

    override fun getFee(amount: Double): Double {
        return amount * 0.02
    }

    override fun processPayment(amount: Double): PaymentResult {
        if (amount <= 0) {
            return PaymentResult.Failed("Nominal tidak valid", 400)
        }

        return PaymentResult.Success(
            "CC001",
            "2026-10-11"
        )
    }
}

// Pembayaran QRIS
class QRIS(val qrCode: String) : PaymentMethod {
    override val name: String = "QRIS"

    init {
        require(qrCode.length >= 10) {
            "Kode QR minimal 10 karakter"
        }
    }

    override fun getFee(amount: Double): Double {
        return amount * 0.005
    }

    override fun processPayment(amount: Double): PaymentResult {
        if (amount <= 0) {
            return PaymentResult.Failed("Nominal tidak valid", 400)
        }

        return PaymentResult.Success(
            "QR001",
            "2026-10-11"
        )
    }
}

// Pembayaran tunai
class Cash : PaymentMethod {
    override val name: String = "Tunai"

    override fun getFee(amount: Double): Double {
        return 0.0
    }

    override fun processPayment(amount: Double): PaymentResult {
        if (amount <= 0) {
            return PaymentResult.Failed("Nominal tidak valid", 400)
        }

        return PaymentResult.Success(
            "CSH001",
            "2026-10-11"
        )
    }
}