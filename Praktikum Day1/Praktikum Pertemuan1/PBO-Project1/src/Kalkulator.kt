class Kalkulator {
    fun tambah(a: Double, b: Double): Double = a + b

    fun kurang(a: Double, b: Double): Double = a - b

    fun kali(a: Double, b: Double): Double = a * b

    fun bagi(a: Double, b: Double): Double {
        if (b == 0.0) {
            println("Peringatan: Pembagian dengan nol!")
            return 0.0
        }
        return a / b
    }

    fun tampilkanOperasi(a: Double, b: Double, operator: Char) {
        val hasil = when (operator) {
            '+' -> tambah(a, b)
            '-' -> kurang(a, b)
            '*' -> kali(a, b)
            '/' -> bagi(a, b)
            else -> {
                println("Operator tidak valid!")
                return
            }
        }
        println("$a $operator $b = $hasil")
    }
}

fun main() {
    val calc = Kalkulator()

    calc.tampilkanOperasi(10.0, 5.0, '+')
    calc.tampilkanOperasi(10.0, 5.0, '-')
    calc.tampilkanOperasi(10.0, 5.0, '*')
    calc.tampilkanOperasi(10.0, 2.0, '/')
    calc.tampilkanOperasi(10.0, 0.0, '/')
}