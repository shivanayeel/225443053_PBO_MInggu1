import kotlin.math.PI

open class Shape(val name: String) {
    open fun luas(): Double {
        return 0.0
    }

    fun tampilkanInfo() {
        println("Bentuk : $name")
        println("Luas   : ${luas()}")
    }
}

class Rectangle(
    name: String,
    val panjang: Double,
    val lebar: Double
) : Shape(name) {

    override fun luas(): Double {
        return panjang * lebar
    }
}

class Circle(
    name: String,
    val jariJari: Double
) : Shape(name) {

    override fun luas(): Double {
        return PI * jariJari * jariJari
    }
}

class Triangle(
    name: String,
    val alas: Double,
    val tinggi: Double
) : Shape(name) {

    override fun luas(): Double {
        return 0.5 * alas * tinggi
    }
}

fun main() {
    val rectangle = Rectangle("Persegi Panjang", 5.0, 4.0)
    val circle = Circle("Lingkaran", 7.0)
    val triangle = Triangle("Segitiga", 6.0, 8.0)

    val shapes: List<Shape> = listOf(rectangle, circle, triangle)

    println("=== INFORMASI BENTUK DAN LUAS ===")
    for (shape in shapes) {
        shape.tampilkanInfo()
        println("---------------------------------")
    }
}