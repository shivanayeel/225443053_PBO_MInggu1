class Karyawan(val nama: String, var gajiPokok: Double) {
    fun tampilkanGaji() {
        val formatGaji = String.format("Rp %,d", gajiPokok.toInt())
        println("Nama: $nama, Gaji: $formatGaji")
    }

    fun naikGaji(persen: Double) {
        if (persen > 0) {
            val kenaikan = gajiPokok * (persen / 100)
            gajiPokok += kenaikan
            println(">> (Gaji berhasil dinaikkan sebesar $persen%)")
        } else {
            println(">> (Gagal: Persentase kenaikan harus lebih dari 0)")
        }
    }
}

fun main() {
    println("=== MANAJEMEN GAJI KARYAWAN ===")
    val karyawan1 = Karyawan("Aleea Zahra", 5000000.0)

    println("Informasi Awal")
    karyawan1.tampilkanGaji()

    println("\nProses Kenaikan Gaji")
    karyawan1.naikGaji(15.0)

    println("\nInformasi Akhir")
    karyawan1.tampilkanGaji()
    println("===============================")
}