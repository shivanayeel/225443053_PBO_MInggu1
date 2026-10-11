class Driver(val id: String, val nameDriver: String, val phoneDriver: String, val vehicle: Vehicle) {
    var isActive: Boolean = true

    fun displayInfoD(): Unit{
        println("ID Driver       : $id")
        println("Nama Driver     : $nameDriver")
        println("Phone Driver    : $phoneDriver")
        println("Kendaraan Driver: $vehicle\n")
    }

    fun acceptOrder(): Boolean{
        if (!isActive) {vehicle.isAvailable == true
            return true}   else {return false}
    }
}