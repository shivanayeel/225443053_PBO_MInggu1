open class Product(
    val id: String,
    val nama: String,
    val harga: Double,
    var stok: Int
) {
    open fun hitungDiskon(): Double {
        return 0.0
    }

    fun hitungHargaJual(): Double {
        return harga - hitungDiskon()
    }

    fun kurangiStok(jumlah: Int): Boolean {
        return if (stok >= jumlah) {
            stok -= jumlah
            true
        } else {
            false
        }
    }

    open fun tampilkanInfo(): String {
        val diskonStr = if (hitungDiskon() > 0) " (Diskon: Rp${String.format("%.0f", hitungDiskon())})" else ""
        return "[$id] $nama - Rp${String.format("%.0f", harga)}$diskonStr -> Rp${String.format("%.0f", hitungHargaJual())} | Stok: $stok"
    }
}

class Elektronik(
    id: String,
    nama: String,
    harga: Double,
    stok: Int,
    val garansi: Int,
    val daya: Int
) : Product(id, nama, harga, stok) {

    override fun hitungDiskon(): Double {
        return if (garansi > 12) {
            harga * 0.10
        } else {
            0.0
        }
    }

    override fun tampilkanInfo(): String {
        return super.tampilkanInfo() + " | Kategori: Elektronik (Garansi: ${garansi}bln, Daya: ${daya}W)"
    }
}

class Makanan(
    id: String,
    nama: String,
    harga: Double,
    stok: Int,
    val tanggalKadaluarsa: String,
    val sisaHariKadaluarsa: Int,
    val berat: Double
) : Product(id, nama, harga, stok) {

    override fun hitungDiskon(): Double {
        return if (sisaHariKadaluarsa in 1..6) {
            harga * 0.20
        } else {
            0.0
        }
    }

    override fun tampilkanInfo(): String {
        return super.tampilkanInfo() + " | Kategori: Makanan (Exp: $tanggalKadaluarsa, Berat: ${berat}g)"
    }
}

class Pakaian(
    id: String,
    nama: String,
    harga: Double,
    stok: Int,
    val ukuran: String,
    val bahan: String
) : Product(id, nama, harga, stok) {

    override fun hitungDiskon(): Double {
        return if (ukuran.equals("XL", ignoreCase = true)) {
            harga * 0.15
        } else {
            0.0
        }
    }

    override fun tampilkanInfo(): String {
        return super.tampilkanInfo() + " | Kategori: Pakaian (Ukuran: $ukuran, Bahan: $bahan)"
    }
}

class Toko(
    val nama: String
) {
    private val daftarProduk = mutableListOf<Product>()

    fun tambahProduk(product: Product) {
        daftarProduk.add(product)
        println("Produk '${product.nama}' berhasil ditambahkan ke $nama.")
    }

    fun cariProduk(keyword: String): List<Product> {
        return daftarProduk.filter {
            it.nama.contains(keyword, ignoreCase = true) || it.id.equals(keyword, ignoreCase = true)
        }
    }

    fun tampilkanSemuaProduk() {
        println("\n---- DAFTAR SEMUA PRODUK DI $nama ----")
        if (daftarProduk.isEmpty()) {
            println("Belum ada produk.")
        } else {
            daftarProduk.forEach { println(it.tampilkanInfo()) }
        }
    }

    fun tampilkanProdukByKategori(kategori: String) {
        println("\n=== PRODUK KATEGORI: ${kategori.uppercase()} ===")
        val filtered = when (kategori.lowercase()) {
            "elektronik" -> daftarProduk.filterIsInstance<Elektronik>()
            "makanan" -> daftarProduk.filterIsInstance<Makanan>()
            "pakaian" -> daftarProduk.filterIsInstance<Pakaian>()
            else -> emptyList()
        }

        if (filtered.isEmpty()) {
            println("Tidak ada produk untuk kategori '$kategori'.")
        } else {
            filtered.forEach { println(it.tampilkanInfo()) }
        }
    }

    fun tampilkanProdukDiskon() {
        println("\n=======================================================")
        println("PRODUK SEDANG DISKON")
        println("=======================================================")
        val produkDiskon = daftarProduk.filter { it.hitungDiskon() > 0 }
        if (produkDiskon.isEmpty()) {
            println("Saat ini tidak ada produk yang memiliki diskon.")
        } else {
            produkDiskon.forEach { println(it.tampilkanInfo()) }
        }
    }

    fun beliProduk(idProduk: String, jumlah: Int) {
        val produk = daftarProduk.find { it.id.equals(idProduk, ignoreCase = true) }
        if (produk != null) {
            if (produk.kurangiStok(jumlah)) {
                val totalBayar = produk.hitungHargaJual() * jumlah
                println("Pembelian Berhasil! [$jumlah x ${produk.nama}] - Total Bayar: Rp${String.format("%.0f", totalBayar)}")
            } else {
                println("Pembelian Gagal! Stok '${produk.nama}' tidak mencukupi (Sisa Stok: ${produk.stok}).")
            }
        } else {
            println("Produk dengan ID '$idProduk' tidak ditemukan!")
        }
    }
}

fun main() {
    val toko = Toko("Toko Serba Ada")

    println("--- SISTEM TOKO SERBA ADA ---")

    println("--- INPUT DATA PRODUK ---")
    toko.tambahProduk(Elektronik("E01", "MacBook Pro 16 M3 Max", 75000000.0, 5, garansi = 24, daya = 140))
    toko.tambahProduk(Elektronik("E02", "DJI Mavic 3 Pro Cine", 79000000.0, 10, garansi = 12, daya = 65))

    toko.tambahProduk(Makanan("M01", "Daging Wagyu A5 Miyazaki", 3500000.0, 20, tanggalKadaluarsa = "15/08/2026", sisaHariKadaluarsa = 3, berat = 500.0))
    toko.tambahProduk(Makanan("M02", "Kaviar Beluga Iran", 15000000.0, 15, tanggalKadaluarsa = "20/12/2026", sisaHariKadaluarsa = 130, berat = 250.0))

    toko.tambahProduk(Pakaian("P01", "Jaket Kulit Gucci", 45000000.0, 12, ukuran = "XL", bahan = "Kulit Asli"))
    toko.tambahProduk(Pakaian("P02", "Kemeja Sutra Louis Vuitton", 25000000.0, 8, ukuran = "M", bahan = "Sutra Premium"))

    toko.tampilkanSemuaProduk()

    toko.tampilkanProdukByKategori("Elektronik")

    toko.tampilkanProdukDiskon()

    println("\n--- PROSES TRANSAKSI PEMBELIAN ---")
    toko.beliProduk("E01", 2)
    toko.beliProduk("M01", 5)
    toko.beliProduk("P01", 15)

    println("\n--- STATUS AKHIR SEMUA PRODUK ---")
    toko.tampilkanSemuaProduk()
}