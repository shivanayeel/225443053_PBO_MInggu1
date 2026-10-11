import kotlin.math.PI

class Lingkaran(
    var jariJari: Double
) {
    //Untuk menghitung luas lingkaran (π * r^2)
    fun luas(): Double {
        return PI * jariJari * jariJari
    }

    //Untuk menghitung keliling lingkaran (2 * π * r)
    fun keliling(): Double {
        return 2 * PI * jariJari
    }

    //Menampilkan jari-jari, luas, dan keliling
    fun tampilkan() {
        println("Informasi Lingkaran")
        println("Jari-jari : $jariJari")
        println("Luas      : ${luas()}")
        println("Keliling  : ${keliling()}")
        println()
    }
}

fun main() {
    val lingkaran1 = Lingkaran(7.0)

    lingkaran1.tampilkan()

    println("Mengubah jari-jari menjadi 14.0\n")
    lingkaran1.jariJari = 14.0
    lingkaran1.tampilkan()
}