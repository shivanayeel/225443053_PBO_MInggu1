class Buku(val judul: String, val pengarang: String, var tahunTerbit: Int) {
    fun infoBuku() {
        println("Judul        : $judul")
        println("Pengarang    : $pengarang")
        println("Tahun Terbit : $tahunTerbit")
        println("-----------------------------------")
    }
}

fun main() {
    println("=== SISTEM PEMINJAMAN BUKU ===")
    // Instansiasi 3 objek
    val buku1 = Buku("Agent Russell", "George Russell", 2020)
    val buku2 = Buku("Williams Racing", "Carloz Sainz", 2025)
    val buku3 = Buku("Tifosi", "Charles Leclerc", 2023)
    val buku4 = Buku("Race For History", "Toto", 2025)

    val daftarBuku = listOf(buku1, buku2, buku3, buku4)
    for (buku in daftarBuku) {
        buku.infoBuku()
    }
}