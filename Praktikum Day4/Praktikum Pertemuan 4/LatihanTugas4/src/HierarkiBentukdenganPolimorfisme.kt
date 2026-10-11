import java.security.Principal
import kotlin.math.PI

open class Shape{
    open fun area(): Double = 0.0
    open fun perimeter(): Double = 0.0
    open fun name(): String = "Shape"

    class Circle(val radius: Double) : Shape(){
        override fun area(): Double = radius * radius * PI
        override fun perimeter(): Double = 2 * radius * PI
        override fun name(): String = "Circle"
    }

    open class Rectangle(val width: Double, val height: Double): Shape(){
        override fun area(): Double = width * height
        override fun perimeter(): Double = 2 * (width + height)
        override fun name(): String = "Rectangle"
    }

    class Square(val side: Double): Rectangle(side, side){
        override fun name(): String = "Square"
    }
}

fun main(){
    val shapes: List<Shape> = listOf(
        Shape.Circle(7.0),
        Shape.Rectangle(7.0, 9.0),
        Shape.Square(8.0),
    )

    for (shape in shapes){
        println("Nama           : ${shape.name()}")
        println("Area           : ${shape.area()}")
        println("Perimeter      : ${shape.perimeter()}")

        when (shape){
            is Shape.Circle -> println("Detail Jari-Jari: ${shape.radius}")
            is Shape.Rectangle -> println("Detail Panjang: ${shape.width}, lebar ${shape.height}")
            is Shape.Square -> println("Detail Lebar: ${shape.side}")
        }
        println("------------------------------------")
    }
}