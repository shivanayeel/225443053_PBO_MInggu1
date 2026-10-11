class Book(
    val judul: String,
    val penulis: String,
    val isbn: String,
    val tahunTerbit: Int,
){
    var isDipinjam: Boolean = false
        private set
    var peminjam: String? = null
        private set

    fun pinjam(namaPeminjam: String): Boolean {
        if (isDipinjam) {
            println("Book $judul telah dipinjam oleh $peminjam")
            return false
        }
        isDipinjam = true
        peminjam = namaPeminjam
        println("Book '$judul' berhasil dipinjam oleh $namaPeminjam")
        return true
    }

    fun kembalikan(): Boolean {
        if (isDipinjam) {
            println("Book berhasil dikembalikan oleh $peminjam")
            isDipinjam = false
            peminjam = null
            return true
        } else{
            println("Book tidak sedang dipijam oleh $peminjam")
            return false
        }
    }

    fun tampilkanInfo(): String {
        return "Judul: $judul\nPenulis : $penulis\nIsbn: $isbn\nTahun Terbit: $tahunTerbit\nStatus: $isDipinjam"
    }

    fun isTersedia(): Boolean {
        if (isDipinjam) {
            println("Book sedang dipinjam oleh $peminjam")
            return false
        } else{
            println("Book tersedia")
            return true
        }
    }
}

class Perpustakaan(
    val nama: String
) {
    private val daftarBook = mutableListOf<Book>()

    fun tambahBook(buku: Book) {
        daftarBook.add(buku)
        println("Book '${buku.judul}' ditambahkan ke perpustakaan")
    }

    private fun cariByIsbn(isbn: String): Book? {
        return daftarBook.find { it.isbn == isbn }
    }

    fun pinjamBook(isbn: String, peminjam: String): Boolean {
        val buku = cariByIsbn(isbn)
        if (buku == null) {
            println("Book dengan ISBN $isbn tidak ditemukan")
            return false
        }
        return buku.pinjam(peminjam)
    }

    fun kembalikanBook(isbn: String): Boolean {
        val buku = cariByIsbn(isbn)
        if (buku == null) {
            println("Book dengan ISBN $isbn tidak ditemukan")
            return false
        }
        return buku.kembalikan()
    }

    fun cariBook(keyword: String): List<Book> {
        val lowerKeyword = keyword.lowercase()
        return daftarBook.filter {
            it.judul.lowercase().contains(lowerKeyword) ||
                    it.penulis.lowercase().contains(lowerKeyword)
        }
    }

    fun tampilkanSemuaBook() {
        println("=== DAFTAR SEMUA BUKU — $nama ===")
        if (daftarBook.isEmpty()) {
            println("Belum ada buku")
        } else {
            for (buku in daftarBook) {
                println(buku.tampilkanInfo())
                println("\n")
            }
        }
    }

    fun tampilkanBookTersedia() {
        val tersedia = daftarBook.filter { it.isTersedia() }
        println("=== BUKU TERSEDIA (${tersedia.size}) ===")
        for (buku in tersedia) {
            println(buku.tampilkanInfo())
            println("\n")
        }
    }

    fun tampilkanBookDipinjam() {
        val dipinjam = daftarBook.filter { it.isDipinjam }
        println("=== BUKU DIPINJAM (${dipinjam.size}) ===")
        for (buku in dipinjam) {
            println(buku.tampilkanInfo())
            println("\n")
        }
    }
}

fun main() {
    val perpus = Perpustakaan("Perpustakaan Kampus")

    perpus.tambahBook(Book("Surat Cinta Untuk Russell", "Shiva Nayla Z", "978-1", 2023))
    perpus.tambahBook(Book("Kucing Imut", "Roy Kiyoshi", "978-2", 1991))
    perpus.tambahBook(Book("Ngoding Itu Menyenangkan", "Dafirly", "978-3", 2017))
    perpus.tambahBook(Book("Antara Aku dan Dia", "Rafiw R", "978-4", 2025))
    perpus.tambahBook(Book("HIMAMO Ku", "Ajeng Putri", "978-5", 1996))
    println()

    println("--- SEMUA BUKU ---")
    perpus.tampilkanSemuaBook()
    println()

    println("--- PROSES PEMINJAMAN ---")
    perpus.pinjamBook("978-1", "Angelica")
    perpus.pinjamBook("978-3", "Chantika")
    perpus.pinjamBook("978-9", "Andi")
    println()

    println("--- STATUS SETELAH PEMINJAMAN ---")
    perpus.tampilkanBookTersedia()
    println()
    perpus.tampilkanBookDipinjam()
    println()

    println("--- PROSES PENGEMBALIAN ---")
    perpus.kembalikanBook("978-1")
    println()

    println("--- STATUS AKHIR ---")
    perpus.tampilkanSemuaBook()
}