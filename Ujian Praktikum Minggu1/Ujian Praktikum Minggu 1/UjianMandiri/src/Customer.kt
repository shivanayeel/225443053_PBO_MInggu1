class Customer(val idCustomer: String, val nameCustomer: String, val phoneCustomer: String, val email: String, var balance: Double = 0.0) {

    fun displayInfoC(): Unit {
        println("Id Customer    : $idCustomer")
        println("Nama Customer  : $nameCustomer")
        println("Phone Number   : $phoneCustomer")
        println("Email          : $email")
        println("Saldo Customer : $balance\n")
    }

    fun topUp(amount: Double): Unit {
        balance += amount
        println("Top up berhasil! Saldo anda sekarang $balance!")
    }

    fun canPay(amount: Double): Boolean {
        return balance >= amount
    }
}