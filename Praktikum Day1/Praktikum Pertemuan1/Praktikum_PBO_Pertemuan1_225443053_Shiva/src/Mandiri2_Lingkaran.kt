import kotlin.math.PI

class Lingkaran(var jariJari: Double) {
    fun hitungLuas(): Double {
        return PI * jariJari * jariJari
    }

    fun hitungKeliling(): Double {
        return 2 * PI * jariJari
    }

    fun cetakInfo() {
        println("Lingkaran dengan Jari-jari: $jariJari")
        println("Luas     : ${String.format("%.2f", hitungLuas())}")
        println("Keliling : ${String.format("%.2f", hitungKeliling())}")
        println("-----------------------------------")
    }
}

fun main() {
    println("=== PERHITUNGAN LINGKARAN ===")
    val lingkaran1 = Lingkaran(7.0)
    val lingkaran2 = Lingkaran(14.0)

    lingkaran1.cetakInfo()
    lingkaran2.cetakInfo()
}