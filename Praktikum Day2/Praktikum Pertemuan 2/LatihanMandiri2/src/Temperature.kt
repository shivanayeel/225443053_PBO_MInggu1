class Temprature(celciusAwal: Double = 0.0) {
    var celcius: Double = 0.0
        private set

    val fahrenheit: Double
        get() = (celcius * 9 / 5) + 32

    val kelvin: Double
        get() = (celcius + 273.15)

    init {
        setCelcius(celciusAwal)
    }

    fun setCelcius(value: Double): Boolean {
        return if (value >= -273.15) {
            celcius = value
            true
        } else {
            println("Error")
            false
        }
    }

    fun setFahranheit(value: Double): Boolean {
        val celciusCalculated = (value - 32) * 5 / 9
        return setCelcius(celciusCalculated)
    }

    fun setKelvin(value: Double): Boolean {
        val celciusCalculated = value - 273.15
        return setCelcius(celciusCalculated)
    }

    fun display() {
        println("--- Temprature ---")
        println("Celcius: $celcius")
        println("Fahrenheit: $fahrenheit")
        println("Kelvin: $kelvin\n")
    }
}

fun main() {
    val suhu = Temprature(celciusAwal = 10.0)
    suhu.display()

    suhu.setFahranheit(100.0)
    suhu.display()

    suhu.setKelvin(300.0)
    suhu.display()

    suhu.setCelcius(-300.0)
    suhu.display()
}