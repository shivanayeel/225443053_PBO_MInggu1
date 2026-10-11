class Mahasiswa(val nim: String, val nama: String, val jurusan: String, val ipk: Double) {

    fun tampilkan() {
        var predikat = ""
        if (ipk >= 3.51) {
            predikat = "Dengan Pujian (Cumlaude)"
        } else if (ipk >= 2.76) {
            predikat = "Sangat Memuaskan"
        } else if (ipk >= 2.00) {
            predikat = "Cukup"
        } else {
            predikat = "Kurang"
        }
        println("NIM: $nim")
        println("Nama: $nama")
        println("Jurusan: $jurusan")
        println("IPK: $ipk")
        println("Predikat: $predikat")
    }
}

fun main() {
    val daftarMahasiswa = mutableListOf<Mahasiswa>()
    var lanjut = "y"

    // Looping untuk input user
    while (lanjut == "y") {
        print("Masukkan NIM: ")
        val nim = readln()

        print("Masukkan Nama: ")
        val nama = readln()

        print("Masukkan Jurusan: ")
        val jurusan = readln()

        print("Masukkan IPK: ")
        val ipk = readln().toDouble()

        val mhs = Mahasiswa(nim, nama, jurusan, ipk)
        daftarMahasiswa.add(mhs)

        print("\nApakah ingin menambah data lagi? (y/n): ")
        lanjut = readln().lowercase()
        println()
    }

    println("===================================")
    println("          DATA MAHASISWA           ")
    println("===================================")
    for (mhs in daftarMahasiswa) {
        mhs.tampilkan()
        println("===================================")
    }
}