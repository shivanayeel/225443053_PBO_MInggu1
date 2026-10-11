sealed class OrderStatus() {
    data class Success(val data: String, val orderId: String): OrderStatus()
    data class Failed(val reason: String, val errorCode: Int): OrderStatus()
    data class Processing(val progress: Int): OrderStatus()
    object Pending : OrderStatus()
}

fun handlerOrderStatus(status: OrderStatus): String{
    return when (status){
        is OrderStatus.Success -> "Success: ${status.data}, OrderID: ${status.orderId}"
        is OrderStatus.Failed -> "Failed: ${status.reason}, Error Code: ${status.errorCode}"
        is OrderStatus.Processing -> "Processing: ${status.progress}%"
        OrderStatus.Pending -> "Pending"
    }
}

fun main(){
    val status1: OrderStatus = OrderStatus.Success("Pembayaran sudah diterima", "ID010")
    val status2: OrderStatus = OrderStatus.Failed("Gagal transaksi", 200)
    val status3: OrderStatus = OrderStatus.Processing(70)
    val status4: OrderStatus = OrderStatus.Pending

    val listStatus = listOf(status1, status2, status3, status4)

    for (status in listStatus) {
        println(handlerOrderStatus(status))
    }
}