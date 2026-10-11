class Mahasiswa(
    val nim: String,
    val nama: String,
    var jurusan: String,
    var ipk: Double
) {
    val angkatan: Int = nim.takeLast(2).toInt()

    fun tampilkan() {
        println("NIM      : $nim")
        println("Nama     : $nama")
        println("Jurusan  : $jurusan")
        println("Angkatan : 20$angkatan")
        println("IPK      : $ipk")
        println("Predikat : ${predikat()}")
        println("Status   : ${if (isLulus()) "Lulus" else "Tidak Lulus"}")
        println("-------------------------")
    }

    fun predikat(): String {
        if (ipk >= 3.5) return "Cumlaude"
        if (ipk >= 3.0) return "Sangat Memuaskan"
        if (ipk >= 2.0) return "Cukup"
        return "Kurang"
    }

    fun isLulus(): Boolean {
        return ipk >= 2.0
    }

    fun updateIpk(ipkBaru: Double) {
        if (ipkBaru >= 0.0 && ipkBaru <= 4.0) {
            ipk = ipkBaru
            println("IPK berhasil diupdate menjadi $ipk")
        } else {
            println("IPK tidak valid!")
        }
    }
}

fun main() {
    //Membuat 5 objek mahasiswa
    val m1 = Mahasiswa("220101", "Budi", "Informatika", 3.8)
    val m2 = Mahasiswa("220102", "Ani", "Sistem Informasi", 3.4)
    val m3 = Mahasiswa("230103", "Doni", "Teknik Komputer", 2.5)
    val m4 = Mahasiswa("210104", "Siti", "Manajemen", 3.9)
    val m5 = Mahasiswa("230105", "Joko", "Akuntansi", 1.8)

    val listMhs = arrayOf(m1, m2, m3, m4, m5)

    //Menampilkan data semua mahasiswa
    println("=== DATA SEMUA MAHASISWA ===")
    for (m in listMhs) {
        m.tampilkan()
    }

    //Menampilkan mahasiswa yang lulus
    println("\n=== MAHASISWA YANG LULUS ===")
    for (m in listMhs) {
        if (m.isLulus()) {
            println("- ${m.nama} (IPK: ${m.ipk})")
        }
    }

    //Menampilkan mahasiswa Cumlaude
    println("\n=== MAHASISWA CUMLAUDE ===")
    for (m in listMhs) {
        if (m.predikat() == "Cumlaude") {
            println("- ${m.nama} (IPK: ${m.ipk})")
        }
    }

    //Update IPK
    println("\n=== UPDATE IPK ===")
    println("Sebelum update:")
    m5.tampilkan()

    m5.updateIpk(3.0)

    println("\nSetelah update:")
    m5.tampilkan()
}