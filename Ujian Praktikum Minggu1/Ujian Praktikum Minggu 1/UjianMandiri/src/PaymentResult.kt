sealed class PaymentResult {
    abstract fun display(): String

    data class Success(
        val transactionId: String,
        val timestamp: String
    ) : PaymentResult() {
        override fun display(): String {
            return "Berhasil | ID Transaksi: $transactionId | Waktu: $timestamp"
        }
    }

    data class Failed(
        val reason: String,
        val errorCode: Int
    ) : PaymentResult() {
        override fun display(): String {
            return "Gagal | Alasan: $reason | Kode Error: $errorCode"
        }
    }

    data object Pending : PaymentResult() {
        override fun display(): String {
            return "Pembayaran masih menunggu"
        }
    }
}