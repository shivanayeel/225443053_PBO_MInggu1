class PersegiPanjang(var panjang: Double, var lebar: Double) {

    fun hitungLuas(): Double {
        return panjang * lebar
    }

    fun ubahUkuran(panjangBaru: Double, lebarBaru: Double) {
        panjang = panjangBaru
        lebar = lebarBaru
    }

    fun isSquare(): Boolean {
        return panjang == lebar
    }
}

fun main() {
    val pp = PersegiPanjang(10.0, 5.0)

    println("--- Luas Awal ---")
    println("Luas awal: ${pp.hitungLuas()}")
    println("Apakah berbentuk persegi? ${pp.isSquare()}\n")

    pp.ubahUkuran(20.0, 10.0)

    println("--- Luas Baru ---")
    println("Luas baru: ${pp.hitungLuas()}")
    println("Apakah berbentuk persegi? ${pp.isSquare()}")
}