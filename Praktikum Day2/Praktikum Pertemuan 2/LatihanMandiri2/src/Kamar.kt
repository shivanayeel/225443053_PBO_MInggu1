class Kamar(
    val roomNumber: String,
    val type: String = "Standard",
    val pricePerNight: Double,
) {
    init {
        require(type in listOf("Standard", "Deluxe", "Suite")) {
            "Type $type error"
        }
    }

    var isOccupied: Boolean = false
        private set

    var guestName: String? = null
        private set

    var checkInDate: String? = null
        private set

    var checkOutDate: String? = null
        private set

    fun isAvailable(): Boolean{
        return !isOccupied
    }

    fun checkIn(guest: String, checkIn: String, checkOut: String): Boolean {
        if (isAvailable()) {
            isOccupied = true
            guestName = guest
            checkInDate = checkIn
            checkOutDate = checkOut
            return true
        }
        print("Kamar $roomNumber Sudah diisi")
        return false
    }

    fun checkOut(): Boolean{
        isOccupied = false
        return false
    }

    fun displayInfo(): String {
        val status = if (isOccupied) "Terisi ($guestName)" else "Tersedia"
        return "Kamar $roomNumber [$type] - Harga Rp $pricePerNight | Status: $status"
    }
}

class Hotel(
    val name: String,
){
    private val rooms = mutableListOf<Kamar>()
    private var totalRevenue: Double = 0.0

    fun addRoom (room: Kamar){
        rooms.add(room)
        println("Menambahkan ${room.roomNumber}")
    }

    fun find(roomNumber: String):Kamar?{
        return rooms.find{it.roomNumber.equals(roomNumber, ignoreCase = true)}
    }

    fun checkIn(roomNumber: String, guest: String, checkIn: String, checkOut: String): Boolean{
        val room = find(roomNumber)
        if (room == null) {
            print("Kamar $roomNumber tidak ditemukan")
            return false
        }

        val success = room.checkIn(guest, checkIn, checkOut)
        if (success){
            println("Checkin Berhasil, $guest di kamar $roomNumber")
        } else {
            println("Checkin Gagal, $roomNumber sedang di isi")
        }
        return success
    }

    fun checkOut(roomNumber: String): Boolean{
        val room = find(roomNumber)
        if (room == null){
            println("Kamar $roomNumber tidak ditemukan")
            return false
        }

        if (room.isOccupied){
            val guest = room.guestName
            totalRevenue += room.pricePerNight
            room.checkOut()
            println("Checkout berhasil, $guest dari kamar $roomNumber")
            return true
        } else {
            println("Checkout Gagal, $roomNumber masih diisi")
            return false
        }
    }

    fun getAvailableRooms(): List<Kamar>{
        return rooms.filter { it.isAvailable() }
    }

    fun getOccupiedRooms(): List<Kamar>{
        return rooms.filter { it.isOccupied }
    }

    fun displayAllRooms(){
        println("\n--- DAFTAR KAMAR ---")
        if (rooms.isEmpty())
            println("Belum ada kamar")
        else rooms.forEach { println(it.displayInfo()) }
    }

    fun displayAvailableRooms(){
        println("\n--- KAMAR TERSEDIA ---")
        val available = getAvailableRooms()
        if (available.isEmpty())
            println("Tidak ada kamar tersedia")
        else available.forEach { println(it.displayInfo()) }
    }

    fun displayOccupied(){
        println("\n--- OCCUPIED ---")

        val occupied = getOccupiedRooms()
        if (occupied.isEmpty())
            println("Tidak ada kamar terisi")
        else occupied.forEach { println(it.displayInfo()) }
    }

    fun getTotalRevenue(): Double{
        return totalRevenue
    }
}

fun main(){
    val hotel = Hotel("Hotel F1")

    println("--- KAMAR DITAMBAHKAN ---")
    hotel.addRoom(Kamar("C301", "Standard", 3000000.0))
    hotel.addRoom(Kamar("C302", "Standard", 3000000.0))
    hotel.addRoom(Kamar("B204", "Deluxe", 4500000.0))
    hotel.addRoom(Kamar("D304", "Deluxe", 4500000.0))
    hotel.addRoom(Kamar("A201", "Suite", 6000000.0))
    hotel.addRoom(Kamar("A202", "Suite", 6000000.0))

    hotel.displayAllRooms()

    println("\n--- CHECKIN ---")
    hotel.checkIn("A201", "George Russell", "14/08/2026", "16/01/2026" )
    hotel.checkIn("A202", "Kimi Antonelli", "11/06/2026", "12/06/2026" )
    hotel.checkIn("B204", "Ollie Bearman", "14/07/2026", "15/07/2026\n" )
    hotel.checkIn("C301", "Max Verstappen", "12/05/2026", "12/05/2026" )

    hotel.displayAvailableRooms()
    hotel.displayOccupied()

    println("\n--- CHECKOUT ---")
    hotel.checkOut("A201")
    hotel.checkOut("B204")

    hotel.displayAllRooms()
    println("Hasil pendapatan = Rp. ${hotel.getTotalRevenue()}")
}