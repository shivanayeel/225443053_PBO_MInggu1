class Buku(
    val judul: String,
    val penulis: String,
    val tahunTerbit: Int,
    var isDipinjam: Boolean = false,
    var peminjam: String? = null
) {
    fun pinjam(namaPeminjam: String) {
        isDipinjam = true
        peminjam = namaPeminjam
        println("Buku '$judul' berhasil dipinjam oleh $peminjam.")
    }

    fun kembalikan() {
        isDipinjam = false
        peminjam = null
        println("Buku '$judul' telah dikembalikan.")
    }

    fun tampilkanInfo() {
        println("--- Informasi Buku ---")
        println("Judul        : $judul")
        println("Penulis      : $penulis")
        println("Tahun Terbit : $tahunTerbit")
        println("Status       : ${if (isDipinjam) "Dipinjam oleh $peminjam" else "Tersedia"}")
        println()
    }
}

fun main() {
    // Contoh penggunaan / pengujian program
    val buku1 = Buku("Belajar Pemrograman Kotlin", "Andi Pratama", 2023)

    buku1.tampilkanInfo()

    buku1.pinjam("Budi")
    buku1.tampilkanInfo()

    buku1.kembalikan()
    buku1.tampilkanInfo()
}