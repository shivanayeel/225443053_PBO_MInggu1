sealed class OrderStatus {
    abstract fun display(): String

    fun isFinal(): Boolean {
        return this is Completed || this is Cancelled
    }

    data object Waiting : OrderStatus() {
        override fun display(): String = "Menunggu"
    }

    data object Ongoing : OrderStatus() {
        override fun display(): String = "Berjalan"
    }

    data object Completed : OrderStatus() {
        override fun display(): String = "Selesai"
    }

    data class Cancelled(
        val reason: String
    ) : OrderStatus() {
        override fun display(): String {
            return "Dibatalkan: $reason"
        }
    }
}