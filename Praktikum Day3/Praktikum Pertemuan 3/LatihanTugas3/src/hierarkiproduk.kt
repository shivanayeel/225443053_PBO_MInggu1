open class Produk(
    val id: String,
    val name: String,
    val price: Double
) {
    open fun getDiscountedPrice(): Double {
        return price
    }

    open fun displayInfo() {
        println("ID Produk    : $id")
        println("Nama Produk  : $name")
        println("Harga Awal   : Rp$price")
        println("Harga Diskon : Rp${getDiscountedPrice()}")
    }
}

class ElectronicProduct(
    id: String,
    name: String,
    price: Double,
    val warrantyMonths: Int
) : Produk(id, name, price) {

    override fun getDiscountedPrice(): Double {
        return if (warrantyMonths > 12) {
            price * 0.90
        } else {
            price
        }
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Garansi      : $warrantyMonths bulan")
    }
}

class FoodProduct(
    id: String,
    name: String,
    price: Double,
    val expiryDate: String,
    val isNearExpiry: Boolean = false
) : Produk(id, name, price) {

    override fun getDiscountedPrice(): Double {
        return if (isNearExpiry) {
            price * 0.80
        } else {
            price
        }
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Tgl Kadaluarsa: $expiryDate")
    }
}

class ClothingProduct(
    id: String,
    name: String,
    price: Double,
    val size: String,
    val material: String
) : Produk(id, name, price) {

    override fun getDiscountedPrice(): Double {
        return if (size.equals("XL", ignoreCase = true)) {
            price * 0.85
        } else {
            price
        }
    }

    override fun displayInfo() {
        super.displayInfo()
        println("Ukuran       : $size")
        println("Bahan        : $material")
    }
}

fun main() {
    val laptop = ElectronicProduct("A01", "Laptop Asus Vivobook", 10000000.0, 24)
    val tv = ElectronicProduct("A02", "TV LG 50 inch", 7000000.0, 12)

    val susu = FoodProduct("E01", "Roti Breadco Gepeng", 10000.0, "2026-08-15", true)
    val roti = FoodProduct("E02", "Susu Cimory Cashew", 7000.0, "2026-11-20", false)

    val kaosL = ClothingProduct("B01", "Kaos Spiderman", 1000000.0, "L", "Katun")
    val kaosXL = ClothingProduct("B02", "Kaos HIMAMO", 500000.0, "XL", "Katun")

    val products: List<Produk> = listOf(laptop, tv, roti, susu, kaosL, kaosXL)

    println("=== DAFTAR PRODUK DAN HARGA SETELAH DISKON ===\n")
    for (product in products) {
        product.displayInfo()
        println("----------------------------------------------")
    }
}